/**
 * SuppliersController.js Version 1.0
 * @abstract Controller for Good view
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 23/11/2018
 */
Elysium.App.Controllers.Areas.Award.SupplierController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	var SupplierService = Elysium.App.Services.Award.SupplierService;	
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
        columnDefs: [],
        columns: [
            { data: "name" },
            { data: "address" },
            { data: "city" },
            { data: "state" },
            { data: "zipCode" },
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // Form
    var IForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.FormSupplier), [Elysium.UI.Interfaces.IForm]);
    // Parsley
    var Parsley = null;
    /*******************************************************************************/
    /*                                   Methods                                   */
    /*******************************************************************************/
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
    /**
     * @name GetSupplier
     * @abstract Method to get suppliers
     */
    var GetSuppliers = function() {
    	// Define promise
    	var deferred = new $.Deferred();
    	// Request data
    	SupplierService.Get().then(
			function(data) {
	    		// Set data on tale
				IDataTable.SetData(data);
				// resolve promise
				deferred.resolve();
			}, 
			function(xhr){
				// Show error
	            Elysium.Directives.RequestError.ThrowXhr(xhr);
			}
		);
    	// Return promise
    	return deferred.promise();		
    }
    /**
     * @name Refresh
     * @abstract Method to refresh
     */
    var Refresh = function() {
    	// Define hide functionv
    	var hide = function () {  ISpinner.Hide(Attr.UI.MainPanel); };
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Get Suppliers
    	GetSuppliers().then(function() {
   		    // Hide Spinner
    		hide();
    	}, hide);
    }
    /**
     * @name OnSuccess
     * @abstract Event fired when form validation success
     */
    var OnSuccess = function () {
    	// Get information
        var supplier = IForm.GetValues();
        // Show message dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Guardar Proveedor',
            '¿Desea guardar este proveedor?',
            function () {
            	// Send information to server
                SupplierService.Save(supplier).then(
                    function (response) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "El proveedor se ha guardado correctamente", time: Elysium.NotificationTime });
                        // Clean form
                        IForm.Clean();
                        // Clean pasrley
                        Parsley.reset();  
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
    	// Define hide functionv
    	var hide = function () {  ISpinner.Hide(Attr.UI.MainPanel); };
    	// Initialize data table
    	IDataTable.Initialize();
    	// Controller validatore
    	ControllerValidators();
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Get Suppliers
    	GetSuppliers().then(function() {
    		// Enable parsley
        	Parsley = $(Attr.UI.FormSupplier).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
    		// Hide Spinner
    		hide();
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