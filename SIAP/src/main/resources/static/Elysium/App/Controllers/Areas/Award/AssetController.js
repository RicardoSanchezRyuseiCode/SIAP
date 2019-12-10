/**
 * AssetController.js Version 1.0
 * @abstract Controller for Good view
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 23/11/2018
 */
Elysium.App.Controllers.Areas.Award.AssetController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	var AssetService = Elysium.App.Services.Award.AssetService;
	
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
            { data: "name" }
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    // Form
    var IForm = Elysium.Implements(new Elysium.UI.Entities.Form(Attr.UI.FormAsset), [Elysium.UI.Interfaces.IForm]);
    // Parsley
    var Parsley = null;
    /*******************************************************************************/
    /*                                   Methods                                   */
    /*******************************************************************************/
    /**
     * @name GetAsset
     * @abstract Method to get Asset
     */
    var GetAsset = function() {
    	// Define promise
    	var deferred = new $.Deferred();
    	// Request data
    	AssetService.Get().then(
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
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Get Asset
    	GetAsset().then(function() {
   		    // Hide Spinner
    		hide();
    	}, hide);
    }
    /**
     * @name OnSuccess
     * @abstract Event fired when validation is ok
     */
    var OnSuccess = function() {
    	// Get information
        var good = IForm.GetValues();
        // Show message dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Guardar Bien o Servicio',
            '¿Desea guardar esta unidad?',
            function () {
            	// Send information to server
                AssetService.Save(good).then(
                    function (response) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "El bien o servicio se ha guardado correctamente", time: Elysium.NotificationTime });
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
    /**
     * @name OpenImport
     * @abstract Method to open modal for file import
     */
    var OpenImport = function() {
    	$(Attr.UI.ModalAsset).modal("show");
    }
    /**
     * @name HandlerProgressUpload
     * @abstract Method to handle the process of file upload
     */
    var HandlerProgressUpload = function (e) {
        if (e.lengthComputable) {
            ISpinner.UpdateText("#ModalAttachments .modal-dialog", "Uploading (" + Math.round((e.loaded * 100) / e.total) + "%)");
        } else {
            console.log("Hola Mundo");
        }
    }
    /**
     * Method to check if is a valid file
     */
    var ValidFile = function (type) {
        switch (type) {
            case 'application/vnd.ms-excel': return true;
            case 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet': return true;
            default: return false;
        }
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
        var file = $(Attr.UI.ImportInput).get(0).files[0];
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
            "¿Desea cargar este archivo de bienes o servicios?",
            function (resolve, reject) {
                AssetService.Upload(formData, HandlerProgressUpload).then(
                    // On Success
                    function (response) {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                    	// Close modal
                    	$(Attr.UI.ModalAsset).modal("hide");
                    	// Show success message
                    	Elysium.UI.Entities.Notification.Success({ text: "El archivo se ha procesado correctamente", time: Elysium.NotificationTime });
                    	// Refresh
                        Refresh();
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
            $(Attr.UI.ImportLabel).text(file.name);
        }
        else 
            $(Attr.UI.ImportLabel).text("Seleccionar archivo");
    }
    /**
     * @name CleanInput
     * @abstract Event fired when modal close
     */
    var CleanInput = function() {
    	$(Attr.UI.ImportInput).val('');
    	$(Attr.UI.ImportLabel).text("Seleccionar archivo");
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
    	// Show spinner
    	ISpinner.Show(Attr.UI.MainPanel);
    	// Get Asset
    	GetAsset().then(function() {
    		// Bind events
        	$(Attr.UI.BtnImport).click(OpenImport);
        	$(Attr.UI.ImportButton).click(UploadFile);
        	$(Attr.UI.ImportInput).change(SetFileName);
        	$(Attr.UI.ModalAsset).on('hide.bs.modal', CleanInput);
        	// Enable parsley
        	Parsley = $(Attr.UI.FormAsset).parsley({ excluded: "input[type=button], input[type=submit], input[type=reset], input[type=hidden], [disabled], :hidden" }).on('form:submit', OnSuccess);
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