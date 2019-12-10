/**
 * UserController.js Version 1.0
 * @abstract Controller for Good view
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 23/11/2018
 */
Elysium.App.Controllers.Areas.Admin.UserController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	var AdministrativeUnitService = Elysium.App.Services.Admin.AdministrativeUnitService;
	var RoleService = Elysium.App.Services.Admin.RoleService;
	var UserService = Elysium.App.Services.Admin.UserService;	
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
                        		'<button data-elysium-admin-users-modify type="button" class="btn btn-info"><span class="fas fa-edit"></span></button>' +
                        		'<button data-elysium-admin-users-password type="button" class="btn btn-info"><span class="fas fa-key"></span></button>' + 
                        		'<button data-elysium-admin-users-delete type="button" class="btn btn-danger"><span class="fas fa-trash-alt"></span></button>' +
                    	   '</div>';
                    
                }	
        	}
        	
        ],
        columns: [
        	{ data: "userData.userDataId", visible: false },
        	{ data: "userData.nickname" },
            { data: "userData.name" },
            { data: "" }
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // Form
    var IForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.FormUser), [Elysium.UI.Interfaces.IForm]);
    // Parsley
    var Parsley = null;
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
    /*            Administrive Unit                */
    /***********************************************/
    /**
     * @name GetAdministrativeUnit
     * @abstract Method to get Administrative Units
     */
    var GetAdministrativeUnits = function () {
    	// Define promise
    	var deferred = new $.Deferred();
    	// request data
    	AdministrativeUnitService.Get().then(
			function(data) {
	    		// Set on select
				SetDataOnSelect(Attr.UI.CbAdministrativeUnit, "administrativeUnitId", "code", "description", "Seleccionar ...", data);
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
    /***********************************************/
    /*                      Role                   */
    /***********************************************/
    /**
     * @name GetRoles
     * @abstract Method to get roles
     */
    var GetRoles = function () {
    	// Define promise
    	var deferred = new $.Deferred();
    	// request data
    	RoleService.Get().then(
			function(data) {
	    		// Set on select
				SetDataOnSelect(Attr.UI.CbRole, "roleId", "name", "description", "Seleccionar ...", data);
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
    /***********************************************/
    /*                    Users                    */
    /***********************************************/
    /**
     * @name GetUsers
     * @abstract Method to get roles
     */
    var GetUsers = function() { 
    	// Define promise
    	var deferred = new $.Deferred();
    	// Request data
    	UserService.Get().then(
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
     * @abstract Method to refresh data
     */
    var Refresh = function() {
    	// define hide funtion
    	var hide = function() { ISpinner.Hide(Attr.UI.MainPanel);  };
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// request data
    	GetUsers().then(function () {
    		hide();
    	}, hide);
    }
    /**
     * @name OnSuccess
     * @abstract Event fired when form validation is ok
     */
    var OnSuccess = function() {
    	// Get car information
        var user = IForm.GetValues();
        // Check operation type
        if(user.operation != "PASSWORD") {
        	// Show message dialog
            Elysium.UI.Entities.MsgBox.DialogQuestion(
                'Guardar usuario',
                '¿Desea guardar la información del usuario?',
                function () {
                	// Send information to server
                    UserService.Save(user).then(
                        function (response) {
                        	// Close message
                        	Elysium.UI.Entities.MsgBox.Close();
                            // Show success information
                            Elysium.UI.Entities.Notification.Success({ text: "El usuario se ha guardado correctamente", time: Elysium.NotificationTime });
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
        }
        else {
        	// Show message dialog
            Elysium.UI.Entities.MsgBox.DialogQuestion(
                'Cambiar contraseña',
                '¿Desea cambiar la contraseña del usuario?',
                function () {
                	// Send information to server
                    UserService.UpdatePassword(user).then(
                        function (response) {
                        	// Close message
                        	Elysium.UI.Entities.MsgBox.Close();
                            // Show success information
                            Elysium.UI.Entities.Notification.Success({ text: "La contraseña se ha actualizado correctamente", time: Elysium.NotificationTime });
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
        }
        // prevent default
        return false;
    }
    /**
     * @name OpenModal
     * @abstract Method to open modal
     */
    var New = function() {
    	// Destroy parsley
    	$(Attr.UI.FormUser).parsley().destroy();
    	// Show elements
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-create]').show();
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-create]').attr('data-parsley-required', 'true');
    	// Define operation
    	$(Attr.UI.FormOperation).val("CREATION");
    	// Creatr parsley
    	Parsley = $(Attr.UI.FormUser).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
    	// Show model
    	$(Attr.UI.Modal).modal("show");   
    }
    /**
     * @name Edit
     * @abstract Method to edit user
     */
    var Edit = function(object, row, data, event) {
    	// Set values
    	IForm.SetValues(data);
    	// Destroy parsley
    	$(Attr.UI.FormUser).parsley().destroy();
    	// Hide elements
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-create]').hide();
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-create]').attr('data-parsley-required', 'false');
    	// Show elements
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-edit]').show();
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-edit]').attr('data-parsley-required', 'true');
    	// Creatr parsley
    	Parsley = $(Attr.UI.FormUser).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
    	// Define operation
    	$(Attr.UI.FormOperation).val("EDITION");
    	// Show model
    	$(Attr.UI.Modal).modal("show");   
    }
    /**
     * @name Password
     * @abstract Method to update password
     */
    var Password = function (object, row, data, event) {
    	// Set values
    	IForm.SetValues(data);
    	// Destroy parsley
    	$(Attr.UI.FormUser).parsley().destroy();
    	// Hide elements
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-create]').hide();
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-create]').attr('data-parsley-required', 'false');
    	// Show elements
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-password]').show();
    	$(Attr.UI.FormUser).find('[data-elysium-ctrl-users-password]').attr('data-parsley-required', 'true');
    	// Clean password field
    	$(Attr.UI.FormUser).find("#userData_password").val("");
    	// Creatr parsley
    	Parsley = $(Attr.UI.FormUser).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
    	// Define operation
    	$(Attr.UI.FormOperation).val("PASSWORD");
    	// Show model
    	$(Attr.UI.Modal).modal("show");       	
    }
    /**
     * @name Delete
     * @abstract Method to delete user
     */
    var Delete = function(object, row, data, event) {
    	Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Eliminar usuario',
            i18next.t('¿Desea eliminar este usuario?'),
            function () {
            	// Send information to server
                UserService.Delete(data.userData.userDataId).then(
                    function (response) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "El usuario se ha eliminado correctamente", time: Elysium.NotificationTime });
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
    var CleanForm = function() {
    	// Reset AdminUnit
    	$(Attr.UI.FormId).val(null);
    	// Clean form
        IForm.Clean();
        // Clean pasrley
        Parsley.reset();    
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
    	// Initialize data table
    	IDataTable.Initialize();
    	// Enable Controller Validator
    	ControllerValidators();
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Get administrative unit
    	GetAdministrativeUnits().then(function() {
			// Get roles
			GetRoles().then(function() {
				// Get users
				GetUsers().then(function() {
		    		// Bind events
		        	$(Attr.UI.BtnNew).click(New)
		        	$(Attr.UI.BtnRefresh).click(Refresh)
		        	// Bind Events
		        	IDataTable.OnEvent("click", "[data-elysium-admin-users-modify]", Edit);
		        	IDataTable.OnEvent("click", "[data-elysium-admin-users-password]", Password);
		        	IDataTable.OnEvent("click", "[data-elysium-admin-users-delete]", Delete);
		        	// Modal
		        	$(Attr.UI.Modal).on('hide.bs.modal', CleanForm);
		        	// Enable parsley
		        	Parsley = $(Attr.UI.FormUser).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
		        	// Hide spinner
		        	ISpinner.Hide(Attr.UI.MainPanel); 
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