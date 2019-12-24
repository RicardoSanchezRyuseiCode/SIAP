/**
 * @name AssetService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Award.AssetService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'award/asset';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name Get
     * @abstract Method to get a collection of user objects
     */
    var Get = function () {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/get';
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
     * @abstract Method to update an user
     */
    var Save = function (asset) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/post';
        var type = 'POST';
        var objJson = JSON.stringify(asset);
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
     * @name Upload
     * @abstract Method to upload a file to server
     */
    var Upload = function (formData, handlerProgress) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/upload';
        var type = 'POST';
        $.ajax({
            // tipo de llamado
            type: type,
            // url del llamado a ajax
            url: url,
            // obtener siempre los datos mas actualizados
            cache: false,
            // content type
            contentType: false,
            // process data
            processData: false,
            // progress
            xhr: function () {  // Custom XMLHttpRequest
                var myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) { // Check if upload property exists
                    myXhr.upload.addEventListener('progress', handlerProgress, false); // For handling the progress of the upload
                }
                return myXhr;
            },
            // data
            data: formData,
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
    }
    
    /**********************************************/
    /*                 Encapsulate                */
    /**********************************************/
    return {
    	Get: Get,
    	Upload: Upload,
        Save: Save
    };
}());