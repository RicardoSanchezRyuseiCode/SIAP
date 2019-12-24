/**
 * AdministrativeUnitController.js Version 1.0
 * @abstract Controller for AdministrativeUnit
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 23/11/2018
 */
Elysium.App.Controllers.Areas.Admin.AdministrativeUnitController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	var AdministrativeUnitService = Elysium.App.Services.Admin.AdministrativeUnitService;
	
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
    // Table
    var IDataTable = Elysium.Implements(new Elysium.UI.Entities.Table({
        selector: Attr.UI.DataTable,
        responsive: true,
        paging: true,
        columnDefs: [
        	
        	{
        		targets: [3],
                className: "dt-center",
                render: function (data, type, full, meta) {
                    return '<div class="btn-group">' +
                        		'<button data-elysium-admin-au-modify type="button" class="btn btn-info"><span class="fas fa-edit"></span></button>' +
                        		'<button data-elysium-admin-au-delete type="button" class="btn btn-danger"><span class="fas fa-trash-alt"></span></button>' +
                    	   '</div>';
                    
                }	
        	}
        	
        ],
        columns: [
        	{ data: "administrativeUnitId", visible: false },
        	{ data: "code" },
            { data: "description" },
            { data: "" }
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // Form
    var IForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.Form), [Elysium.UI.Interfaces.IForm]);
    // Parsley
    var Parsley = null;
    /*******************************************************************************/
    /*                                   Methods                                   */
    /*******************************************************************************/
    /**
     * @name Get
     * @abstract Method to get data
     */
    var Get = function() { 
    	var deferred = new $.Deferred();
    	AdministrativeUnitService.Get().then(
			function(data) {
				IDataTable.SetData(data);
				deferred.resolve();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
			}
		);
    	return deferred.promise();
    }
    /**
     * @name Refresh
     * @abstract Method to refresh data
     */
    var Refresh = function() {
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Get initial data
    	Get().then(function() {
    		// Hide spinner
        	ISpinner.Hide(Attr.UI.MainPanel);
    	});
    }
    /**
     * @name OpenModal
     * @abstract Method to open modal
     */
    var OpenModal = function() {
    	$(Attr.UI.Modal).modal("show");   
    }        
    /**
     * @name OnSuccess
     * @abstract Event fired when validation success
     */
    var OnSuccess = function() {
    	// Get car information
        var administrativeUnit = IForm.GetValues();
        if( $(Attr.UI.InputAuthorizer).is(":checked")) {
        	administrativeUnit['authorizer'] = 1;
        }
        else {
        	administrativeUnit['authorizer'] = 0;
        }
        // Show message dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Guardar unidad',
            '¿Desea guardar esta unidad?',
            function () {
            	// Send information to server
                AdministrativeUnitService.Save(administrativeUnit).then(
                    function (response) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "La unidad administrativa se ha guardado correctamente", time: Elysium.NotificationTime });
                        // Hide modal
                        $(Attr.UI.Modal).modal("hide");   
                        // Refresh
                        Refresh();
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
        // prevent default
        return false;
    }
    /**
     * @name Edit
     * @abstract Method to edit administrative unit
     */
    var Edit = function(object, row, data, event)
    {
    	// Set values
    	IForm.SetValues(data);
    	// Check authorizer
    	if(data.authorizer == 1) {
    		$(Attr.UI.InputAuthorizer).prop('checked', true);
    		$(Attr.UI.InputEmail).prop('disabled', false);
    	} 
    	else {
    		$(Attr.UI.InputAuthorizer).prop('checked', false);
    		$(Attr.UI.InputEmail).prop('disabled', true);
    	} 
    	// Open Modal
        OpenModal();
    }
    /**
     * @name Delete
     * @abstract Method to delete administrative unit
     */
    var Delete = function(object, row, data, event) {
    	Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Eliminar unidad',
            i18next.t('¿Desea eliminar esta unidad?'),
            function () {
            	// Send information to server
                AdministrativeUnitService.Delete(data.administrativeUnitId).then(
                    function (response) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "La unidad administrativa se ha eliminado correctamente", time: Elysium.NotificationTime });
                        // Refresh
                        Refresh();
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
     * @name CleanForm
     * @abstract Method to clean form
     */
    var CleanForm = function () {
    	// Reset AdminUnit
    	$(Attr.UI.InputAdminUnit).val(null);
    	// Clean form
        IForm.Clean();
        $(Attr.UI.InputAuthorizer).prop('checked', false);
		$(Attr.UI.InputEmail).prop('disabled', true);
		$(Attr.UI.InputEmail).val('');
        // Clean pasrley
        Parsley.reset();    	
    }
    /**
     * @name InputAuthorizerClick
     * @astract Evet fired when input authorizer click
     */
    var InputAuthorizerClick = function() {
    	// Check if is 
    	if( $(Attr.UI.InputAuthorizer).is(":checked")) {
    		// Enable input
    		$(Attr.UI.InputEmail).prop("disabled", false);
    		$(Attr.UI.InputEmail).val("");
    		// Destroy parsley 
    		$(Attr.UI.Form).parsley().destroy();
    		// Enable again
    		Parsley = $(Attr.UI.Form).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
    	}
    	else {
    		// Enable input
    		$(Attr.UI.InputEmail).prop("disabled", true);
    		$(Attr.UI.InputEmail).val("");
    		// Destroy parsley 
    		$(Attr.UI.Form).parsley().destroy();
    		// Enable again
    		Parsley = $(Attr.UI.Form).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
    	}
    	
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
    	// Initialize data table
    	IDataTable.Initialize();
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Get initial data
    	Get().then(function() {
    		// Bind events
        	$(Attr.UI.BtnNew).click(OpenModal)
        	$(Attr.UI.BtnRefresh).click(Refresh)
        	// Bind DataTable Events
        	IDataTable.OnEvent("click", "[data-elysium-admin-au-modify]", Edit);
        	IDataTable.OnEvent("click", "[data-elysium-admin-au-delete]", Delete);
        	// modal
        	$(Attr.UI.Modal).on('hide.bs.modal', CleanForm);
        	// Parsley
        	Parsley = $(Attr.UI.Form).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
        	// Bind
        	$(Attr.UI.InputAuthorizer).click(InputAuthorizerClick);
        	
        	// Hide spinner
        	ISpinner.Hide(Attr.UI.MainPanel);
    	});
    }
    /*******************************************************************************/
    /*                                Encapsulation                                */
    /*******************************************************************************/
    return {
        SetLocale: SetLocale,
        Initialize: Initialize
    }
    
}