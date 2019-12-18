/**
 * PendingController.js Version 1.0
 * @abstract Controller for Good view
 * @author Ricardo Sanchez Romero, ricardo.sanchez@ryuseicode.com
 * @copyright Elysium 24/11/2018
 */
Elysium.App.Controllers.Areas.Award.PendingController = function (arguments) {
	/*******************************************************************************/
    /*                                   Services                                  */
    /*******************************************************************************/
	var AdjudicationService = Elysium.App.Services.Award.AdjudicationService;
	
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
                	return	'<button class="btn btn-primary" data-elysium-pending-continue><span class="fas fa-play"></span></button>' +
                			'<button class="btn btn-danger" data-elysium-pending-delete><span class="fas fa-times"></span></button>';
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
     * @name GetPendingData
     * @abstract Method to get pending data
     */
    var GetPendingData = function() {
    	var deferred = new $.Deferred();
    	AdjudicationService.GetPending().then(
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
    	GetPendingData().then(
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
     * @name ContinueAdjudication
     * @abstract MEthod to continue with adjudication
     */
    var ContinueAdjudication = function(object, row, data, event) {
    	// Show confirmation dialog
        Elysium.UI.Entities.MsgBox.DialogQuestion(
            'Continue adjudicación',
            '¿Desea continuar la edición de la adjudicación?',
            function () {
            	window.location.href = "creation?adjudicationId=" + data.adjudicationId;
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
    	IDataTable.OnEvent("click", "[data-elysium-pending-delete]", DeleteAdjudication);
    	IDataTable.OnEvent("click", "[data-elysium-pending-continue]", ContinueAdjudication);
    	// Get pending data
    	GetPendingData().then(
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