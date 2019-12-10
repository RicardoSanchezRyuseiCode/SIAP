/**
 * Elysium.js Version 1.0
 * @abstract Framework initialize
 * @author Ricardo Sanchez Romero, RicardoSanchezRomero@outlook.es
 * @copyright Elysium 15/03/2018
 */
var Elysium = Elysium || {};
/***************************************************************/
/*                       Global Methods                        */
/***************************************************************/
/**
 * @name Elysium.CreateNS
 * @abstract Method to initialize the namespaces of framework
 */
Elysium.CreateNS = function (namespace) {
    var nsparts = namespace.split(".");
    var parent = Elysium;
    if (nsparts[0] === "Elysium") {
        nsparts = nsparts.slice(1);
    }
    for (var i = 0; i < nsparts.length; i++) {
        var partname = nsparts[i];
        if (typeof parent[partname] === "undefined") {
            parent[partname] = {};
        }
        parent = parent[partname];
    }
    return parent;
};
/**
 * @Elysium.Interface
 * @abstract Method to define a new interface
 */
Elysium.Interface = function (name, methods) {
    // Name of the interface
    this.Name = name;
    // Methods of interface
    this.Methods = [];
    // Verify if all the method are string
    for (var i = 0, len = methods.length; i < len; i++) {
        if (typeof methods[i] !== 'string') {
            throw new Error("Interface constructor expects method names to be passed as a string.");
        }
        this.Methods.push(methods[i]);
    }
}
/**
 * @name Elysium.Implements
 * @abstract Method to ensure that a class implements an interface
 * @param object object to verify if accomplish the methods of interfaces
 * @param interfaces array of interfaces
 */
Elysium.Implements = function (object, interfaces) {
    // Loop in the array of interface
    interfaces.forEach(function (iface) {
        // Check  if argument is an interface
        if (iface.constructor !== Elysium.Interface) {
            throw new Error("Second argument have to be an array of instances of Interface.");
        }
        // Check the methods of object
        iface.Methods.forEach(function (method) {
            if (!object[method] || typeof object[method] !== 'function') {
                throw new Error("Object does not implement the " + iface.Name + " interface. Method " + method + " was not found.");
            }
        });
    });
    return object;
}
/***************************************************************/
/*                          NameSpaces                         */
/***************************************************************/
Elysium.CreateNS("Elysium.UI");
Elysium.CreateNS("Elysium.UI.Entities");
Elysium.CreateNS("Elysium.UI.Interfaces");
Elysium.CreateNS("Elysium.Types");
Elysium.CreateNS("Elysium.Directives");
Elysium.CreateNS("Elysium.App");
Elysium.CreateNS("Elysium.App.Globals");
Elysium.CreateNS("Elysium.App.Interfaces");
Elysium.CreateNS("Elysium.App.Controllers");
Elysium.CreateNS("Elysium.App.Controllers.Areas");
Elysium.CreateNS("Elysium.App.Controllers.Areas.Award");
Elysium.CreateNS("Elysium.App.Controllers.Areas.Admin");
Elysium.CreateNS("Elysium.App.Controllers.Areas.Requisition");
Elysium.CreateNS("Elysium.App.Services");
Elysium.CreateNS("Elysium.App.Services.Admin");
Elysium.CreateNS("Elysium.App.Services.Award");
Elysium.CreateNS("Elysium.App.Services.Util");

/***************************************************************/
/*                       Global Variables                      */
/***************************************************************/
try {
    if (localStorage.getItem("Elysium.Ryusei.Language") == null) {
        // Default interface
        localStorage["Elysium.Ryusei.Language"] = "es-MX"; 
    }
    // App Host
    //Elysium.AppHost = AppHost;
    // Api gateway
    //Elysium.ApiGateway = 'http://localhost:8080';
    // Elysium MsgTime
    Elysium.MsgTime = 300;
    // Notification time
    Elysium.NotificationTime = 5000;
    // Profile Picture Size
    Elysium.ProfilePictureSize = 10485760;
    Elysium.ProfilePictureDimension = 114;
    // Spinner Image
    Elysium.SpinnerImage =  "/App/img/loader.gif";
    // Spinner Background
    Elysium.SpinnerBackground = "#fff";
    // File Size
    Elysium.FileSize = 15728640;    
    // Enable Ajax prefilter
    Elysium.EnableAjaxPrefilter();
    // Global Obj
    Elysium.GlobalObj = {};
}
catch (err) { }
