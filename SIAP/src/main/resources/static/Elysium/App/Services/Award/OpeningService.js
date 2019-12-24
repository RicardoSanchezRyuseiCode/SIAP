/**
 * @name SupplierService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-12-01
 */
Elysium.App.Services.Award.OpeningService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'award/opening';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name Save
     * @abstract Method to update an user
     */
    var Save = function (opening) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/post';
        var type = 'POST';
        var objJson = JSON.stringify(opening);
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
    	Save: Save
    };
}());