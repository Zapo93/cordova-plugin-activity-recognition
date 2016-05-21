var exec = require('cordova/exec');

exports.GetActivity = function(arg0, success) {
    exec(
		success,
		function(){alert("Error!");}, 
		"ActivityRecognition", "GetActivity", [arg0]
		);
};
