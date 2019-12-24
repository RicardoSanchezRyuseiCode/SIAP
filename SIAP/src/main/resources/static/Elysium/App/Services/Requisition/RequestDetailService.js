/**
 * @name ChapterService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Requisition.RequestDetailService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'requisition/requestDetail';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name GetByRequestId
     * @abstract Method to get by request id
     */
    var GetByRequestId = function (requestId) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByRequestId/' + requestId;
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
     * @name GetByCode
     * @abstract Method to get by code
     */
    var GetByRequestDetailId = function (requestDetailId) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByRequestDetailId/' + requestDetailId;
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
     * @name Save
     * @abstract Method to save a request
     */
    var Save = function (requestDetail) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/post';
        var type = 'POST';
        var objJson = JSON.stringify(requestDetail);
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
     * @name Delete
     * @abstract Method to delete an user data
     */
    var Delete = function (requestDetailId) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/delete/' + requestDetailId;
        var type = 'DELETE';
        
        $.ajax({
            // tipo de llamado
            type: type,
            // url del llamado a ajax
            url: url,
            // obtener siempre los datos mas actualizados
            cache: false,
            // tipo de datos de regreso
            //dataType: "json",
            // content type
            contentType: 'application/json; charset=utf-8',
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
    	GetByRequestId: GetByRequestId,
    	GetByRequestDetailId: GetByRequestDetailId,
    	Save: Save,
    	Delete: Delete
    };
}());