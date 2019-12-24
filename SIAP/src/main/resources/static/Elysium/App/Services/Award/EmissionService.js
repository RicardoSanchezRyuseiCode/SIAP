/**
 * @name EmissionService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Award.EmissionService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'award/emission';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name Get
     * @abstract Method to get a collection of user objects
     */
    var GetByAdjudicationId = function (adjudicationId) {
        // Define promise
        var deferred = new $.Deferred();
        // Check Access        
        var url = Api + '/getByAdjudicationId/' + adjudicationId;
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
    var Save = function (emission) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/post';
        var type = 'POST';
        var objJson = JSON.stringify(emission);
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
     * @name ValidateDays
     * @abstract Method to validate days
     */
    var ValidateDays = function(sourceOrigin, daysForPayment) {
    	// Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/validateDays';
        var type = 'POST';
        var objJson = JSON.stringify({sourceOrigin: sourceOrigin, daysForPayment: daysForPayment });
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
    }
    /**********************************************/
    /*                 Encapsulate                */
    /**********************************************/
    return {
    	GetByAdjudicationId: GetByAdjudicationId,
    	Save: Save,
    	ValidateDays: ValidateDays
    };
}());