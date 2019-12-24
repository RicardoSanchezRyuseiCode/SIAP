/**
 * @name BudgetService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Requisition.BudgetService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'requisition/budget';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name GetByAdministrativeUnitId
     * @abstract Method to get a collection of user objects
     */
    var GetByAdministrativeUnitId = function (administrativeUnitId) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByAdministrativeUnitId/' + administrativeUnitId;
        $.ajax({
            // tipo de llamado
            type: 'GET',
            // url del llamado a ajax
            url: url,
            // obtener siempre los datos mas actualizados
            cache: false,
            // tipo de datos de regreso
            dataType: "json",
            // funcion en caso de exito
            success: function (data, textStatus, jqXHR) {
                deferred.resolve(data);
            },
            // funcion en caso de error
            error: function (xhr, status, error) {
                deferred.reject(xhr);
            }
        });
        // return promise
        return deferred.promise();
    };
    /**
     * @name GetByBudgetId
     * @abstract Method to get by budget id
     * 
     */
    var GetByBudgetId = function (budgetId) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByBudgetId/' + budgetId;
        $.ajax({
            // tipo de llamado
            type: 'GET',
            // url del llamado a ajax
            url: url,
            // obtener siempre los datos mas actualizados
            cache: false,
            // tipo de datos de regreso
            dataType: "json",
            // funcion en caso de exito
            success: function (data, textStatus, jqXHR) {
                deferred.resolve(data);
            },
            // funcion en caso de error
            error: function (xhr, status, error) {
                deferred.reject(xhr);
            }
        });
        // return promise
        return deferred.promise();
    };
    /**********************************************/
    /*                 Encapsulate                */
    /**********************************************/
    return {
    	GetByAdministrativeUnitId: GetByAdministrativeUnitId,
    	GetByBudgetId: GetByBudgetId
    };
}());