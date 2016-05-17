window.ActivityRecognition = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "ActivityRecognition", "GetCurrentActivity", [str]);
};