/**
 * ReprintController.js Version 1.0
 * @abstract Controller for Good view
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 23/11/2018
 */
Elysium.App.Controllers.Areas.Award.ReprintController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	var AdjudicationService = Elysium.App.Services.Award.AdjudicationService;
	var AdjudicationDocumentService = Elysium.App.Services.Award.AdjudicationDocumentService;
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
    			targets: 5,
                searchable: false,
                orderable: false,
                className: 'dt-center',
                render: function (data, type, full, meta) {
                	return	'<button class="btn btn-primary" data-elysium-reprint-print><span class="fas fa-print"></span></button>' +
                			'<button class="btn btn-danger" data-elysium-reprint-delete><span class="fas fa-times"></span></button>';
                }
    		}
        ],
        columns: [
        	{ data: "procedureNumber" },
            { data: "adjudicationType" },
            { data: "contractType" },
            { data: "sourceOrigin" },
            { data: "status" },
            { data: "" },
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    /*******************************************************************************/
    /*                                   Methods                                   */
    /*******************************************************************************/
    /**
     * @name GetFinishedData
     * @abstract Method to get pending data
     */
    var GetFinishedData = function() {
    	var deferred = new $.Deferred();
    	AdjudicationService.GetFinished().then(
			function(pendingAdjudications) {
				IDataTable.SetData(pendingAdjudications);
				deferred.resolve();
			},
			function (xhr) {
				deferred.reject(xhr);
			}
		);
    	return deferred.promise();
    }
    /**
     * @name Refresh 
     * @abstract Method to refresh 
     */
    var Refresh = function() {
    	var hide = function() {  ISpinner.Hide(Attr.UI.MainPanel);  };
    	// Get pending data
    	GetFinishedData().then(
    		hide, hide
    	);
    };
    /**
     * @name DeleteAdjudication
     * @abstract Method to delete adjudication
     */
    var DeleteAdjudication = function(object, row, data, event) {
    	// Show confirmation dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Eliminar adjudicación',
            '¿Desea eliminar la adjudicación?',
            function () {
            	// Send information to server
            	AdjudicationService.Delete(data.adjudicationId).then(
                    function () {
                    	// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "La adjudicación se ha eliminado correctamente", time: Elysium.NotificationTime });
                        // Refresh table 
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
     * @name PrintAdjudication
     * @abstract MEthod to print with documents of adjudication
     */
    var PrintAdjudication = function(object, row, data, event) {
    	// Show confirmation dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Generar documentos',
            '¿Desea generar la documentación de la adjudicación?',
            function () {
            	AdjudicationDocumentService.GetByAdjudicationId(data.adjudicationId).then(
            		function(files){
            			// Loop in files 
            			files.forEach(function(file, index, array){
            				AdjudicationDocumentService.Download(file);
            			});
            			// Close message
                    	Elysium.UI.Entities.MsgBox.Close();
                        // Show success information
                        Elysium.UI.Entities.Notification.Success({ text: "Los documentos se han generado correctamente", time: Elysium.NotificationTime });
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
    	var hide = function() {  ISpinner.Hide(Attr.UI.MainPanel);  };
    	// Initialize data table
    	IDataTable.Initialize();
    	IDataTable.OnEvent("click", "[data-elysium-reprint-delete]", DeleteAdjudication);
    	IDataTable.OnEvent("click", "[data-elysium-reprint-print]", PrintAdjudication);
    	// Get pending data
    	GetFinishedData().then(
    		hide, hide
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