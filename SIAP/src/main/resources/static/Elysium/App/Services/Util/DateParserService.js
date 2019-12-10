/**
 * @name EmissionService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Util.DateParserService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = '/util/dateParser';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name ShortDate
     * @abstract Method to validate days
     */
    var ShortDate = function(date) {
    	// Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/shortDate?date=' + date;
        var type = 'POST';
        
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
    /**
     * @name time
     * @abstract Method to parse time unit
     */
    var Time = function(time) {
    	// Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/time?time=' + time;
        var type = 'POST';
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
    	ShortDate: ShortDate,
    	Time: Time
    };
}());