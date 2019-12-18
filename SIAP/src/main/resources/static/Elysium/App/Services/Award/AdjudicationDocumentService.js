/**
 * @name AdjudicationDocumentService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-11-30
 */
Elysium.App.Services.Award.AdjudicationDocumentService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = '/award/adjudicationDocument';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    
    /**
     * @name GetById
     * @abstract Method to get by id
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
    	GetByAdjudicationId: GetByAdjudicationId,
    	Download: Download
    };
}());