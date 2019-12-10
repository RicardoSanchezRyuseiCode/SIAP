/**
 * Elysium.Tools.Strings.js Version 1.0
 * @abstract Clase utilitaria para operaciones de cadena
 * @author Ricardo Sanchez Romero, RicardoSanchezRomero@outlook.es
 * @copyright  07/03/2017
 */
Elysium.Directives.RequestError = (function () {
    /**
     * @name ThrowXhr
     * @abstract Method to throw Xhr error
     * @param {any} xhr
     */
    var ThrowXhr = function (xhr) {
    	// Check status
        if(xhr.status == 500) {
        	// Check if structure is json
	        if (typeof xhr.responseJSON != 'undefined' && xhr.responseJSON != null) {	
	        	
	        	if(xhr.responseJSON.message.indexOf("Error") >= 0)
	        		Elysium.UI.Entities.Notification.Error({ text: xhr.responseJSON.message, time: Elysium.NotificationTime });
	        	else
	        		Elysium.UI.Entities.Notification.Warning({ text: xhr.responseJSON.message, time: Elysium.NotificationTime });
	        }
        }
        else if(xhr.status == 302) {
        	window.location.href = "/login";
        }       
        else if(xhr.status == 200) {
        	window.location.href = "/login";
        }
    }
    /**
     * Encapsulamiento
     */
    return {
        ThrowXhr: ThrowXhr
    }
}());