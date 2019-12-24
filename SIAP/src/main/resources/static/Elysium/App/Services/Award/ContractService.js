/**
 * @name ContractService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Award.ContractService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'award/contract';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name create
     * @abstract Method to update an user
     */
    var Create = function (contract) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/create';
        var type = 'POST';
        var objJson = JSON.stringify(contract);
        $.ajax({
            // tipo de llamado
            type: type,
            // url del llamado a ajax
            url: url,
            // obtener siempre los datos mas actualizados
            cache: false,
            // content type
            contentType: 'application/json; charset=utf-8',
            // data
            data: objJson,
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
    	Create: Create
    };
}());