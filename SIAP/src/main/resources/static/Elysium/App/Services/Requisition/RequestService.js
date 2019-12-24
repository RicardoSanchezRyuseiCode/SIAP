/**
 * @name ChapterService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Requisition.RequestService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'requisition/request';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name GetByEntryId
     * @abstract Method to get by entry id
     */
    var GetByEntryId = function (entryId) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByEntryId/' + entryId;
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
     * @name GetByEntryId
     * @abstract Method to get by entry id
     */
    var GetByEntryIdStatus = function (entryId, status) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByEntryIdStatus/' + entryId + '/' + status;
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
    var GetByCode = function (code) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByCode/' + code;
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
     * @name GetNextCode
     * @abstract Method to get next code
     */
    var GetNextCode = function () {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getNextCode';
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
    var Save = function (request) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/post';
        var type = 'POST';
        var objJson = JSON.stringify(request);
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
     * @name Close
     * @abstract Method to close request
     */
    var Close = function (requestId) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/close';
        var type = 'POST';
        var objJson = JSON.stringify(requestId);
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
    	GetByEntryId: GetByEntryId,
    	GetByEntryIdStatus: GetByEntryIdStatus,
    	GetByRequestId: GetByRequestId,
    	GetByCode: GetByCode,
    	GetNextCode: GetNextCode,
    	Save: Save,
    	Close: Close
    };
}());