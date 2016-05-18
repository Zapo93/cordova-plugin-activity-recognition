window.ActivityRecognition = function(str, callback) {
    alert("ActivityRecognition !!!");
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "ActivityRecognition", "GetCurrentActivity", [str]);
};
