/**
 * @name QuotationService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-12-01
 */
Elysium.App.Services.Award.QuotationService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = Elysium.AppHost + 'award/quotation';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name Create
     * @abstract Method to create quotation
     */
    var Create = function (quotationCreationParam) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/create';
        var type = 'POST';
        var objJson = JSON.stringify(quotationCreationParam);
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
     * @name ValidateDate
     * @abstract Method validate date of quotation
     */
    var ValidateDate = function (quotationValidateDateParam) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/validateDate';
        var type = 'POST';
        var objJson = JSON.stringify(quotationValidateDateParam);
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
    	Create: Create,
    	ValidateDate: ValidateDate
    };
}());