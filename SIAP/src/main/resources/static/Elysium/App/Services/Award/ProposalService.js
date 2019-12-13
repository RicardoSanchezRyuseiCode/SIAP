/**
 * @name ProposalService
 * @abstract Service class
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @CreationDate 2019-12-01
 */
Elysium.App.Services.Award.ProposalService = (function () {
    /**********************************************/
    /*                   Variables                */
    /**********************************************/
    var Api = '/award/proposal';
    /**********************************************/
    /*                   Methods                  */
    /**********************************************/
    /**
     * @name Save
     * @abstract Method to update an user
     */
    var Create = function (listProposalCreationParam) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/create';
        var type = 'POST';
        var objJson = JSON.stringify(listProposalCreationParam);
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
    var Upload = function (adjudicationId, formData, handlerProgress) {
        // Define promise
        var deferred = new $.Deferred();
        // Get type and url
        var url = Api + '/upload/' + adjudicationId;
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
    	Upload: Upload,
    	Download: Download
    };
}());