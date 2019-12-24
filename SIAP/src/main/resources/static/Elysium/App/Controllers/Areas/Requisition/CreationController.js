/**
 * UserController.js Version 1.0
 * @abstract Controller for Good view
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 23/11/2018
 */
Elysium.App.Controllers.Areas.Requisition.CreationController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	var AdministrativeUnitService = Elysium.App.Services.Admin.AdministrativeUnitService;
	var UserService = Elysium.App.Services.Admin.UserService;
	
	var BudgetService = Elysium.App.Services.Requisition.BudgetService;
	var ChapterService = Elysium.App.Services.Requisition.ChapterService;
	var EntryService = Elysium.App.Services.Requisition.EntryService;
	var RequestService = Elysium.App.Services.Requisition.RequestService;
	var RequestDetailService = Elysium.App.Services.Requisition.RequestDetailService;
	/*******************************************************************************/
    /*                                  Attributes                                 */
    /*******************************************************************************/
    // Arguments
    var Attr = arguments;
    // Spinner
    var ISpinner = Elysium.Implements(new Elysium.UI.Entities.Spinner({
        image: Elysium.SpinnerImage,
        bg_color: Elysium.SpinnerBackground
    }), [Elysium.UI.Interfaces.ISpinner]);
    // RequestListTable
    var RequestListTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.RequestListTable,
        responsive: true,
        paging: true,
        columnDefs: [
        	{
        		targets: [3],
                className: "dt-center",
                render: $.fn.dataTable.render.moment(moment.ISO_8601, 'DD/MM/YYYY h:mm:ss A')
        	},
        	{
        		targets: [4],
        		searchable: false,
                orderable: false,
                className: "dt-center",
                render: function (data, type, full, meta) {
                	if(full.status == 'En proceso')
                		return '<div class="btn-group">' +
                			   	   '<button data-elysium-requisition-creation-list-continue type="button" class="btn btn-primary"><span class="fas fa-play"></span></button>' +
                			   '</div>';
                	else
                		return '<div class="btn-group">' +
     			   	   		   	   '<button data-elysium-requisition-creation-list-visualize type="button" class="btn btn-secondary"><span class="fas fa-eye"></span></button>' +
        			           '</div>';
                    
                }	
        	}
        	
        ],
        columns: [
        	{ data: "code" },
        	{ data: "status" },
            { data: "amount" },
            { data: "creationDate" },
            { data: "" }
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // RequestDetailTable
    var RequestDetailTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.RequestDetailTable,
        responsive: true,
        paging: true,
        columnDefs: [
        	{
    			targets: [1,2,3],
                searchable: false,
                orderable: false,
                className: 'dt-center'
    		},
        	{
        		targets: [4],
                className: "dt-center",
                render: function (data, type, full, meta) {
            		return '<div class="btn-group">' +
            			   	   '<button data-elysium-requisition-creation-detail-delete type="button" class="btn btn-danger"><span class="fas fa-trash"></span></button>' +
            			   '</div>';
                }	
        	}
        ],
        columns: [
        	{ data: "asset" },
        	{ data: "quantity" },
            { data: "unitPrice" },
            { data: "total" },
            { data: "" }
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // Form 
    var RequestEditForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.RequestEditForm), [Elysium.UI.Interfaces.IForm]);
    var RequestEditFormParsley = null; 
    var RequestDetailForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.RequestDetailForm), [Elysium.UI.Interfaces.IForm]);
    var RequestDetailFormParsley = null;
    // Form variable
    var RequestListFormParsley = null;
    // Current objects
    var GLOBAL_CURRENT_USER = null;
    var GLOBAL_CURRENT_ADMINISTRATIVE_UNIT = null;
    var GLOBAL_REQUEST = null;
    /*******************************************************************************/
    /*                                   Methods                                   */
    /*******************************************************************************/
    /***********************************************/
    /*                      Utils                  */
    /***********************************************/
    /***
     * @name SetDataOnSelect
     * @asbtract Method to set data on select
     */
    var SetDataOnSelect = function(selectSelector, defaultText, data, callback) {
    	// Clean options
    	$(selectSelector).empty();
    	// Add default option
    	$(selectSelector).append($('<option>', {
    	    text: defaultText
    	}));
    	// Loop in data to add options
    	data.forEach(function(element, index, array) {
    		$(selectSelector).append($('<option>', callback(element)));
    	});
    }
    /**
     * @name ControllerValidators
     * @abstract Method to enable validators
     */
    var ControllerValidators = function() {
    	// Custom function for select
    	window.Parsley.addValidator('select', {
    	    requirementType: 'string',
    	    validateString: function(value, requirement) {
    	      return value != requirement;
    	    }
    	});
    }
    /***********************************************/
    /*                    Methods                  */
    /***********************************************/
    /**
     * @name GetCurren
     * @abstract Method to get current user
     */
    var GetCurrentUser = function() {
    	// Define promise
    	var deferred = new $.Deferred();
    	// Request data
    	UserService.GetCurrentUser().then(
			function(data) {
				// Set data on tale
				GLOBAL_CURRENT_USER = data;
				// resolve promise
				deferred.resolve();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            deferred.reject();
			}
		);
    	// Return promise
    	return deferred.promise();
    }
    /**
     * @name GetAdministrativeUnit
     * @abstract Method to get administrative unit
     */
    var GetAdministrativeUnit = function() {
    	// Define promise
    	var deferred = new $.Deferred();
    	// Request data
    	AdministrativeUnitService.GetByUserDataId(GLOBAL_CURRENT_USER.userData.userDataId).then(
			function(data) {
				// Set data on tale
				GLOBAL_CURRENT_ADMINISTRATIVE_UNIT = data;
				// resolve promise
				deferred.resolve();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            deferred.reject();
			}
		);
    	// Return promise
    	return deferred.promise();
    }
    /**
     * @name GetBudget
     * @abstract Method to get budget
     */
    var GetBudget = function() {
    	// Define promise
    	var deferred = new $.Deferred();
    	// Request data
    	BudgetService.GetByAdministrativeUnitId(GLOBAL_CURRENT_ADMINISTRATIVE_UNIT.administrativeUnitId).then(
			function(data) {
				// Set data on select
				SetDataOnSelect(Attr.UI.RequestListBudgetId, 'Seleccionar Ejercicio', data, function(element) {
					return {
		    			value: element.budgetId,
		        	    text: element.code + ' - ' + element.description + ' - ' + element.season
		        	}
				});
				
				SetDataOnSelect(Attr.UI.RequestEditBudgetId, 'Seleccionar Ejercicio', data, function(element) {
					return {
		    			value: element.budgetId,
		        	    text: element.code + ' - ' + element.description + ' - ' + element.season
		        	}
				});
				// resolve promise
				deferred.resolve();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            deferred.reject();
			}
		);
    	// Return promise
    	return deferred.promise();
    }
    /**
     * @name RequestListBudgetIdChange
     * @abstract Method to request list budget id change
     */
    var RequestListBudgetIdChange = function() {
    	var value = $(this).val();
    	if(value != 'Seleccionar Ejercicio') {
    		// Show spinner
        	ISpinner.Show(Attr.UI.MainPanel);
        	// Request data
        	ChapterService.GetByBudgetId(value).then(
    			function(data) {
    				// Set data on select
    				SetDataOnSelect(Attr.UI.RequestListChapterId, 'Seleccionar Capítulo', data, function(element) {
    					return {
    		    			value: element.chapterId,
    		        	    text: element.code + ' - ' + element.concept
    		        	}
    				});
    				$(Attr.UI.RequestListEntryId).empty();
    				// Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}, 
    			function(xhr){
    				// Show error
    	            Elysium.Directives.RequestError.ThrowXhr(xhr);
    	            // Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}
    		);
    	}
    	else {
    		// Clean selects
    		$(Attr.UI.RequestListChapterId).empty();
    		$(Attr.UI.RequestListEntryId).empty();
    	}
    }
    /**
     * @name RequestListChapterIdChange
     * @abstract Event fired when chapter list change
     */
    var RequestListChapterIdChange = function() {
    	var value = $(this).val();
    	if(value != 'Seleccionar Capítulo') {
    		// Show spinner
        	ISpinner.Show(Attr.UI.MainPanel);
        	// Request data
        	EntryService.GetByChapterId(value).then(
    			function(data) {
    				// Set data on select
    				SetDataOnSelect(Attr.UI.RequestListEntryId, 'Seleccionar Partida', data, function(element) {
    					return {
    		    			value: element.entryId,
    		        	    text: element.code + ' - ' + element.description
    		        	}
    				});
    				// Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}, 
    			function(xhr){
    				// Show error
    	            Elysium.Directives.RequestError.ThrowXhr(xhr);
    	            // Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}
    		);
    	}
    	else {
    		// Clean selects
    		$(Attr.UI.RequestListEntryId).empty();
    	}
    }
    /**
     * @name RequestListFormParsleyOnSuccess
     * @abstract Event fired when request list form success
     */
    var RequestListFormParsleyOnSuccess = function() {
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Request data
    	RequestService.GetByEntryIdStatus($(Attr.UI.RequestListEntryId).val(), $(Attr.UI.RequestListStatus).val()).then(
			function(data) {
				RequestListTable.SetData(data);
				// Hide spinner
				ISpinner.Hide(Attr.UI.MainPanel);
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            // Hide spinner
				ISpinner.Hide(Attr.UI.MainPanel);
			}
		);
    	return false;
    }
    /**
     * @name RequestEditNewClick
     * @abstract Method to request edit new click
     */
    var RequestEditNewClick = function() {
    	// Reset parsley
    	RequestEditFormParsley.reset();
    	// Clean form
    	RequestEditForm.Clean();
    	// Clean detail form 
    	RequestDetailForm.Clean();
    	// Clean parsley
    	if(RequestDetailFormParsley != null) {
    		RequestDetailFormParsley.reset();
    		$(Attr.UI.RequestDetailForm).parsley().destroy();
    		RequestDetailFormParsley = null;
    	}
    	$(Attr.UI.RequestDetailQuantity).val("1");
    	$(Attr.UI.RequestDetailUnitPrice).val("0.01");
    	$(Attr.UI.RequestDetailTotal).val("0.01");
    	$(Attr.UI.RequestEditBudgetId).val('Seleccionar Ejercicio');
    	$("[data-elysium-requisition-edit-element]").prop('disabled', false);
    	$("[data-elysium-requisition-creation-detail-element]").prop('disabled', true);
		$("[data-elysium-requisition-creation-close]").prop('disabled', true);
		// Set administrative unit name 
		$(Attr.UI.RequestEditAdministrativeUnitName).val(GLOBAL_CURRENT_ADMINISTRATIVE_UNIT.code + ' - ' + GLOBAL_CURRENT_ADMINISTRATIVE_UNIT.description);
		// Hide / Show
		$(Attr.UI.RequestListContainer).hide();
		$(Attr.UI.RequestEditContainer).show();
		// reset table
		RequestDetailTable.SetData([]);
		// Request the next code
		ISpinner.Show(Attr.UI.MainPanel);
		RequestService.GetNextCode().then(
			function(nextCode) {
				// Set code
				$(Attr.UI.RequestEditCode).val(nextCode.code);
				// Hide spinner
				ISpinner.Hide(Attr.UI.MainPanel);
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            // Hide spinner
				ISpinner.Hide(Attr.UI.MainPanel);
				// Click return
				$(Attr.UI.RequestEditReturn).click();
			}
		);
    }
    /**
     * @name RequestEditReturnClick
     * @abstract Method to return to list
     */
    var RequestEditReturnClick = function () {
    	$(Attr.UI.RequestListContainer).show();
		$(Attr.UI.RequestEditContainer).hide();
		$(Attr.UI.RequestListForm).submit();
    }
    /**
     * @name RequestEditBudgetIdChange
     * @abstract Event fired when budget change
     */
    var RequestEditBudgetIdChange = function() {
    	var value = $(this).val();
    	if(value != 'Seleccionar Ejercicio') {
    		// Show spinner
        	ISpinner.Show(Attr.UI.MainPanel);
        	// Request data
        	ChapterService.GetByBudgetId(value).then(
    			function(data) {
    				// Set data on select
    				SetDataOnSelect(Attr.UI.RequestEditChapterId, 'Seleccionar Capítulo', data, function(element) {
    					return {
    		    			value: element.chapterId,
    		        	    text: element.code + ' - ' + element.concept
    		        	}
    				});
    				$(Attr.UI.RequestEditEntryId).empty();
    				// Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}, 
    			function(xhr){
    				// Show error
    	            Elysium.Directives.RequestError.ThrowXhr(xhr);
    	            // Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}
    		);
    	}
    	else {
    		// Clean selects
    		$(Attr.UI.RequestEditChapterId).empty();
    		$(Attr.UI.RequestEditEntryId).empty();
    	}
    }
    /**
     * @name RequestEditChapterIdChange
     * @abstract Event fired when chapter change
     */
    var RequestEditChapterIdChange = function() {
    	var value = $(this).val();
    	if(value != 'Seleccionar Capítulo') {
    		// Show spinner
        	ISpinner.Show(Attr.UI.MainPanel);
        	// Request data
        	EntryService.GetByChapterId(value).then(
    			function(data) {
    				var cleaned = [];
    				data.forEach(function(element, index, array) {
    					if((element.amountAllocated - element.amountUsed) > 0)
    						cleaned.push(element);
    				});
    				// Set data on select
    				SetDataOnSelect(Attr.UI.RequestEditEntryId, 'Seleccionar Partida', cleaned, function(element) {
    					return {
    		    			value: element.entryId,
    		        	    text: element.code + ' - ' + element.description
    		        	}
    				});
    				// Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}, 
    			function(xhr){
    				// Show error
    	            Elysium.Directives.RequestError.ThrowXhr(xhr);
    	            // Hide spinner
    				ISpinner.Hide(Attr.UI.MainPanel);
    			}
    		);
    	}
    	else {
    		// Clean selects
    		$(Attr.UI.RequestEditEntryId).empty();
    	}
    }
    /**
     * @name RequestEditFormParsleyOnSuccess
     * @abstract Event fired when edit form success 
     */
    var RequestEditFormParsleyOnSuccess = function() {
    	var request = {
        		entryId: $(Attr.UI.RequestEditEntryId).val(),
        		code: $(Attr.UI.RequestEditCode).val()
        	}
        	Elysium.UI.Entities.MsgBox.DialogQuestion(
	            'Guardar requisición',
	            '¿Desea guardar la requisición?',
	            function () {
	            	// Send information to server
	                RequestService.Save(request).then(
	                    function (response) {
	                    	// Get request
	                    	RequestService.GetByCode(request.code).then(
	                    		function(request) {
	                    			GLOBAL_REQUEST = request;
	                    			// Close message
	                            	Elysium.UI.Entities.MsgBox.Close();
	                                // Show success information
	                                Elysium.UI.Entities.Notification.Success({ text: "La requisicion se ha guardado correctamente", time: Elysium.NotificationTime });
	                                // Reset parsley
	                                RequestEditFormParsley.reset()
	                                // disable elements   
	                                $("[data-elysium-requisition-edit-element]").prop('disabled', true);
	                                // enable elements
	                                $("[data-elysium-requisition-creation-detail-element]").prop('disabled', false);
	                                // Enable parsley
	                                RequestDetailFormParsley = $(Attr.UI.RequestDetailForm).parsley().on('form:submit', RequestDetailFormParsleyOnSuccess);
	                    		},
	                    		function (xhr) {
	                            	// Close message
	                            	Elysium.UI.Entities.MsgBox.Close();
	                                // Show error
	                                Elysium.Directives.RequestError.ThrowXhr(xhr);
	                            }
	                    	);
	                    },
	                    function (xhr) {
	                    	// Close message
	                    	Elysium.UI.Entities.MsgBox.Close();
	                        // Show error
	                        Elysium.Directives.RequestError.ThrowXhr(xhr);
	                    }
	                );
	            }
	        );
    	return false;
    }
    /**
     * @name RequestDetailFormParsleyOnSuccess
     * @abstract Method to add request detail form parsley on success
     */
    var RequestDetailFormParsleyOnSuccess = function() {
    	// Get value
    	var requestDetail = RequestDetailForm.GetValues();
    	requestDetail['requestId'] =  GLOBAL_REQUEST.requestId;
    	// Show confirmation dialog
    	Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Guardar detalle de requisición',
            '¿Desea guardar el detalle de la requisición?',
            function () {
            	// Send information to server
                RequestDetailService.Save(requestDetail).then(
                    function (response) {
                    	// Get items to set in table
                    	RequestDetailService.GetByRequestId(GLOBAL_REQUEST.requestId).then(
                    		function(items) {
                    			// Close message
                            	Elysium.UI.Entities.MsgBox.Close();
                                // Show success information
                                Elysium.UI.Entities.Notification.Success({ text: "El detalle de la requisición se ha guardado correctamente", time: Elysium.NotificationTime });
                                // Reset parsley
                                RequestDetailFormParsley.reset();
                                RequestDetailForm.Clean();
                                // Clean values
                                $(Attr.UI.RequestDetailQuantity).val("1");
                            	$(Attr.UI.RequestDetailUnitPrice).val("0.01");
                            	$(Attr.UI.RequestDetailTotal).val("0.01");
                            	// Set data
                            	RequestDetailTable.SetData(items);
                            	// Check quantity to see if need enable the last button
                            	if(items.length > 0) {
                            		$("[data-elysium-requisition-creation-close]").prop('disabled', false);
                            	}
                    		},
                    		function (xhr) {
                            	// Close message
                            	Elysium.UI.Entities.MsgBox.Close();
                                // Show error
                                Elysium.Directives.RequestError.ThrowXhr(xhr);
                            }
                    	);
                    },
                    function (xhr) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show error
                        Elysium.Directives.RequestError.ThrowXhr(xhr);
                    }
                );
            }
        );
    	return false;
    }
    /**
     * @name DeleteDetail
     * @abstract Method to delete detail
     */
    var DeleteDetail = function(object, row, data, event) {
    	Elysium.UI.Entities.MsgBox.DialogQuestion(
                'Eliminar detalle de requisición',
                '¿Desea eliminar el detalle de la requisición?',
                function () {
                	// Send information to server
                    RequestDetailService.Delete(data.requestDetailId).then(
                        function (response) {
                        	// Get items to set in table
                        	RequestDetailService.GetByRequestId(GLOBAL_REQUEST.requestId).then(
                        		function(items) {
                        			// Close message
                                	Elysium.UI.Entities.MsgBox.Close();
                                    // Show success information
                                    Elysium.UI.Entities.Notification.Success({ text: "El detalle de la requisición se ha eliminado correctamente", time: Elysium.NotificationTime });
                                    // Set data
                                	RequestDetailTable.SetData(items);
                                	// Check to disable close button
                                	if(items.length <= 0) {
                                		$("[data-elysium-requisition-creation-close]").prop('disabled', true);
                                	}
                        		},
                        		function (xhr) {
                                	// Close message
                                	Elysium.UI.Entities.MsgBox.Close();
                                    // Show error
                                    Elysium.Directives.RequestError.ThrowXhr(xhr);
                                }
                        	);
                        },
                        function (xhr) {
                        	// Close message
                        	Elysium.UI.Entities.MsgBox.Close();
                            // Show error
                            Elysium.Directives.RequestError.ThrowXhr(xhr);
                        }
                    );
                }
            );
    }
    /**
     * @name CalculateDetailTotal
     * @abstract Method to calculate detail total
     */
    var CalculateDetailTotal = function() {
    	var quantity = $(Attr.UI.RequestDetailQuantity).val().trim();
		var unitPrice = $(Attr.UI.RequestDetailUnitPrice).val().trim();
		if(quantity == "" || unitPrice == "") {
			$(Attr.UI.RequestDetailTotal).val("0.00");
			return;
		}
		else {
			// check if quantity is valid
			if(!isNaN(parseFloat(quantity)) && isFinite(quantity)) {
				// check if unit price is valid
				if(!isNaN(parseFloat(unitPrice)) && isFinite(unitPrice)) {
					var total = parseFloat(quantity) * parseFloat(unitPrice);
					$(Attr.UI.RequestDetailTotal).val((Math.round(total * 100) / 100).toFixed(2));
					return;
				}
				else {
					$(Attr.UI.RequestDetailTotal).val("0.00");
					return;
				}
			}
			else {
				$(Attr.UI.RequestDetailTotal).val("0.00");
				return;
			}
		}	
    }
    /**
     * @name RequestCompleteClick
     * @abstract Event fired when request complete button click
     */
    var RequestCompleteClick = function() {
    	Elysium.UI.Entities.MsgBox.DialogQuestion(
                'Completar requisición',
                '¿Desea completar la requisición?',
                function () {
                	// Send information to server
                    RequestService.Close(GLOBAL_REQUEST.requestId).then(
                        function (response) {
                			// Close message
                        	Elysium.UI.Entities.MsgBox.Close();
                            // Show success information
                            Elysium.UI.Entities.Notification.Success({ text: "La requisición se ha completado correctamente", time: Elysium.NotificationTime });
                            // Return
                            $(Attr.UI.RequestEditReturn).trigger("click");
                        },
                        function (xhr) {
                        	// Close message
                        	Elysium.UI.Entities.MsgBox.Close();
                            // Show error
                            Elysium.Directives.RequestError.ThrowXhr(xhr);
                        }
                    );
                }
            );
    }
    /**
     * @name ContinueRequisition
     * @abstract Method to continue requisition
     */
    var ContinueRequisition = function(object, row, data, event) {
    	// Define error
    	var error = function(xhr) {
    		// Show error
            Elysium.Directives.RequestError.ThrowXhr(xhr);
            // Click
            $(Attr.UI.RequestEditReturn).trigger("click");
    	};
    	// With information of request get the entry
    	EntryService.GetByEntryId(data.entryId).then(function(entry) {
    		// Get information of chapter
    		ChapterService.GetByChapterId(entry.chapterId).then(function(chapter) {
    			// Get Budget
    			BudgetService.GetByBudgetId(chapter.budgetId).then(function(budget) {
    				// Get items of request
    				RequestDetailService.GetByRequestId(data.requestId).then(function(requestDetailItems) {
    					// Set global request
    					GLOBAL_REQUEST = data;
    					// Set administrative unit name 
    					$(Attr.UI.RequestEditAdministrativeUnitName).val(GLOBAL_CURRENT_ADMINISTRATIVE_UNIT.code + ' - ' + GLOBAL_CURRENT_ADMINISTRATIVE_UNIT.description);
    					// Set code
    					$(Attr.UI.RequestEditCode).val(GLOBAL_REQUEST.code);
    					// Set elements in select
    					SetDataOnSelect(Attr.UI.RequestEditEntryId, 'Seleccionar Partida', [entry], function(element) {
        					return {
        						value: element.entryId,
        						text: element.code + ' - ' + element.description
        					}
        				});
        				SetDataOnSelect(Attr.UI.RequestEditChapterId, 'Seleccionar Capítulo', [chapter], function(element) {
        					return {
        						value: element.chapterId,
        						text: element.code + ' - ' + element.concept
        					}
        				});
        				SetDataOnSelect(Attr.UI.RequestEditBudgetId, 'Seleccionar Ejercicio', [budget], function(element) {
        					return {
        						value: element.budgetId,
        						text: element.code + ' - ' + element.description + ' - ' + element.season
        					}
        				});
        				$(Attr.UI.RequestEditEntryId).val(entry.entryId);
        				$(Attr.UI.RequestEditChapterId).val(chapter.chapterId);
        				$(Attr.UI.RequestEditBudgetId).val(budget.budgetId);
        				// Set items of table
        				RequestDetailTable.SetData(requestDetailItems);
        				if(requestDetailItems.length > 0) {
                    		$("[data-elysium-requisition-creation-close]").prop('disabled', false);
                    	}
        				// disable elements   
                        $("[data-elysium-requisition-edit-element]").prop('disabled', true);
                        // enable elements
                        $("[data-elysium-requisition-creation-detail-element]").prop('disabled', false);
                        // Reset parsley
                        RequestEditFormParsley.reset();
                        // Enable parsley
                        RequestDetailFormParsley = $(Attr.UI.RequestDetailForm).parsley().on('form:submit', RequestDetailFormParsleyOnSuccess);
                        RequestDetailFormParsley.reset();
                        // Show/Hide
                        $(Attr.UI.RequestEditContainer).show();
                        $(Attr.UI.RequestListContainer).hide();
    				}, error);
    			},error);
    		}, error);
    	}, error);
    }
    /**
     * @name VisualizeRequisition
     * @abstract Method to visualize requisition
     */
    var VisualizeRequisition = function(object, row, data, event) {
    	// Define error
    	var error = function(xhr) {
    		// Show error
            Elysium.Directives.RequestError.ThrowXhr(xhr);
            // Click
            $(Attr.UI.RequestEditReturn).trigger("click");
    	};
    	// With information of request get the entry
    	EntryService.GetByEntryId(data.entryId).then(function(entry) {
    		// Get information of chapter
    		ChapterService.GetByChapterId(entry.chapterId).then(function(chapter) {
    			// Get Budget
    			BudgetService.GetByBudgetId(chapter.budgetId).then(function(budget) {
    				// Get items of request
    				RequestDetailService.GetByRequestId(data.requestId).then(function(requestDetailItems) {
    					// Set global request
    					GLOBAL_REQUEST = data;
    					// Set administrative unit name 
    					$(Attr.UI.RequestEditAdministrativeUnitName).val(GLOBAL_CURRENT_ADMINISTRATIVE_UNIT.code + ' - ' + GLOBAL_CURRENT_ADMINISTRATIVE_UNIT.description);
    					// Set code
    					$(Attr.UI.RequestEditCode).val(GLOBAL_REQUEST.code);
    					// Set elements in select
    					SetDataOnSelect(Attr.UI.RequestEditEntryId, 'Seleccionar Partida', [entry], function(element) {
        					return {
        						value: element.entryId,
        						text: element.code + ' - ' + element.description
        					}
        				});
        				SetDataOnSelect(Attr.UI.RequestEditChapterId, 'Seleccionar Capítulo', [chapter], function(element) {
        					return {
        						value: element.chapterId,
        						text: element.code + ' - ' + element.concept
        					}
        				});
        				SetDataOnSelect(Attr.UI.RequestEditBudgetId, 'Seleccionar Ejercicio', [budget], function(element) {
        					return {
        						value: element.budgetId,
        						text: element.code + ' - ' + element.description + ' - ' + element.season
        					}
        				});
        				$(Attr.UI.RequestEditEntryId).val(entry.entryId);
        				$(Attr.UI.RequestEditChapterId).val(chapter.chapterId);
        				$(Attr.UI.RequestEditBudgetId).val(budget.budgetId);
        				// Set items of table
        				RequestDetailTable.SetData(requestDetailItems);
        				// Disable all element
                    	$("[data-elysium-requisition-creation-close]").prop('disabled', true);
                        $("[data-elysium-requisition-edit-element]").prop('disabled', true);
                        $("[data-elysium-requisition-creation-detail-element]").prop('disabled', true);
                        $("[data-elysium-requisition-creation-detail-delete]").prop('disabled', true);
                        // Reset parsley
                        RequestEditFormParsley.reset();
                        // Show panels
                        $(Attr.UI.RequestEditContainer).show();
                        $(Attr.UI.RequestListContainer).hide();
    				}, error);
    			},error);
    		}, error);
    	}, error);
    }
    /************************************/
    /*              Initialize          */
    /************************************/
    /**
     * @name SetLocale
     * @abstract Method to set locale
     * @param {any} strLocale
     */
    var SetLocale = function (strLocale) { }
    /**
     * @name Initialize
     * @abstract Method to initialize the controller
     */
    var Initialize = function () {
    	// Define hide function
    	var hide = function() {
    		ISpinner.Hide(Attr.UI.MainPanel);  
		};
		// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Enable controller validator
    	ControllerValidators();
    	// Initialize table
    	RequestListTable.Initialize();
    	RequestDetailTable.Initialize();
    	// Get Current user
    	GetCurrentUser().then(function() {
    		// Get administrative unit
    		GetAdministrativeUnit().then(function () { 
    			// Get budget information
    			GetBudget().then(function() {
    				// Bind events
    				$(Attr.UI.RequestListBudgetId).change(RequestListBudgetIdChange);
    				$(Attr.UI.RequestListChapterId).change(RequestListChapterIdChange);
    				$(Attr.UI.RequestEditBudgetId).change(RequestEditBudgetIdChange);
    				$(Attr.UI.RequestEditChapterId).change(RequestEditChapterIdChange);
    				RequestListTable.OnEvent('click', '[data-elysium-requisition-creation-list-continue]', ContinueRequisition);
    				RequestListTable.OnEvent('click', '[data-elysium-requisition-creation-list-visualize]', VisualizeRequisition);
    				$(Attr.UI.RequestEditNew).click(RequestEditNewClick);
    				$(Attr.UI.RequestEditReturn).click(RequestEditReturnClick);
    				// Enable parsley
    				RequestListFormParsley = $(Attr.UI.RequestListForm).parsley().on('form:submit', RequestListFormParsleyOnSuccess);
    				RequestEditFormParsley = $(Attr.UI.RequestEditForm).parsley().on('form:submit', RequestEditFormParsleyOnSuccess);
    				// Disable and hide initial elements
    				$(Attr.UI.RequestEditContainer).hide();
    				$("[data-elysium-requisition-creation-detail-element]").prop('disabled', true);
    				$("[data-elysium-requisition-creation-close]").prop('disabled', true);
    				RequestDetailTable.OnEvent('click', '[data-elysium-requisition-creation-detail-delete]', DeleteDetail);
    				$(Attr.UI.RequestDetailQuantity).keyup(CalculateDetailTotal);
    				$(Attr.UI.RequestDetailUnitPrice).keyup(CalculateDetailTotal);
    				$(Attr.UI.RequestComplete).click(RequestCompleteClick);
    				hide();
    			}, hide);
    		}, hide);
    	}, hide);
    }
    /*******************************************************************************/
    /*                                Encapsulation                                */
    /*******************************************************************************/
    return {
        SetLocale: SetLocale,
        Initialize: Initialize
    }
}