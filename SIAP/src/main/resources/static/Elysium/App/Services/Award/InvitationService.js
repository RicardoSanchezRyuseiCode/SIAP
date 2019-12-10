/**
 * @name SupplierService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-12-01
 */
Elysium.App.Services.Award.InvitationService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = '/award/invitation';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name Save
     * @abstract Method to update an user
     */
    var Create = function (adjudicationId) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/create';
        var type = 'POST';
        var objJson = JSON.stringify(adjudicationId);
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
    /**
     * @name Download
     * @abstract Method to download files
     */
    var Download = function (fileName) {
    	window.open(Api + '/download/' + fileName, '_blank');
    }
    /**********************************************/
    /*                 Encapsulate                */
    /**********************************************/
    return {
    	Create: Create,
    	Download: Download
    };
}());