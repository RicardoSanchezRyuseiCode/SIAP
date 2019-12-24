/**
 * @name AdministrativeUnitService
 * @abstract Service class to use functionality of AdministrativeController
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-25
 */
Elysium.App.Services.Admin.AdministrativeUnitService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'admin/administrativeUnit';
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
     * @name GetByUserDataId
     * @abstract Method to get userDataId
     */
    var GetByUserDataId = function (userDataId) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByUserDataId/' + userDataId;
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
    var Save = function (administrativeUnit) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/post';
        var type = 'POST';
        if(administrativeUnit.administrativeUnitId != null) {
        	var url = Api + '/put';
            var type = 'PUT';
        }
        var objJson = JSON.stringify(administrativeUnit);
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
    var Delete = function (administrativeUnitId) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/delete/' + administrativeUnitId;
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
    	Get: Get,
    	GetByUserDataId: GetByUserDataId,
        Save: Save,
        Delete: Delete
    };
}());