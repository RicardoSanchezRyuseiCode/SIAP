/**
 * CreationController.js Version 1.0
 * @abstract Controller for Good view
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 24/11/2018
 */
Elysium.App.Controllers.Areas.Award.CreationController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	// Award
	var AdjudicationService = Elysium.App.Services.Award.AdjudicationService;	
	var EmissionService = Elysium.App.Services.Award.EmissionService;
	var CompetitorService = Elysium.App.Services.Award.CompetitorService;
	var SupplierService = Elysium.App.Services.Award.SupplierService;	
	var InvitationService = Elysium.App.Services.Award.InvitationService;
	var AnnexService = Elysium.App.Services.Award.AnnexService;
	var AssetService = Elysium.App.Services.Award.AssetService;
	var OpeningService = Elysium.App.Services.Award.OpeningService;
	var ProposalService = Elysium.App.Services.Award.ProposalService;
	var QuotationService = Elysium.App.Services.Award.QuotationService;
	// Util
	var DateParserService = Elysium.App.Services.Util.DateParserService;
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
    // Supplier Table
    var ISupplierDataTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.InvitationTable,
        responsive: true,
        paging: true,
        columnDefs: [],
        columns: [
            { data: "name" },
            { data: "address" },
            { data: "city" },
            { data: "state" },
            { data: "tradeNumber" },
        ]
    }), [Elysium.UI.Interfaces.ITable]);    
    // Append Table
    var IAppendDataTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.AppendTable,
        responsive: true,
        paging: true,
        keys: true,
        columnDefs: [
    		{
    			targets: 1,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                    return '<input type="checkbox" data-dt-checkbox-child />';
                }
    		},
    		{
    			targets: 2,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                    return '<input type="text" class="form-control" value="' + full.annex.quantity + '" data-adjudication-annex-quantity disabled="disabled" />';
                }
    		},
    		{
    			targets: 3,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                    return '<input type="text" class="form-control" value="' + full.annex.deliveryTerm + '" data-adjudication-annex-deliveryterm disabled="disabled" />';
                }
    		},
    		{
    			targets: 4,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                    return '<input type="text" class="form-control" value="' + full.annex.deliveryPlace + '" data-adjudication-annex-deliveryplace disabled="disabled" />';
                }
    		}
    		
        ],
        columns: [
            { data: "asset.name" },
            { data: "" },
            { data: "" },
            { data: "" },
            { data: "" },
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // DATable1
    var IDTA1DataTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.DetailTATable1,
        responsive: true,
        paging: true,
        keys: true,
        columnDefs: [
        	
        	{
    			targets: 1,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                    return '<select class="form-control" data-adjudication-proposal-technicalsubmitted>' +
                    			'<option selected>Seleccionar...</option>' +
                    			'<option value="Si Presentó">Si Presentó</option>' +
                    			'<option value="No Presentó">No Presentó</option>' +
                    	   '</select>';
                }
    		},
    		{
    			targets: 2,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<input type="text" class="form-control" value="" data-adjudication-proposal-technicalremark />';
                }
    		},
    		{
    			targets: 3,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                    return '<select class="form-control" data-adjudication-proposal-economicsubmitted>' +
                    			'<option selected>Seleccionar...</option>' +
                    			'<option value="Si Presentó">Si Presentó</option>' +
                    			'<option value="No Presentó">No Presentó</option>' +
                    	   '</select>';
                }
    		},
    		{
    			targets: 4,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<input type="text" class="form-control" value="" data-adjudication-proposal-economicremark />';
                }
    		},
    		{
    			targets: 5,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<label data-adjudication-proposal-totalamount>$0.00</label>';
                }
    		},
    		{
    			targets: 6,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<label data-adjudication-proposal-status>Sin modificar</label>';
                }
    		}
        ],
        columns: [
        	{ data: "supplier.name" },
        	{ data: "" },
        	{ data: "" },
        	{ data: "" },
        	{ data: "" },
        	{ data: "" },
        	{ data: "proposal.status" }
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    //DATable2
    var IDTA2DataTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.DetailTATable2,
        responsive: true,
        paging: true,
        keys: true,
        columnDefs: [
        	{
    			targets: 2,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<input type="number" min="0" step="0.05" class="form-control" value="' + full.unitPrice + '" data-adjudication-proposal-item-unitprice />';
                }
    		},
        	{
    			targets: 3,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<label data-adjudication-proposal-item-total>$' + (Math.round(full.totalAmount * 100) / 100).toFixed(2)  + '</label>';
                }
    		}
        ],
        columns: [
        	{ data: "asset.name" },
        	{ data: "annex.quantity" },
        	{ data: "" },
        	{ data: "" },
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // Quotation table
    var IQuotationTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.QuotationTable,
        responsive: true,
        paging: true,
        keys: true,
        columnDefs: [
        	{
    			targets: 1,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<input type="text" class="form-control" value="" data-adjudication-quotation-date />';
                }
    		},
    		{
    			targets: 2,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<input type="text" class="form-control" value="" data-adjudication-quotation-date-text  readonly="readonly" />';
                }
    		},
    		{
    			targets: 3,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<input type="text" class="form-control" value="" data-adjudication-quotation-creditcondition />';
                }
    		},
    		{
    			targets: 4,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<input type="text" class="form-control" value="" data-adjudication-quotation-deliveryterm />';
                }
    		},
    		{
    			targets: 5,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return '<label data-adjudication-quotation-status>' + full.quotationDetail.status + '</label>';
                }
    		}
        ],
        columns: [
        	{ data: "supplier.name" },
        	{ data: "" },
        	{ data: "" },
        	{ data: "" },
        	{ data: "" },
        	{ data: "" },
        ],
    }), [Elysium.UI.Interfaces.ITable]);
    // Global adjudication
    var GLOBAL_ADJUDICATION = null;    
    /*************************************/
    /*       Adjudication Variables      */
    /*************************************/
    // Form
    var FormAdjudication = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.FormAdjudication), [Elysium.UI.Interfaces.IForm]);
    // Validation form
    var ParsleyAdjudication = null;
    /*************************************/
    /*        Invitation Variables       */
    /*************************************/
    // EmissionDatePicker
    var EmissionDatePicker = null;
    // Emission Form
    var EmissionForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.EmissionForm), [Elysium.UI.Interfaces.IForm]);
    // Emission Validation form
    var EmissionParsley = null;
    // Competitor Form
    var CompetitorForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.CompetitorForm), [Elysium.UI.Interfaces.IForm]);
    // Competitor Validation form
    var CompetitorParsley = null;
    // Competitor Supplier
    var CompetitorSuppliers = [];
    /*************************************/
    /*             Annex Variables       */
    /*************************************/
    // Last cell on focus cell
    var LastCellOnFocusCell = null;
    // Last cell on focus
    var LastCellOnFocus = null;
    /*************************************/
    /*             Annex Variables       */
    /*************************************/
    var OpeningForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.OpeningFormOpening), [Elysium.UI.Interfaces.IForm]);
    // Emission Validation form
    var OpeningParsley = null;
    /*************************************/
    /*         Proposal Variables        */
    /*************************************/
    // Last cell on focus
    var LastProposalCellOnFocus = null;
    /*************************************/
    /*        Quotation Variables        */
    /*************************************/
    // Form
    var QuotationForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.QuotationForm), [Elysium.UI.Interfaces.IForm]);
    // Validation form
    var QuotationParsley = null;
    /*************************************/
    /*        Judgment Variables        */
    /*************************************/
    // Validation form
    var JudgmentParsley = null;
    /*******************************************************************************/
    /*                                   Methods                                   */
    /*******************************************************************************/
    /*************************************/
    /*                 Common            */
    /*************************************/
    /**
     * @name ControllerValidators
     * @abstract Method to enable validators
     */
    var ControllerValidators = function() {
    	// Custom valdiation for select
    	window.Parsley.addValidator('select', {
    	    requirementType: 'string',
    	    validateString: function(value, requirement) {
    	      return value != requirement;
    	    }
    	});
    	// Custom validation for procedure
    	window.Parsley.addValidator('procedure', {
    		requirementType: 'string',
    	    validateString: function(value, requirement) {
    	    	var regex = /^[a-zA-Z0-9\s]*$/;
    	        return  regex.test(value);
    	    }
    	});
    }
    /***
     * @name SetDataOnSelect
     * @asbtract Method to set data on select
     */
    var SetDataOnSelect = function(selectSelector, valuePropertie, textPropertie1, textPropertie2, defaultText, data) {
    	// Clean options
    	$(selectSelector).empty();
    	// Add default option
    	$(selectSelector).append($('<option>', {
    	    text: defaultText
    	}));
    	// Loop in data to add options
    	data.forEach(function(element, index, array) {
    		$(selectSelector).append($('<option>', {
    			value: element[valuePropertie],
        	    text: element[textPropertie1] + ' - ' + element[textPropertie2]
        	}));
    	});
    }
    /*************************************/
    /*             Adjudication          */
    /*************************************/
    /**
     * @name OnSuccessAdjudication
     * @abstract Event fired when adjudication form validation success
     */
    var OnSuccessAdjudication = function() {
    	// Get adjudication information
        var adjudication = FormAdjudication.GetValues();
        // Validate if amount is correct
        AdjudicationService.ValidateAmount(adjudication).then(
    		function(response) {
    			var message = "¿Desea guardar la adjudicación?";
    			if(response != "") {
					message = response;
    			}
    			// Show message dialog
    	        Elysium.UI.Entities.MsgBox.DialogQuestion(
    	            'Guardar Adjudicación',
    	            message,
    	            function () {
    	            	// Send information to server
    	            	AdjudicationService.Save(adjudication).then(
    	                    function () {
    	                    	// Get the definition of adjudication saved
    	                    	AdjudicationService.GetByProcedureNumber(adjudication.procedureNumber).then(
    	                    		function (adjudicationSaved) {
    	                    			// Assign global adjudication
    	                    			GLOBAL_ADJUDICATION = adjudicationSaved;
    	                    			// Set status
    	                    			$(Attr.UI.FormAdjudicationStatus).text(GLOBAL_ADJUDICATION.status);
    	                    			// Close message
    	    	                    	Elysium.UI.Entities.MsgBox.Close();
    	    	                        // Show success information
    	    	                        Elysium.UI.Entities.Notification.Success({ text: "La adjudicación se ha guardado correctamente", time: Elysium.NotificationTime });
    	    	                        // Reset validations                       
    	    	                        ParsleyAdjudication.reset();  
    	    	                        // Set elements on read only
    	    	                        $(Attr.UI.FormAdjudication).find("input").prop('disabled', true);
    	    	                        $(Attr.UI.FormAdjudication).find("select").prop('disabled', true);
    	    	                        $(Attr.UI.FormAdjudication).find("button").prop('disabled', true);
    	    	                        // Enable inventory button
    	    	                        $(Attr.UI.BtnInvitation).prop('disabled', false);
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
    			
    		}, 
    		function(xhr) {
    			// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
    		}
		);
    	// Prevent Default
    	return false;
    }
    /*************************************/
    /*               Invitation          */
    /*************************************/
    /**
     * @name OpenInvitation
     * @abstract Method to open modal invitation
     */
    var OpenInvitation = function() {
    	$(Attr.UI.ModalInvitation).modal("show");
    }
    /**
     * @name EnableEmission
     * @abstract Method to enable disable emission controls
     */
    var EnableEmission = function(enable) {
    	$(Attr.UI.EmissionForm).find("input").prop('readonly', !enable);
    	$(Attr.UI.EmissionForm).find("button").prop('disabled', !enable);
    }
    /**
     * @name EnableCompetitor
     * @abstract Method to enable disable competitor controls
     */
    var EnableCompetitor = function(enable) {
    	$(Attr.UI.CompetitorSelect).prop('disabled',!enable);
    	$(Attr.UI.CompetitorAdd).prop('disabled', !enable)
    }
    /**
     * @name EmissionParseDate
     * @creation Method to parse date
     */
    var EmissionParseDate = function(evt, date) {
    	// Define hide function
    	var hide = function() { ISpinner.Hide(Attr.UI.ModalInvitation + ' .modal-dialog'); };
    	// Show spinner
    	ISpinner.Show(Attr.UI.ModalInvitation + ' .modal-dialog');
    	// Parse date to text
    	DateParserService.ShortDate(date.format("YYYY-MM-DD")).then(
			function (response) {
				// Set date text
				$(Attr.UI.EmissionDateText).val(response);
				// Hide spinner
                hide();
			},
			function (xhr) {
				// Show error
                Elysium.Directives.RequestError.ThrowXhr(xhr);
                // Hide spinner
                hide();
			}
		);
    }
    /**
     * @name OnSuccessEmission
     * @abstract Event fired when emission form validation is ok
     */
    var OnSuccessEmission = function() {
    	// Get adjudication information
        var emission = EmissionForm.GetValues();
        // Set date format
        emission.emissionDate = moment(emission.emissionDate, 'DD-MM-YYYY').format('YYYY-MM-DDTHH:mm:ss');
        emission['adjudicationId'] = GLOBAL_ADJUDICATION.adjudicationId;
        emission['emissionDateText'] = $(Attr.UI.EmissionDateText).val();
        // Validate days
        EmissionService.ValidateDays(GLOBAL_ADJUDICATION.sourceOrigin, emission.daysForPayment).then(
        	function(response) {
        		var message = "¿Desea guardar los datos de emision?";
    			if(response != "")
					message = response;
    			// Show message dialog
    	        Elysium.UI.Entities.MsgBox.DialogQuestion(
    	            'Guardar emisión',
    	            message,
    	            function () {
    	            	// Send information to server
    	            	EmissionService.Save(emission).then(
    	                    function () {
    	                    	// Close message
    	                    	Elysium.UI.Entities.MsgBox.Close();
    	                        // Show success information
    	                        Elysium.UI.Entities.Notification.Success({ text: "Los datos de emisión se han almacenado correctamente", time: Elysium.NotificationTime });
    	                        // Reset parsley
    	                        EmissionParsley.reset();
    	                        // if all was ok disable the elements of emission and enable the elements of competitors
    	                    	EnableEmission(false);
    	                    	EnableCompetitor(true);
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
        	},
        	function(xhr){
        		// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
        	}
    	);
        return false;
    }
    /**
     * @name GetSuppliers
     * @abstract Method to get suppliers
     */
    var GetSuppliers = function() {
    	// Define promise
    	var deferred = new $.Deferred();
    	// request data
    	SupplierService.Get().then(
			function(suppliers) {
				// Set competitor on select
				CompetitorSuppliers = suppliers;
				// filter from element in table to 
				var selectedSuppliers = ISupplierDataTable.GetData();
				// unselect suppliers
				var unselectSuppliers = [];
				suppliers.forEach(function(supplier, index, array) {
					var flag = true;
					for(var i = 0; i < selectedSuppliers.length; i++) {
						if(selectedSuppliers[i].supplierId == supplier.supplierId) {
							flag = false;
							break;
						}
					}
					if(flag)
						unselectSuppliers.push(supplier);
				});
	    		// Set on select
				SetDataOnSelect(Attr.UI.CompetitorSelect, "supplierId", "name", "state", "Seleccionar proveedor", unselectSuppliers);
				// resolve promise
				deferred.resolve();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
			}
		);
    	// return promise
    	return deferred.promise();
    }
    /**
     * @name RefreshSuppliers
     * @abstract Method to refresh suppliers
     */
    var RefreshSuppliers =  function() {
    	var hide = function() { ISpinner.Hide(Attr.UI.ModalInvitation + ' .modal-dialog');  };
    	ISpinner.Show(Attr.UI.ModalInvitation + ' .modal-dialog');
    	GetSuppliers().then(hide, hide);
    }
    /**
     * @name SetSupplierData
     * @abstract Method to set supplier data
     */
    var SetSupplierData = function() {
    	// Get selected supplier id
    	var supplierId = $(Attr.UI.CompetitorSelect).val();
    	// Check if vall is correct
    	if(supplierId != "Seleccionar proveedor") {
	    	// Loop the object in current collection
	    	var supplier = null;
	    	for(var i = 0; i < CompetitorSuppliers.length; i++) {
	    		if(CompetitorSuppliers[i].supplierId == supplierId) {
	    			supplier = CompetitorSuppliers[i]; 
	    			break;
	    		}	
	    	}
	    	$(Attr.UI.CompetitorForm).find("#address").val(supplier.address);
	    	$(Attr.UI.CompetitorForm).find("#state").val(supplier.state);
			$(Attr.UI.CompetitorForm).find("#city").val(supplier.city);
			$(Attr.UI.CompetitorForm).find("#zipCode").val(supplier.zipCode);
    	}
    	else {
    		$(Attr.UI.CompetitorForm).find("#address").val('');
	    	$(Attr.UI.CompetitorForm).find("#state").val('');
			$(Attr.UI.CompetitorForm).find("#city").val('');
			$(Attr.UI.CompetitorForm).find("#zipCode").val('');
    	}
    }
    /**
     * @name OnSuccessCompetitor
     * @abstract Event fired when competitor form valdiation is ok
     */
    var OnSuccessCompetitor = function() { 
    	// Get selected supplier id
    	var supplierId = $(Attr.UI.CompetitorSelect).val();
    	// Loop the object in current collection
    	var supplier = null;
    	for(var i = 0; i < CompetitorSuppliers.length; i++) {
    		if(CompetitorSuppliers[i].supplierId == supplierId) {
    			supplier = CompetitorSuppliers[i]; 
    			break;
    		}	
    	}
    	// With supplier get the trade number
    	supplier["tradeNumber"] = $(Attr.UI.CompetitorTradeNumber).val().trim();
    	// Create competitos
    	var competitor = {
    		adjudicationId : GLOBAL_ADJUDICATION.adjudicationId,
    		supplierId : supplier.supplierId,
    		tradeNumber : supplier.tradeNumber
    	}	
    	var competitorCreationParams = {
    		adjudicationId: GLOBAL_ADJUDICATION.adjudicationId,
    		competitors: [competitor]
    	}
		// Show message dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Guardar competidor',
            '¿Desea guardar los datos del competidor seleccionado?',
            function () {
            	// Send information to server
            	CompetitorService.Save(competitorCreationParams).then(
                    function () {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "Los datos del competidor se han almacenado correctamente", time: Elysium.NotificationTime });
                        // Get data of table
                    	var data = ISupplierDataTable.GetData();
                    	// Add supplier to data
                    	data.push(supplier);
                    	// Set data
                    	ISupplierDataTable.SetData(data);
                    	// Reset parsley
                    	CompetitorParsley.reset();
                    	// Clean form
                    	$(Attr.UI.CompetitorForm).find("#address").val('');
                    	$(Attr.UI.CompetitorForm).find("#state").val('');
                		$(Attr.UI.CompetitorForm).find("#city").val('');
                		$(Attr.UI.CompetitorForm).find("#zipCode").val('');
                		$(Attr.UI.CompetitorForm).find("#tradeNumber").val('');
                    	// Refresh suppliers
                    	RefreshSuppliers();
                    	// Enable invitation button
                    	$(Attr.UI.InvitationCreateBtn).prop('disabled', false);
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
    	// Prevent default
    	return false;
    }
    /**
     * @name CreateInvitation
     * @abstract Event fired to create invitation
     */
    var CreateInvitation = function() {
    	 // Show message dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Crear invitación',
            '¿Desea proceder con la creación de la invitación?',
            function () {
            	// Send information to server
            	InvitationService.Create(GLOBAL_ADJUDICATION.adjudicationId).then(            	
                    function (fileNames) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "La invitación se ha creado correctamente", time: Elysium.NotificationTime });
                        // Loop in collection to download file
                        fileNames.forEach(function(element, index, array) {
                        	InvitationService.Download(element);
                        });
                        // Close modal
                        $(Attr.UI.ModalInvitation).modal("hide");
                        // Disable button to show modal and enable second button
                        $(Attr.UI.BtnInvitation).prop('disabled', true);
                    	$(Attr.UI.BtnAppend).prop('disabled', false);
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
    /*************************************/
    /*                Annex             */
    /*************************************/
    /**
     * @name OpenAppend
     * @abstract Method to open modal append
     */
    var OpenAppend = function() {
    	$(Attr.UI.ModalAppend).modal("show");    	
    }
    /**
     * @name GetAssets
     * @abstract Method to get assets for annex
     */
    var GetAssets = function() {
    	// Define promise
    	var deferred = new $.Deferred();
    	// request data
    	AssetService.Get().then(
			function(assets) {
				// Define array of new objects
				var annexData = [];
				// Loop in assets to create new objects
				assets.forEach(function(element, index, array) {
					annexData.push({
						asset: element,
						annex: {
							assetId: element.assetId,
							quantity: 0,
							deliveryTerm: '',
							deliveryPlace: ''
						}
					});
				});
				// Set data on table 
				IAppendDataTable.SetData(annexData);
				// resolve promise
				deferred.resolve();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
			}
		);
    	// return promise
    	return deferred.promise();
    }
    /**
     * @name SetInputFocus
     * @abstract Event fired on focus on cell
     */
    var SetInputFocus = function(e, datatable, cell) {
    	$(Attr.UI.AppendTable).find(".focus").find("input").select();
    	$(Attr.UI.AppendTable).find(".focus").find("input").focus();
    	LastCellOnFocus = $(Attr.UI.AppendTable).find(".focus");
    	LastCellOnFocusCell = cell;
    }
    /**
     * @name EnableInputs
     * @asbtract Event fired when checkbox state change
     */
    var EnableInputs = function(object, row, data, event) {
    	if($(object).is(":checked"))
    		$(object).parent().parent().find("input[type=text]").prop("disabled", false);
    	else
    		$(object).parent().parent().find("input[type=text]").prop("disabled", true);
    }
    /**
     * @name CopyDeliveryTerm
     * @abstract Method to copy delivery term to enable cells
     */
    var CopyDeliveryTerm = function() {
    	// Check if we have a selected cell
    	if(LastCellOnFocus == null)
    		return;
    	// Check if is enabled 
    	if(!$(LastCellOnFocus).parent().find("[data-dt-checkbox-child]").is(":checked"))
    		return;
    	// Get value of place input
    	var deliveryTerm = $(LastCellOnFocus).parent().find("[data-adjudication-annex-deliveryterm]").val().trim();
    	// Get dom rows
    	var domRows = IAppendDataTable.GetDomRows();
		for(var i = 0; i < domRows.length; i++) {
			// Get dom row
			var domRow = domRows[i];
			// get row data from dom row
			var row = IAppendDataTable.GetRow(domRow);
			// Check if row is enable
			if($(row.node()).find("[data-dt-checkbox-child]").is(":checked")){
				// if row is checked, set the delivery term on data an input
				row.data().annex.deliveryTerm = deliveryTerm;
				// set on row ndoe
				$(row.node()).find("[data-adjudication-annex-deliveryterm]").val(deliveryTerm);
			}
		}
    	// Set focus again on cell
    	LastCellOnFocusCell.focus();
    }
    /**
     * @name CopyDeliveryPlace
     * @abstract Method to copy delivery place to enable cells
     */
    var CopyDeliveryPlace = function() {
    	// Check if we have a selected cell
    	if(LastCellOnFocus == null)
    		return;
    	// Check if is enabled 
    	if(!$(LastCellOnFocus).parent().find("[data-dt-checkbox-child]").is(":checked"))
    		return;
    	// Get value of place input
    	var deliveryPlace = $(LastCellOnFocus).parent().find("[data-adjudication-annex-deliveryplace]").val().trim();
    	// Get dom rows
    	var domRows = IAppendDataTable.GetDomRows();
		for(var i = 0; i < domRows.length; i++) {
			// Get dom row
			var domRow = domRows[i];
			// get row data from dom row
			var row = IAppendDataTable.GetRow(domRow);
			// Check if row is enable
			if($(row.node()).find("[data-dt-checkbox-child]").is(":checked")){
				// if row is checked, set the delivery term on data an input
				row.data().annex.deliveryPlace = deliveryPlace;
				// set on row ndoe
				$(row.node()).find("[data-adjudication-annex-deliveryplace]").val(deliveryPlace);
			}
		}
    	// Set focus again on cell
    	LastCellOnFocusCell.focus();
    }
    /**
     * @name SetDataOnAnnex
     * @abstract Method to set data on annex when cell loss focus
     */
    var SetDataOnAnnex = function(e, datatable, cell) {
    	// from cell get node and check if row is enabled
    	if($(cell.node()).parent().find("[data-dt-checkbox-child]").is(":checked")) {
    		// if node is checked get row and then data
    		var data = cell.row(cell.node()).data();
    		// Set data
    		data.annex.quantity = $(cell.node()).parent().find("[data-adjudication-annex-quantity]").val().trim();
    		data.annex.deliveryTerm = $(cell.node()).parent().find("[data-adjudication-annex-deliveryterm]").val().trim();
    		data.annex.deliveryPlace = $(cell.node()).parent().find("[data-adjudication-annex-deliveryplace]").val().trim();	
    	}
    }
    /**
     * @name ValidateData
     * @asbtract Method to validate data
     */
    var ValidateData = function(annexOutputs) {
    	// Loop in rows
    	for(var i = 0; i < annexOutputs.length; i++) {
    		// Get annex
    		var annexOutput = annexOutputs[i];
    		// Check if quantity is valid
    		if(!isNaN(parseFloat(annexOutput.annex.quantity)) && isFinite(annexOutput.annex.quantity)) {
    			if(Number(annexOutput.annex.quantity) > 0 && Number(annexOutput.annex.quantity) < 2000000000) {
    				// if quantity is ok check the delivery place
    				if(annexOutput.annex.deliveryTerm != "" ){
    					if(annexOutput.annex.deliveryTerm.length < 250) {
    						// check delivery place
    						if(annexOutput.annex.deliveryPlace != "" ) {
    							if(annexOutput.annex.deliveryPlace.length > 250) { 
    								Elysium.UI.Entities.Notification.Warning({ text: 'El bien seleccionado: ' + annexOutput.asset.name + ', contiene lugar de entrega con una longitud mayor a la permitida (menor a 250 caracteres)' , time: Elysium.NotificationTime });
    	                			return false;
    							}
    						}
    						else {
    							Elysium.UI.Entities.Notification.Warning({ text: 'El bien seleccionado: ' + annexOutput.asset.name + ', contiene lugar de entrega vacio' , time: Elysium.NotificationTime });
    	            			return false;
    						}
    					}
    					else {
    						Elysium.UI.Entities.Notification.Warning({ text: 'El bien seleccionado: ' + annexOutput.asset.name + ', contiene plazo de entrega con una longitud mayor a la permitida (menor a 250 caracteres)' , time: Elysium.NotificationTime });
                			return false;
    					}
    				}
    				else {
    					Elysium.UI.Entities.Notification.Warning({ text: 'El bien seleccionado: ' + annexOutput.asset.name + ', contiene plazo de entrega vacio' , time: Elysium.NotificationTime });
            			return false;
    				} 
    			}
    			else {
    				Elysium.UI.Entities.Notification.Warning({ text: 'El bien seleccionado: ' + annexOutput.asset.name + ', contiene una cantidad fuera del rango permitido (1 a 2000000000)' , time: Elysium.NotificationTime });
        			return false;
    			}	
    		}
    		else {
    			Elysium.UI.Entities.Notification.Warning({ text: 'El bien seleccionado: ' + annexOutput.asset.name + ', contiene una cantidad incorrecta' , time: Elysium.NotificationTime });
    			return false;
    		}
    	}
    	return true;
    }
    /**
     * @name CreateAnnex
     * @abstract Method to create annex
     */
    var CreateAnnex = function() {
    	// Get selected rows
    	var annexOutput = IAppendDataTable.GetSelectedData();
    	//Check if we have selected rows
    	if(annexOutput.length > 0) {
    		// if we have elements, its necessary validate that data is ok
    		if(ValidateData(annexOutput)) {
    			// Show confirmation dialog
    	        Elysium.UI.Entities.MsgBox.DialogQuestion(
    	            'Crear annexo',
    	            '¿Desea proceder con la creación del anexo?',
    	            function () {    	            	
    	            	var annexs = [];
    	            	annexOutput.forEach(function(element, index, array) {
    	            		annexs.push(element.annex);
    	            	});
    	            	var param = {
    	            		adjudicationId: GLOBAL_ADJUDICATION.adjudicationId,
    	            		annexs: annexs
    	            	};
    	            	// Send information to server
    	            	AnnexService.Create(param).then(            	
    	                    function (fileName) {
    	                    	// Close message
    	                    	Elysium.UI.Entities.MsgBox.Close();
    	                        // Show success information
    	                        Elysium.UI.Entities.Notification.Success({ text: "El anexo se ha creado correctamente", time: Elysium.NotificationTime });
    	                        // Download file
	                        	AnnexService.Download(fileName);
    	                        // Close modal
    	                        $(Attr.UI.ModalAppend).modal("hide");
    	                        // Disable button to show modal and enable second button
    	                        $(Attr.UI.BtnAppend).prop('disabled', true);
    	                    	$(Attr.UI.BtnTechnicalAperture).prop('disabled', false);
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
    	}
    	else 
    		Elysium.UI.Entities.Notification.Warning({ text: "No ha seleccionado algún bien para crear el anexo a la adjudicación", time: Elysium.NotificationTime });
    }
    
    
    /**
     * @name HandlerProgressUpload
     * @abstract Method to handle the process of file upload
     */
    var HandlerProgressUpload = function (e) { }
    /**
     * Method to check if is a valid file
     */
    var ValidFile = function (type) {
       // switch (type) {
       //     case 'application/vnd.ms-excel': return true;
       //     case 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet': return true;
       //     default: return false;
       // }
    	return true;
    }
    /**
     * @name SizeFile
     * @abstract Method to check the size of the file
     */
    var SizeFile = function (size) {
        return size > Elysium.FileSize;
    }
    /**
     * @name UploadFile
     * @abstract Method to upload file
     */
    var UploadFile = function () {
        // 1. Check if the input have a selected file
        var file = $(Attr.UI.AnnexImportInput).get(0).files[0];
        if (typeof file == "undefined") {
        	Elysium.UI.Entities.Notification.Warning({ text: "Debe Seleccionar un archivo", time: Elysium.MsgTime })
            return;
        }
        // 2. Check if the file is valid
        if (!ValidFile(file.type)) {
        	Elysium.UI.Entities.Notification.Warning({ text: "La extension del archivo es invalida", time: Elysium.MsgTime })
            return;
        }
        // 3. Check the size
        if (SizeFile(file.size)) {
        	Elysium.UI.Entities.Notification.Warning({ text: "El tamaño del archivo debe ser menor o igual a 15Mb", time: Elysium.MsgTime })
            return;
        }
        // Create form data to send
        var formData = new FormData();
        formData.append('file', file);
        // Show confirmation dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            "Importar archivo",
            "¿Desea cargar este archivo?",
            function (resolve, reject) {
                AnnexService.Upload(formData, HandlerProgressUpload).then(
                    // On Success
                    function (data) {
                    	// after import file, its necessary loop in data
                    	data.forEach(function (annexOutput, index, array) {
                    		// Loop in table elements to look for elements and set informaction
                    		var domRows = IAppendDataTable.GetDomRows();
                    		for(var i = 0; i < domRows.length; i++) {
                    			// Get dom row
                    			var domRow = domRows[i];
                    			// get row data from dom row
                    			var row = IAppendDataTable.GetRow(domRow);
                    			// Compare to see if is same asste
                    			if(annexOutput.asset.assetId == row.data().asset.assetId) {
                    				// if is the same, assign data to object
                    				var dataRow = row.data();
                    				dataRow.annex.quantity = annexOutput.annex.quantity;
                    				// Check check box
                    				$(row.node()).find("[data-dt-checkbox-child]").prop("checked", true);
                    				// Add selected class
                    				$(row.node()).removeClass("selectedrow").addClass("selectedrow");
                    				// Enable input
                    				$(row.node()).find("input[type=text]").prop("disabled", false);
                    				// Set quantity on input
                    				$(row.node()).find("[data-adjudication-annex-quantity]").val(annexOutput.annex.quantity);
                    				// Break loop
                    				break;
                    			}
                    		}
                    	});
                    	// Clean Input
                    	CleanInput();
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                    	// Show success message
                    	Elysium.UI.Entities.Notification.Success({ text: "El archivo se ha procesado correctamente", time: Elysium.NotificationTime });
                    	
                    },
                    // On Error
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
     * Event fired when some file is selected
     */
    var SetFileName = function () {
        // Check if we have file
        if (event.target.files.length > 0) {
            // Read input file
            var file = event.target.files[0];
            $(Attr.UI.AnnexImportLabel).text(file.name);
        }
        else 
            $(Attr.UI.AnnexImportLabel).text("Seleccionar archivo");
    }
    /**
     * @name CleanInput
     * @abstract Event fired when modal close
     */
    var CleanInput = function() {
    	$(Attr.UI.AnnexImportInput).val('');
    	$(Attr.UI.AnnexImportLabel).text("Seleccionar archivo");
    }
    /*************************************/
    /*         Technical Aperture        */
    /*************************************/
    /**
     * @name OpendTechnicalAperture
     * @abstract Method to open modal technical paerture
     */
    var OpendTechnicalAperture = function() {
    	$(Attr.UI.ModalTechnicalAperture).modal("show");    	
    }
    /**
     * @name parseDate
     * @abstract Method to parse date string
     */
    var ParseDate = function(date, inputSelector, spinnerSelector, callback) {
    	// Define hide function
    	var hide = function() { ISpinner.Hide(spinnerSelector); };
    	// Show spinner
    	ISpinner.Show(spinnerSelector);
    	// Parse date to text
    	DateParserService.ShortDate(date.format("YYYY-MM-DD")).then(
			function (response) {
				// Set date text
				$(inputSelector).val(response);
				// execute callback
				if(typeof callback != "undefined")
					callback();
				// Hide spinner
                hide();
			},
			function (xhr) {
				// Show error
                Elysium.Directives.RequestError.ThrowXhr(xhr);
                // Hide spinner
                hide();
			}
		); 
    }
    /**
     * @name ParseTime
     * @abstract Method to parse time
     */
    var ParseTime = function(date, inputSelector, spinnerSelector) {
    	// Define hide function
    	var hide = function() { ISpinner.Hide(spinnerSelector); };
    	// Show spinner
    	ISpinner.Show(spinnerSelector);
    	// Parse date to text
    	DateParserService.Time(date.format("HH:mm")).then(
			function (response) {
				// Set date text
				$(inputSelector).val(response);
				// Hide spinner
                hide();
			},
			function (xhr) {
				// Show error
                Elysium.Directives.RequestError.ThrowXhr(xhr);
                // Hide spinner
                hide();
			}
		); 
    }
    /**
     * @name OpeningEventDateDatePickerChange
     * @abstract Event fired when datetime picker change
     */
    var OpeningEventDateDatePickerChange = function(evt, date) {
    	ParseDate(date, Attr.UI.OpeningEventDateText, Attr.UI.ModalTechnicalAperture + ' .modal-dialog');
    }
    /**
     * @name OpeningEventStartHourTimePickerChange
     * @abstract Event fired when time picker change
     */
    var OpeningEventStartHourTimePickerChange = function(evt, date) {
    	ParseTime(date, Attr.UI.OpeningEventStartHourText, Attr.UI.ModalTechnicalAperture + ' .modal-dialog')
    }
    /**
     * @name OpeningEventEndHourTimePickerChange
     * @abstract Event fired when time picked change
     */
    var OpeningEventEndHourTimePickerChange = function(evt, date) {
    	ParseTime(date, Attr.UI.OpeningEventEndHourText, Attr.UI.ModalTechnicalAperture + ' .modal-dialog')
    } 
    /**
     * @name OpeningFailureIssuanceDateDatePickerChange
     * @abstract Event fired when date picker change
     */
    var OpeningFailureIssuanceDateDatePickerChange = function(evt, date) {
    	ParseDate(date, Attr.UI.OpeningFailureIssuanceDateText, Attr.UI.ModalTechnicalAperture + ' .modal-dialog');
    } 
    /**
     * @name OpeningFailureIssuanceHourTimePickerChange
     * @abstract Event fired when time picker change
     */
    var OpeningFailureIssuanceHourTimePickerChange = function(evt, date) {
    	ParseTime(date, Attr.UI.OpeningFailureIssuanceHourText, Attr.UI.ModalTechnicalAperture + ' .modal-dialog');
    }  
    /**
     * @name GetCompetitors
     * @abstract Method to get competitors
     */
    var GetCompetitors = function(e) {
    	// Define hide function
    	var hide = function() { ISpinner.Hide(Attr.UI.ModalTechnicalAperture + ' .modal-dialog'); };
    	// Show spinner
    	ISpinner.Show(Attr.UI.ModalTechnicalAperture + ' .modal-dialog');
    	// request data
    	CompetitorService.GetByAdjudicationId(GLOBAL_ADJUDICATION.adjudicationId).then(
			function(competitors) {
				// Define suppliers collection
				var competitorSuppliers = [];
				// loop to define collection
				competitors.forEach(function(element, index, array){
					competitorSuppliers.push(element.supplier);
				});
				// Set on select
				SetDataOnSelect(Attr.UI.OpeningSupplierId, "supplierId", "name", "state", "Seleccionar proveedor", competitorSuppliers);
				// Hide spinner
	            hide();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            // Hide spinner
	            hide();
			}
		);
    }
    /**
     * @name OnSuccessOpening
     * @abstract Event fired when validation success
     */
    var OnSuccessOpening = function() {
    	// Get data
    	var opening = OpeningForm.GetValues();
    	opening['adjudicationId'] = GLOBAL_ADJUDICATION.adjudicationId;
    	opening['eventDate'] =moment(opening['eventDate'], 'DD-MM-YYYY').format('YYYY-MM-DDTHH:mm:ss');
    	opening['eventDateText'] = $(Attr.UI.OpeningEventDateText).val().trim();
    	opening['eventStartHourText'] = $(Attr.UI.OpeningEventStartHourText).val().trim();
		opening['eventEndHourText'] = $(Attr.UI.OpeningEventEndHourText).val().trim();
		opening['failureIssuanceDate'] =moment(opening['failureIssuanceDate'], 'DD-MM-YYYY').format('YYYY-MM-DDTHH:mm:ss');
		opening['failureIssuanceDateText'] = $(Attr.UI.OpeningFailureIssuanceDateText).val().trim();
		opening['failureIssuanceHourText'] = $(Attr.UI.OpeningFailureIssuanceHourText).val().trim();
		// Show confirmation dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Crear apertura técnica económica',
            '¿Desea proceder con la creación de la apertura técnica económica?',
            function () {    	            	
            	// Send information to server
            	OpeningService.Save(opening).then(            	
                    function () {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "La apertura técnica económica  se ha creado correctamente", time: Elysium.NotificationTime });
                        // Close modal
                        $(Attr.UI.ModalTechnicalAperture).modal("hide");
                        // Disable button to show modal and enable second button
                        $(Attr.UI.BtnTechnicalAperture).prop('disabled', true);
                    	$(Attr.UI.BtnDetailTechnicalAperture).prop('disabled', false);
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
    /*************************************/
    /*     Detail Technical Aperture     */
    /*************************************/
    /**
     * @name OpenDetailTechnicalAperture
     * @abstract Methd to open modal OpenDetailTechnicalAperture
     */
    var OpenDetailTechnicalAperture = function() {
    	$(Attr.UI.ModalDetailTechnicalAperture ).modal("show");
    }
    /**
     * @name GetCompetitorsProposal
     * @asbtract Method to load competitors when modal show
     */    
    var GetCompetitorsProposal = function() { 
    	// Define hide function
    	var hide = function() { ISpinner.Hide(Attr.UI.ModalDetailTechnicalAperture + ' .modal-dialog'); };
    	// Show spinner
    	ISpinner.Show(Attr.UI.ModalDetailTechnicalAperture + ' .modal-dialog');
    	// request competitor data
    	CompetitorService.GetByAdjudicationId(GLOBAL_ADJUDICATION.adjudicationId).then(
			function(competitors) {
				// request annex data
				AnnexService.GetByAdjudicationId(GLOBAL_ADJUDICATION.adjudicationId).then(
					function(annexs) {
						// Loop to assign proposals
						competitors.forEach(function(element, index, array) {
							// Define proposal
							element['proposal'] = {};
							// Assign status
							element['proposal']['status'] = 'Sin modificar';
							// Assign items
							element['items'] = [];
							// loop in annex to get the data
							annexs.forEach(function(annex, index, array) {
								element['items'].push({
									annexId: annex.annex.annexId,
									unitPrice: 0,
									totalAmount: 0,
									annex: annex.annex,
									asset: annex.asset
								});
							});
						});
						// Assign data to table
						IDTA1DataTable.SetData(competitors);
						// Hide spinner
			            hide();
					},
					function(xhr){
						// Show error
			            Elysium.Directives.RequestError.ThrowXhr(xhr);
			            // Hide spinner
			            hide();
					}
				);
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            // Hide spinner
	            hide();
			}
		);
    }
    /**
     * @name SetProposalFocus
     * @abstract Method to set proposal on focus
     */
    var SetProposalFocus = function(e, datatable, cell) {
    	// Set focus on element
    	if($(cell.node()).children().length > 0) {
    		$(cell.node()).children().select();
    		$(cell.node()).children().focus();
    	}
    	// Remove selected
    	$(cell.node()).parent().parent().find('.selected').removeClass('selected');
    	// Set selected
    	$(cell.node()).parent().addClass('selected');
    	// trigger change to put items in table below or not :p
    	$(cell.node()).parent().find("[data-adjudication-proposal-technicalsubmitted]").trigger('change');
    	// Store the last cell on focus to put the total
    	LastProposalCellOnFocus = cell; 
    }
    /**
     * @name SetProposalData
     * @abstract Method to set proposal data
     */
    var SetProposalData = function(e, datatable, cell) {
    	// if node is checked get row and then data
		var data = cell.row(cell.node()).data();
		// Set data
		data.proposal['technicalSubmitted'] = $(cell.node()).parent().find("[data-adjudication-proposal-technicalsubmitted]").val();
		data.proposal['technicalRemark'] = $(cell.node()).parent().find("[data-adjudication-proposal-technicalremark]").val().trim();
		data.proposal['economicSubmitted'] = $(cell.node()).parent().find("[data-adjudication-proposal-economicsubmitted]").val();
		data.proposal['economicRemark'] = $(cell.node()).parent().find("[data-adjudication-proposal-economicremark]").val().trim();
    }
    /**
     * @name ValidateProposalStatus
     * @abstract Method to validate status
     */
    var ValidateProposalStatus = function(row, data) {
    	// Check if both are with correct values
    	if(data.proposal.technicalSubmitted == 'Si Presentó' && data.proposal.economicSubmitted == 'Si Presentó') {
    		// Change status of row
    		data.proposal.status = 'Cargar precios unitarios';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Cargar precios unitarios');
    		// Check if proposal is complete
    		var flag = true;
    		data.items.forEach(function(element, index, array) {
    			if(element.unitPrice <= 0)
    				flag = false;
    		});
    		if(flag) {
    			data.proposal.status = 'Completa';
        		$(row.node()).find("[data-adjudication-proposal-status]").text('Completa');
    		}
    		// Load items in the table below
    		IDTA2DataTable.SetData(data.items);
    	}
    	else if(data.proposal.technicalSubmitted == 'No Presentó' && data.proposal.economicSubmitted == 'No Presentó') {
    		// Change status of row
    		data.proposal.status = 'Completada Sin Presentar';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Completada Sin Presentar');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    	else if(data.proposal.technicalSubmitted == 'Si Presentó' && data.proposal.economicSubmitted == 'No Presentó') {
    		// Change status of row
    		data.proposal.status = 'Invalida';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Invalida');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    	else if(data.proposal.technicalSubmitted == 'No Presentó' && data.proposal.economicSubmitted == 'Si Presentó') {
    		// Change status of row
    		data.proposal.status = 'Invalida';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Invalida');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    	else if(data.proposal.technicalSubmitted == 'Si Presentó' && data.proposal.economicSubmitted == 'Seleccionar...') {
    		// Change status of row
    		data.proposal.status = 'Seleccionar Propuesta Economica';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Seleccionar Propuesta Economica');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    	else if(data.proposal.technicalSubmitted == 'No Presentó' && data.proposal.economicSubmitted == 'Seleccionar...') {
    		// Change status of row
    		data.proposal.status = 'Seleccionar Propuesta Economica';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Seleccionar Propuesta Economica');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    	else if(data.proposal.technicalSubmitted == 'Seleccionar...' && data.proposal.economicSubmitted == 'Si Presentó') {
    		// Change status of row
    		data.proposal.status = 'Seleccionar Propuesta Tecnica';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Seleccionar Propuesta Tecnica');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    	else if(data.proposal.technicalSubmitted == 'Seleccionar...' && data.proposal.economicSubmitted == 'No Presentó') {
    		// Change status of row
    		data.proposal.status = 'Seleccionar Propuesta Tecnica';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Seleccionar Propuesta Tecnica');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    	else if(data.proposal.technicalSubmitted == 'Seleccionar...' && data.proposal.economicSubmitted == 'Seleccionar...') {
    		// Change status of row
    		data.proposal.status = 'Sin modificar';
    		$(row.node()).find("[data-adjudication-proposal-status]").text('Sin modificar');
    		// Load items in the table below
    		IDTA2DataTable.SetData([]);
    	}
    }
    /**
     * @name ProposalTechnicalValidation
     * @abstract Method to check if enable the table below with data
     */
    var ProposalTechnicalValidation = function(object, row, data, event) {
    	// Set data
    	data.proposal['technicalSubmitted'] = $(row.node()).find('[data-adjudication-proposal-technicalsubmitted]').val();
    	data.proposal['economicSubmitted'] = $(row.node()).find('[data-adjudication-proposal-economicsubmitted]').val();
    	// Validate proposal
    	ValidateProposalStatus(row, data);
    }
    /**
     * @name ProposalEconomicValidation
     * @abstract Method to check if enable the table below with data
     */
    var ProposalEconomicValidation = function(object, row, data, event) {
    	// Set data
    	data.proposal['technicalSubmitted'] = $(row.node()).find('[data-adjudication-proposal-technicalsubmitted]').val();
    	data.proposal['economicSubmitted'] = $(row.node()).find('[data-adjudication-proposal-economicsubmitted]').val();
    	// Validate proposal
    	ValidateProposalStatus(row, data);
    }
    /**
     * @name SetProposalItemFocus
     * @abstract Method to set focus on item box
     */
    var SetProposalItemFocus = function(e, datatable, cell) {
    	// Set focus on element
		$(cell.node()).find("input").select();
		$(cell.node()).find("input").focus();
    }
    /**
     * @name SetProposalItemData
     * @abstract Method to set data on item
     */
    var SetProposalItemData = function(e, datatable, cell) {
    	// if node is checked get row and then data
		var data = cell.row(cell.node()).data();
		// Get unit price
		var unitPrice = $(cell.node()).parent().find("[data-adjudication-proposal-item-unitprice]").val();
		// Check if is valid numner
		if(!isNaN(parseFloat(unitPrice)) && isFinite(unitPrice)) {
			if(Number(unitPrice) > 0 && Number(unitPrice) < 2000000000) {
				// Set unit price
				data.unitPrice = Number(unitPrice);
				// Set total
				data.totalAmount = data.annex.quantity * data.unitPrice;
				// Set text
				$(cell.node()).parent().find("[data-adjudication-proposal-item-total]").text('$' + (Math.round(data.totalAmount * 100) / 100).toFixed(2));
				// Calculate total global
				var total = 0;
				var flag = true;
				var items = IDTA2DataTable.GetData();
				items.forEach(function(item, index, array) {
					total = total + item.totalAmount;
					if(item.unitPrice <= 0)
						flag = false;
				});
				$(LastProposalCellOnFocus.node()).parent().find('[data-adjudication-proposal-totalamount]').text('$' + (Math.round(total * 100) / 100).toFixed(2));
				if(flag) {
					$(LastProposalCellOnFocus.node()).parent().find("[data-adjudication-proposal-status]").text('Completa');
					LastProposalCellOnFocus.rows().data().toArray()[LastProposalCellOnFocus.index().row].proposal.status = 'Completa';
				}
			}
			else {
				data.unitPrice = 0;
				$(cell.node()).parent().find("[data-adjudication-proposal-item-unitprice]").val('0');
			}
		}
		else {
			data.unitPrice = 0;
			$(cell.node()).parent().find("[data-adjudication-proposal-item-unitprice]").val('0');
		}
    }
    /**
     * @name UploadProposalFile
     * @abstract Method to upload file
     */
    var UploadProposalFile = function() {
    	// Check if are items to match
    	if(IDTA2DataTable.GetData().length <= 0) {
    		Elysium.UI.Entities.Notification.Warning({ text: "No ha seleccionado un competidor para cargar sus precios", time: Elysium.MsgTime })
    		return;
    	}
    	// 1. Check if the input have a selected file
        var file = $(Attr.UI.ProposalImportInput).get(0).files[0];
        if (typeof file == "undefined") {
        	Elysium.UI.Entities.Notification.Warning({ text: "Debe Seleccionar un archivo", time: Elysium.MsgTime })
            return;
        }
        // 2. Check if the file is valid
        if (!ValidFile(file.type)) {
        	Elysium.UI.Entities.Notification.Warning({ text: "La extension del archivo es invalida", time: Elysium.MsgTime })
            return;
        }
        // 3. Check the size
        if (SizeFile(file.size)) {
        	Elysium.UI.Entities.Notification.Warning({ text: "El tamaño del archivo debe ser menor o igual a 15Mb", time: Elysium.MsgTime })
            return;
        }
        // Create form data to send
        var formData = new FormData();
        formData.append('file', file);
        // Show confirmation dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            "Importar archivo",
            "¿Desea cargar este archivo?",
            function (resolve, reject) {
                ProposalService.Upload(GLOBAL_ADJUDICATION.adjudicationId,formData, HandlerProgressUpload).then(
                    // On Success
                    function (data) {
                    	// Get data on table
                    	var itemsData = IDTA2DataTable.GetData();
                    	var total = 0;
                    	// loop in item data 
                    	itemsData.forEach(function(item, index, array) {
                    		// loop in import data to set unit price
                    		for(var i = 0; i < data.length; i++) {
                    			if(item.annexId == data[i].item.annexId) {
                    				item.unitPrice = data[i].item.unitPrice;
                    				item.totalAmount = item.annex.quantity * item.unitPrice;
                    				total = total + item.totalAmount; 
                    				break;
                    			}
                    		}
                    	});
                    	// Set total
        				$(LastProposalCellOnFocus.node()).parent().find('[data-adjudication-proposal-totalamount]').text('$' + (Math.round(total * 100) / 100).toFixed(2));
        				// Set data again in table
                    	IDTA2DataTable.SetData(itemsData);
                    	// Check if all items are distinct of zero change status to complete
                    	var flag = true;
                    	for(var i = 0; i < itemsData.length; i++){
                    		if(itemsData[i].unitPrice <= 0) {
                    			flag = false;
                    			break;
                    		}
                		}
                    	if(flag) {
                    		$(LastProposalCellOnFocus.node()).parent().find("[data-adjudication-proposal-status]").text('Completa');
                    		LastProposalCellOnFocus.rows().data().toArray()[LastProposalCellOnFocus.index().row].proposal.status = 'Completa';
                    	}
                    	// Clean Input
                    	CleanProposalInput();
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                    	// Show success message
                    	Elysium.UI.Entities.Notification.Success({ text: "El archivo se ha procesado correctamente", time: Elysium.NotificationTime });
                    },
                    // On Error
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
     * Event fired when some file is selected
     */
    var SetProposalFileName = function () {
        // Check if we have file
        if (event.target.files.length > 0) {
            // Read input file
            var file = event.target.files[0];
            $(Attr.UI.ProposalImportText).text(file.name);
        }
        else 
            $(Attr.UI.ProposalImportText).text("Seleccionar archivo");
    }
    /**
     * @name CleanInput
     * @abstract Event fired when modal close
     */
    var CleanProposalInput = function() {
    	$(Attr.UI.ProposalImportInput).val('');
    	$(Attr.UI.ProposalImportText).text("Seleccionar archivo");
    }
    /**
     * @name SaveProposal
     * @abstract Method to save proposal
     */
    var SaveProposal = function() {
    	// Get data from proposal table 
    	var listProposal = IDTA1DataTable.GetData();
    	// Loop in list to check if all are complete or ignored
    	for(var i = 0; i < listProposal.length; i++) {
    		if(listProposal[i].proposal.status != 'Completa' &&  listProposal[i].proposal.status != 'Completada Sin Presentar') {
    			Elysium.UI.Entities.Notification.Warning({ text: "No ha completado las propuestas de los competidores", time: Elysium.MsgTime })
    			return;
    		}
    	}
    	// Loop to assign compertitor id
    	listProposal.forEach(function(element, index, array) {
    		element.proposal['competitorId'] = element.competitor.competitorId;
    	});
    	// Define params
    	var params = {
    		adjudicationId: GLOBAL_ADJUDICATION.adjudicationId,
    		proposalCreationParams: listProposal
    	}
    	// Show confirmation dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Crear Detalle de Apertura',
            '¿Desea guardar el detalle de apertura ?',
            function () {    	            	
            	// Send information to server
            	ProposalService.Create(params).then(            	
                    function (fileName) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "El detalle de apertura técnica económica  se ha creado correctamente", time: Elysium.NotificationTime });
                        // Download file
                    	ProposalService.Download(fileName);
                        // Close modal
                        $(Attr.UI.ModalDetailTechnicalAperture).modal("hide");
                        // Disable button to show modal and enable second button
                        $(Attr.UI.BtnDetailTechnicalAperture).prop('disabled', true);
                    	$(Attr.UI.BtnComparativeChart).prop('disabled', false);
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
    /*************************************/
    /*           Comparative Chart       */
    /*************************************/
    /**
     * @name OpenComparativeChart
     * @abstract Method to open modal comparative chart
     */
    var OpenComparativeChart = function() {
    	$(Attr.UI.ModalComparativeChart).modal("show");
    }
    /**
     * @name Method to validate data
     */
    var ValidateData = function(data, row) {
    	// Validate status
		if(data.quotationDetail.quotationDate == "" || data.quotationDetail.creditCondition == "" || data.quotationDetail.deliveryTerm == "") {
			data.quotationDetail.status = "Ingresar Datos";
			row.find('[data-adjudication-quotation-status]').text("Ingresar Datos");
		}
		else {
			data.quotationDetail.status = "Completa";
			row.find('[data-adjudication-quotation-status]').text("Completa");
		}
    }
    /**
     * @name QuotationDetailDatePickerChange
     * @abstract Event fired when quotation detail picker change
     */
    var QuotationDetailDatePickerChange = function(evt, date) {
    	// Define hide function
    	var hide = function() { ISpinner.Hide(Attr.UI.ModalComparativeChart + ' .modal-dialog'); };
    	// Show spinner
    	ISpinner.Show(Attr.UI.ModalComparativeChart + ' .modal-dialog');
    	// Validate date
    	QuotationService.ValidateDate({ adjudicationId : GLOBAL_ADJUDICATION.adjudicationId, quotationDate: date.format('YYYY-MM-DD') }).then(
    		function() {
    			// Hide spinner
    			hide();
    			// Parse date
    			ParseDate(date, $(evt.target).parent().parent().find("[data-adjudication-quotation-date-text]"), Attr.UI.ModalComparativeChart + ' .modal-dialog', function() {
    				// Get row and set data
        			var data = IQuotationTable.GetRow($(evt.target).parent().parent()).data();
        			data.quotationDetail.quotationDate = date.format('YYYY-MM-DD');
        			data.quotationDetail.quotationDateText = $(evt.target).parent().parent().find("[data-adjudication-quotation-date-text]").val();
        			// Validate data
        			ValidateData(data, $(evt.target).parent().parent());
    			});
    			
    		},
    		function(xhr) {
    			hide();
    			// Clean date text
    			$(evt.target).parent().parent().find("[data-adjudication-quotation-date-text]").val("");
    			// Clean input
    			$(evt.target).val("");
    			// Clean data
    			var data = IQuotationTable.GetRow($(evt.target).parent().parent()).data();
    			data.quotationDetail.quotationDate = "";
    			data.quotationDetail.quotationDateText = "";
    			ValidateData(data, $(evt.target).parent().parent());
                // Show error
                Elysium.Directives.RequestError.ThrowXhr(xhr);
    		}
    	);
    }
    /**
     * @name GetCompetitorsQuotation
     * @abstract Method to get competitor for quotation
     */
    var GetCompetitorsQuotation = function() {
    	// Define hide function
    	var hide = function() { ISpinner.Hide(Attr.UI.ModalComparativeChart + ' .modal-dialog'); };
    	// Show spinner
    	ISpinner.Show(Attr.UI.ModalComparativeChart + ' .modal-dialog');
    	// request competitor data
    	CompetitorService.GetByAdjudicationId(GLOBAL_ADJUDICATION.adjudicationId).then(
			function(competitors) {
				// loop in array to add quotation object
				competitors.forEach(function(element, index, array) {
					element['quotationDetail'] = {
						competitorId : element.competitor.competitorId,
						quotationDate: "",
						quotationDateText: "",
						creditCondition: "",
						deliveryTerm: "",
						status: "Ingresar Datos"
					};
				});
				// Set data on table
				IQuotationTable.SetData(competitors);
				// Enable date picker
				var QuotationDetailDatePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
		            selector: $("[data-adjudication-quotation-date]"),
		            options: {
		                format: 'DD-MM-YYYY',
		                lang: 'es',
		                time: false,
		                date: true
		            }
		        }), [Elysium.UI.Interfaces.IDateTimePicker]);
				QuotationDetailDatePicker.Initialize();
				QuotationDetailDatePicker.OnChangeEvt(QuotationDetailDatePickerChange);
				// hide 
				hide();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
	            // Hide spinner
	            hide();
			}
		);
    }
    /**
     * @name SetQuotationFocus
     * @abstract Method to set focus on input
     */
    var SetQuotationFocus = function(e, datatable, cell) {
    	// Set focus on element
		$(cell.node()).find("input").select();
		$(cell.node()).find("input").focus();
    }
    /**
     * @name SetQuotationData
     * @abstract Method to set quotation data
     */
    var SetQuotationData = function(e, datatable, cell) {
    	// Get data from row 
		var data = cell.row(cell.node()).data();
    	// Get row and then look for each input and get data
		if($(cell.node()).parent().find('[data-adjudication-quotation-date]').val().trim() != "")
			data.quotationDetail.quotationDate = moment($(cell.node()).parent().find('[data-adjudication-quotation-date]').val().trim(), 'DD-MM-YYYY').format('YYYY-MM-DD');
		else 
			data.quotationDetail.quotationDate = "";
		// Set other data
		data.quotationDetail.quotationDateText = $(cell.node()).parent().find('[data-adjudication-quotation-date-text]').val().trim();
		data.quotationDetail.creditCondition = $(cell.node()).parent().find('[data-adjudication-quotation-creditcondition]').val().trim();
		data.quotationDetail.deliveryTerm = $(cell.node()).parent().find('[data-adjudication-quotation-deliveryterm]').val().trim();
		// Validate data
		ValidateData(data, $(cell.node()).parent());
    }
    /**
     * @name QuotationElaborationDatePickerChange
     * @abstract Method to parse date
     */
    var QuotationElaborationDatePickerChange = function(evt, date) {
    	ParseDate(date, Attr.UI.QuotationElaborationDateText, Attr.UI.ModalComparativeChart + ' .modal-dialog');
    } 
    /**
     * @name OnSuccessQuotation
     * @abstract Event fired when form validation success
     */
    var OnSuccessQuotation = function() {
    	// Validate data on table
    	var quotationDetail = IQuotationTable.GetData();
    	// Loop to see if all data is complete
    	for(var i = 0; i < quotationDetail.length; i++) {
    		if (quotationDetail[i].quotationDetail.status != 'Completa') {
    			Elysium.UI.Entities.Notification.Warning({ text: "No ha completado la informacion en la tabla", time: Elysium.NotificationTime });
    			return false;
    		}
    	}
    	// Create params
    	var quotation = QuotationForm.GetValues();
    	quotation.adjudicationId = GLOBAL_ADJUDICATION.adjudicationId;
    	quotation.elaborationDate = moment(quotation.elaborationDate, 'DD-MM-YYYY').format('YYYY-MM-DD');
    	// Loop to get quotation details
    	var quotationDetails = [];
    	quotationDetail.forEach(function(element, index, array) {
    		quotationDetails.push(element.quotationDetail);
    	});
    	var params = {
    		quotation : quotation,
    		quotationDetails: quotationDetails
    	};
    	// Show confirmation dialog
    	Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Crear cuadro comparativo',
            '¿Desea proceder con la creación del cuadro comparativo?',
            function () {
            	// Send information to server
            	QuotationService.Create(params).then(            	
                    function (fileNames) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "El cuadro comparativo se ha creado correctamente", time: Elysium.NotificationTime });
                        // Download files
                        fileNames.forEach(function(fileName, index, array) {
                        	QuotationService.Download(fileName);	
                        });
                        // Close modal
                        $(Attr.UI.ModalComparativeChart).modal("hide");
                        // Disable button to show modal and enable second button
                        $(Attr.UI.BtnComparativeChart).prop('disabled', true);
                    	$(Attr.UI.BtnJudgment).prop('disabled', false);
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
    /*************************************/
    /*              Judgment              */
    /*************************************/
    /**
     * @name OpenJudgment
     * @abstract Method to open modal judgment
     */
    var OpenJudgment = function() {
    	$(Attr.UI.ModalJudgment).modal("show");    	
    }
    /**
     * @name JudgmentDatePickerChange
     * @abstract Method to parse date
     */
    var JudgmentDatePickerChange = function(evt, date) {
    	ParseDate(date, Attr.UI.JudgmentCloseDateText, Attr.UI.ModalJudgment + ' .modal-dialog');
    }
    /**
     * @name JudgmentHourPickerChange
     * @abstract Method to parse time
     */
    var JudgmentHourPickerChange = function(evt, date) {
    	ParseTime(date, Attr.UI.JudgmentCloseHourText, Attr.UI.ModalJudgment + ' .modal-dialog');
    }
    /**
     * @name OnSuccessJudgment
     * @abstract Method to save judgment
     */
    var OnSuccessJudgment = function() {
    	// Get data
    	var adjudicationCloseParam = {
    		adjudicationId : GLOBAL_ADJUDICATION.adjudicationId,
    		closeDate: moment($(Attr.UI.JudgmentCloseDate).val().trim() + " " + $(Attr.UI.JudgmentCloseHour).val().trim(), "DD-MM-YYYY HH:mm").format("YYYY-MM-DDTHH:mm")
    	}
    	// Check if date is valie
    	if(moment($(Attr.UI.JudgmentCloseDate).val().trim(), "DD-MM-YYYY").diff(moment(), 'days') > 20) {
    		Elysium.UI.Entities.Notification.Warning({ text: "Fecha de finalización del evento no puede ser mayor a 20 días de la fecha actual", time: Elysium.NotificationTime });
    		return false;
    	}
    	// Show confirmation dialog
    	Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Crear cuadro comparativo',
            '¿Desea proceder con la creación del cuadro comparativo?',
            function () {
            	// Send information to server
            	AdjudicationService.Close(adjudicationCloseParam).then(            	
                    function (fileNames) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "El fallo se ha creado correctamente", time: Elysium.NotificationTime });
                        // Download files
                        fileNames.forEach(function(fileName, index, array) {
                        	AdjudicationService.Download(fileName);	
                        });
                        // Close modal
                        $(Attr.UI.ModalJudgment).modal("hide");
                        // Disable button to show modal and enable second button
                        $(Attr.UI.BtnJudgment).prop('disabled', true);
                    	$(Attr.UI.BtnContract).prop('disabled', false);
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
    /*************************************/
    /*              Contract             */
    /*************************************/
    /**
     * @name OpenContract
     * @abstract Method to open modal contract
     */
    var OpenContract = function() {
    	$(Attr.UI.ModalContract).modal("show");    	
    }
    /**
     * @name ShowContractData
     * @abstract Method to show contract data form
     */
    var ShowContractData = function() {
    	$(Attr.UI.ContractSelection).hide();
    	$(Attr.UI.ContractData).show();
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
    	var hide = function() { ISpinner.Hide(Attr.UI.MainPanel);  };
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Initialize datatable
    	ISupplierDataTable.Initialize();
    	IAppendDataTable.Initialize();
    	IDTA1DataTable.Initialize();
    	IDTA2DataTable.Initialize();
    	IQuotationTable.Initialize();
    	// Block buttons from bottom
    	$(Attr.UI.BtnInvitation).prop('disabled', true);
    	$(Attr.UI.BtnAppend).prop('disabled', true);
    	$(Attr.UI.BtnTechnicalAperture).prop('disabled', true);
    	$(Attr.UI.BtnDetailTechnicalAperture).prop('disabled', true);
    	$(Attr.UI.BtnComparativeChart).prop('disabled', true);
    	//$(Attr.UI.BtnJudgment).prop('disabled', true);
    	$(Attr.UI.BtnContract).prop('disabled', true);
    	$(Attr.UI.ContractBtnGenerate).prop('disabled', true);
    	// Bind events
    	$(Attr.UI.BtnInvitation).click(OpenInvitation);
    	$(Attr.UI.BtnAppend).click(OpenAppend);
    	$(Attr.UI.BtnTechnicalAperture).click(OpendTechnicalAperture);
    	$(Attr.UI.BtnDetailTechnicalAperture).click(OpenDetailTechnicalAperture);
    	$(Attr.UI.BtnComparativeChart).click(OpenComparativeChart);
    	$(Attr.UI.BtnJudgment).click(OpenJudgment);
    	$(Attr.UI.BtnContract).click(OpenContract);    	
    	$(Attr.UI.ContractBtnGenerate).click(ShowContractData);
    	// Enable extra validators
    	ControllerValidators();
    	/************************************/
        /*            Adjudication          */
        /************************************/
    	// Form Adjustment
    	ParsleyAdjudication = $(Attr.UI.FormAdjudication).parsley().on('form:submit', OnSuccessAdjudication);
    	/************************************/
        /*             Invitation           */
        /************************************/
    	// EMISSION
    	// Create StartDate Picker
        EmissionDatePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
            selector: $(Attr.UI.EmissionDate),
            options: {
                format: 'DD-MM-YYYY',
                lang: 'es',
                minDate: moment(),
                time: false
            }
        }), [Elysium.UI.Interfaces.IDateTimePicker]);
        EmissionDatePicker.Initialize();
        EmissionDatePicker.OnChangeEvt(EmissionParseDate);
        // Initialize parsley
        EmissionParsley = $(Attr.UI.EmissionForm).parsley().on('form:submit', OnSuccessEmission);
        // Disable competitor controls
        EnableCompetitor(false);
        // Disable create invitation button
        $(Attr.UI.InvitationCreateBtn).prop('disabled', true);
        // Bind event
        $(Attr.UI.InvitationCreateBtn).click(CreateInvitation);
        // COMPETITOR
        // Initialize parsley
        CompetitorParsley = $(Attr.UI.CompetitorForm).parsley().on('form:submit', OnSuccessCompetitor);
        // Bind event
        $(Attr.UI.CompetitorSelect).change(SetSupplierData);        
        // Get suppliers
        GetSuppliers().then(
			function() {
				/************************************/
		        /*               Annex              */
		        /************************************/
				// Enable check box selection
				IAppendDataTable.EnableCheckBoxSelection();
				// Bind on focus event
				IAppendDataTable.OnCellFocus(SetInputFocus);
				IAppendDataTable.OnCellBlur(SetDataOnAnnex);
				// Bind Event
				IAppendDataTable.OnEvent("change", "[data-dt-checkbox-child]", EnableInputs)  
				// Bind create
				$(Attr.UI.AnnexCreate).click(CreateAnnex);
				// Bind click
				$(Attr.UI.AnnexCopyDeliveryTerm).click(CopyDeliveryTerm);
				$(Attr.UI.AnnexCopyDeliveryPlace).click(CopyDeliveryPlace);
				$(Attr.UI.AnnexImportButton).click(UploadFile);
	        	$(Attr.UI.AnnexImportInput).change(SetFileName);
				// Get Assets
				GetAssets().then(function(){
					/************************************/
			        /*               Opening            */
			        /************************************/
					// Event date picker
					var OpeningEventDateDatePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.OpeningEventDate),
			            options: {
			                format: 'DD-MM-YYYY',
			                lang: 'es',
			                minDate: moment(),
			                time: false,
			                date: true
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					OpeningEventDateDatePicker.Initialize();
					OpeningEventDateDatePicker.OnChangeEvt(OpeningEventDateDatePickerChange);
					// Event start date picker
					var OpeningEventStartHourTimePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.OpeningEventStartHour),
			            options: {
			                format: 'HH:mm',
			                lang: 'es',
			                time: true,
			                date: false
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					OpeningEventStartHourTimePicker.Initialize();
					OpeningEventStartHourTimePicker.OnChangeEvt(OpeningEventStartHourTimePickerChange);
					// Event end date picker
					var OpeningEventEndHourTimePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.OpeningEventEndHour),
			            options: {
			                format: 'HH:mm',
			                lang: 'es',
			                time: true,
			                date: false
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					OpeningEventEndHourTimePicker.Initialize();
					OpeningEventEndHourTimePicker.OnChangeEvt(OpeningEventEndHourTimePickerChange);
					// failure issuance date picker
					var OpeningFailureIssuanceDateDatePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.OpeningFailureIssuanceDate),
			            options: {
			                format: 'DD-MM-YYYY',
			                lang: 'es',
			                minDate: moment(),
			                time: false,
			                date: true
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					OpeningFailureIssuanceDateDatePicker.Initialize();
					OpeningFailureIssuanceDateDatePicker.OnChangeEvt(OpeningFailureIssuanceDateDatePickerChange);
					
					// failure issuance hour picker
					var OpeningFailureIssuanceHourTimePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.OpeningFailureIssuanceHour),
			            options: {
			                format: 'HH:mm',
			                lang: 'es',
			                time: true,
			                date: false
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					OpeningFailureIssuanceHourTimePicker.Initialize();
					OpeningFailureIssuanceHourTimePicker.OnChangeEvt(OpeningFailureIssuanceHourTimePickerChange);
					// Bind parsley form
					OpeningParsley = $(Attr.UI.OpeningFormOpening).parsley().on('form:submit', OnSuccessOpening);
					// Load competitors when modal show
					$(Attr.UI.ModalTechnicalAperture).on('shown.bs.modal', GetCompetitors);
					/************************************/
			        /*               Proposal           */
			        /************************************/
					// Load competitors for proposal when modal show
					$(Attr.UI.ModalDetailTechnicalAperture).on('shown.bs.modal', GetCompetitorsProposal);
					// Bind events
					IDTA1DataTable.OnCellFocus(SetProposalFocus);
					IDTA1DataTable.OnCellBlur(SetProposalData);
					IDTA1DataTable.OnEvent("change", "[data-adjudication-proposal-technicalsubmitted]", ProposalTechnicalValidation);
					IDTA1DataTable.OnEvent("change", "[data-adjudication-proposal-economicsubmitted]", ProposalEconomicValidation);
					IDTA2DataTable.OnCellFocus(SetProposalItemFocus);
					IDTA2DataTable.OnCellBlur(SetProposalItemData);
					$(Attr.UI.ProposalImporButton).click(UploadProposalFile);
		        	$(Attr.UI.ProposalImportInput).change(SetProposalFileName);
		        	$(Attr.UI.ProposalSave).click(SaveProposal);
		        	/************************************/
			        /*              Quotation           */
			        /************************************/
		        	// Load competitors for quotation when modal open
					$(Attr.UI.ModalComparativeChart).on('shown.bs.modal', GetCompetitorsQuotation);
					// Enabel Parsley
					QuotationParsley = $(Attr.UI.QuotationForm).parsley().on('form:submit', OnSuccessQuotation);
					// Enable table events
					IQuotationTable.OnCellFocus(SetQuotationFocus);
					IQuotationTable.OnCellBlur(SetQuotationData);
					// Enable elaboration date picker
					var QuotationElaborationDatePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.QuotationElaborationDate),
			            options: {
			                format: 'DD-MM-YYYY',
			                lang: 'es',
			                minDate: moment(),
			                time: false,
			                date: true
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					QuotationElaborationDatePicker.Initialize();
					QuotationElaborationDatePicker.OnChangeEvt(QuotationElaborationDatePickerChange);
					/************************************/
			        /*               Judgment           */
			        /************************************/
					// Enable Parsley
					JudgmentParsley = $(Attr.UI.JudgmentForm).parsley().on('form:submit', OnSuccessJudgment);
					// Enable JudgmentDatePicker
					var JudgmentDatePicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.JudgmentCloseDate),
			            options: {
			                format: 'DD-MM-YYYY',
			                lang: 'es',
			                minDate: moment(),
			                time: false,
			                date: true
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					JudgmentDatePicker.Initialize();
					JudgmentDatePicker.OnChangeEvt(JudgmentDatePickerChange);
					// Enable JudgmentHourPicker
					var JudgmentHourPicker = Elysium.Implements(new Elysium.UI.Entities.DateTimePicker({
			            selector: $(Attr.UI.JudgmentCloseHour),
			            options: {
			                format: 'HH:mm',
			                lang: 'es',
			                time: true,
			                date: false
			            }
			        }), [Elysium.UI.Interfaces.IDateTimePicker]);
					JudgmentHourPicker.Initialize();
					JudgmentHourPicker.OnChangeEvt(JudgmentHourPickerChange);
					
					
					
					
					
					// Hide spinner
					hide();
				}, hide);
			}, hide
		);
    }
    /*******************************************************************************/
    /*                                Encapsulation                                */
    /*******************************************************************************/
    return {
        SetLocale: SetLocale,
        Initialize: Initialize
    }
    
}