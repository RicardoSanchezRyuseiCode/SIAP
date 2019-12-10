/**
 * @name UserService
 * @abstract Service class to use functionality of UserController
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-25
 */
Elysium.App.Services.Admin.UserService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = '/admin/users';
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
    var Save = function (userData) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/post';
        var type = 'POST';
        if(userData.userData.userDataId != null) {
        	url = Api + '/put';
            type = 'PUT';
        }
        var objJson = JSON.stringify(userData);
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
     * @name UpdatePassword
     * @abstract Method to update password
     */
    var UpdatePassword = function (userData) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/updatePassword';
        var type = 'PUT';
        var objJson = JSON.stringify(userData);
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
    var Delete = function (userDataId) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/delete/' + userDataId;
        var type = 'DELETE';
        
        $.ajax({
            // tipo de llamado
            type: type,
            // url del llamado a ajax
            url: url,
            // obtener siempre los datos mas actualizados
            cache: false,
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
    	Get: Get,
        Save: Save,
        UpdatePassword: UpdatePassword,
        Delete: Delete
    };
}());