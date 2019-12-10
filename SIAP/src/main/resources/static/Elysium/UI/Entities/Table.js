/**
 * @name Table
 * @abstract Entitie to wrap the behavior of Table
 * @author Ricardo Sanchez Romero (ricardosanchezromero@outlook.es)
 * @copyright Elysium 04/08/2018
 */
Elysium.UI.Entities.Table = function (arguments) {
    /*******************************************/
    /*                 Atrributes              */
    /*******************************************/
    // Attributes
    var Attr = arguments;
    // Datatable reference
    var tableObj = null;
    // var JSON file
    var I18nJsonUrl = Elysium.AppHost +  '/Assets/I18n/DataTables/';
    /*******************************************/
    /*                 Methods                 */
    /*******************************************/
    /**
     * @name Dispose
     * @abstract Method to relase datatables resource
     */
    var Dispose = function () {
        api = $(Attr.selector).dataTable();
        api.fnDestroy();
    }
    /**
     * @name Fit
     * @abstract Method to fit elements of table after a change in display or data
     */
    var Fit = function () {
        datatable = $(Attr.selector).DataTable();
        datatable.columns.adjust().responsive.recalc();
    }
    /**
     * @name SetData
     * @abstract Method to set data of table
     * @param {collection} data
     */
    var SetData = function (data) {
        var datatable = $(Attr.selector).dataTable().api();
        datatable.clear();
        datatable.rows.add(data);
        datatable.draw();
        //Fit();
    }
    /**
     * @name GetData
     * @abstract Method to get data of table
     */
    var GetData = function () {
        var data = $(Attr.selector).dataTable();  
        return data.fnGetData();
    }
    /**
     * @name ScrollTo
     * @abstract Method to scrool 
     * @param {any} rowIndex
     */
    var ScrollTo = function (rowIndex) {
        var data = $(Attr.selector).dataTable().api();
        data.row(rowIndex).scrollTo()
    }
    
    /**
     * @name EnableCheckBoxSelection
     * @abstract Method to enable check box selection
     */
    var EnableCheckBoxSelection = function () {
        $("[data-dt-checkbox-all]").on('click', function () {
            // Get all rows with search applied
            var rows = tableObj.rows({ 'search': 'applied' }).nodes();
            // Check/uncheck checkboxes for all rows in the table
            $('[data-dt-checkbox-child]', rows).prop('checked', this.checked);
        });

        $(Attr.selector).on('change', '[data-dt-checkbox-child]', function () {
            var row = $(this).parents('tr');
            // If checkbox is not checked
            if (!this.checked) {
                row.removeClass('selectedrow');
                var el = $('[data-dt-checkbox-all]').get(0);
                // If "Select all" control is checked and has 'indeterminate' property
                if (el && el.checked && ('indeterminate' in el)) {
                    // Set visual state of "Select all" control
                    // as 'indeterminate'
                    el.indeterminate = true;
                }
            }
            else {
                row.addClass('selectedrow');
            }
        });
    }
    /**
     * @name GetSelectedData
     */
    var GetSelectedData = function () {
        var elements = tableObj.rows('.selectedrow').data();
        var length = elements.length;
        var array = [];
        for (var i = 0; i < length; i++) {
            array.push(elements[i]);
        }
        return array;
    }
    /**
     * @name GetDomRows
     * @asbtract Method to get dom row
     */
    var GetDomRows = function() {
    	return tableObj.rows().nodes().to$();
    }
    /**
     * @name GetRow
     * @abstract Method to get row
     */
    var GetRow = function(selector) {
    	return tableObj.row(selector);
    }
    /**
     * @name OnEvent
     * @abstract Method to enable an event and fire a callback
     * @param {string} event to fire 'click', 'change'
     * @param {any} selector '#dataElement','[data-element]'
     * @param {any} callback function to call when the event is fired
     */
    var OnEvent = function (event, selector, callback) {
        // Bind event
        $(Attr.selector).find('tbody').on(event, selector, function (eventFired) {
            // Get row of element
            var row = tableObj.row($(this).parents('tr'));
            // Get data of row
            var data = tableObj.row($(this).parents('tr')).data();
            // if is undefined the call comes from an autogenerador row for responsive
            if (data == undefined) {
                data = tableObj.row($(this).parents('tr').prev()).data();
            }
            // Call back
            callback(this, row, data, eventFired);
        });
    }
    /**
     * @name OnCellFocus
     * @abstract method for on focus event
     */
    var OnCellFocus = function (Handler) {
        tableObj.on('key-focus', function (e, datatable, cell) {
            Handler(e, datatable, cell);
        });
    }
    /**
     * @name OnCellBlur
     * @abstract Method
     */
    var OnCellBlur = function (Handler) {
        tableObj.on('key-blur', function (e, datatable, cell) {
            Handler(e, datatable, cell);
        });
    }
    /**
     * @name SetLocale
     * @abstract Method to set locale of table
     * @param {string} Localization string 
     */
    var SetLocale = function (strLocale) { 
        // Get data
        var currentData = GetData();
        // Dispose the table
        Dispose();
        // Change the language attribute
        Attr['language'] = {
            url: I18nJsonUrl + strLocale + '.json'
        }
        // Initialize the table again
        Initialize();
        // Set Data
        SetData(currentData);
    }
    /**
     * @name Initialize
     * @abstract Method to initialize data table
     */
    var Initialize = function () {
        tableObj = $(Attr.selector).DataTable(Attr);
    }
    /*******************************************/
    /*              Encapsulation              */
    /*******************************************/
    return {
        Initialize: Initialize,
        SetLocale: SetLocale,
        SetData: SetData,
        GetData: GetData,
        OnEvent: OnEvent,
        Fit: Fit,
        ScrollTo: ScrollTo,
        EnableCheckBoxSelection: EnableCheckBoxSelection,
        GetSelectedData: GetSelectedData,
        GetDomRows: GetDomRows,
        GetRow: GetRow,
        OnCellFocus: OnCellFocus,
        OnCellBlur: OnCellBlur
    }
}