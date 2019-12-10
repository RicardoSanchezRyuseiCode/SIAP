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
            { data: "ProcedureNumber" },
            { data: "Type" },
            { data: "Contract" },
            { data: "ResourceOrigen" },
            { data: "Status" },
        ]
    }), [Elysium.UI.Interfaces.ITable]);
    /*******************************************************************************/
    /*                                   Methods                                   */
    /*******************************************************************************/
    
    
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
    	IDataTable.SetData(
    			[
    				{
    					ProcedureNumber: "201563",
    					Type: "Adquisición",
    					Contract: "Contrato",
    					ResourceOrigen: "Contrato",
    					Status: "Liberado"
    				},
    				{
    					ProcedureNumber: "201564",
    					Type: "Adquisición",
    					Contract: "Contrato",
    					ResourceOrigen: "Contrato",
    					Status: "Liberado"
    				},
    				{
    					ProcedureNumber: "201565",
    					Type: "Adquisición",
    					Contract: "Contrato",
    					ResourceOrigen: "Contrato",
    					Status: "Liberado"
    				}
    			]
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