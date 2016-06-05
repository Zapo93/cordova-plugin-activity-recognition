var exec = require('cordova/exec');

exports.GetActivity = function(success) {
    exec(
		success,
		function(){alert("Error getting activity!");}, 
		"ActivityRecognitionPlugin", "GetActivity", []
		);
};


exports.Connect = function() {
    exec(
		function(){alert("Connected !");},
		function(msg){alert(msg);}, 
		"ActivityRecognition", "Connect", []
		);
};

exports.Dissconnect = function() {
    exec(
		function(){alert("Disconnected !");},
		function(){alert("Error while Dissconnecting");}, 
		"ActivityRecognition", "Dissconnect", []
		);
};

exports.StartActivityUpdates = function(interval) {
    exec(
		function(Interv){alert("Activity Updates Started ! Interval = " + Interv);},
		function(){alert("Error while Starting activity updates!");}, 
		"ActivityRecognition", "StartActivityUpdates", [interval]
		);
};

exports.StopActivityUpdates = function() {
    exec(
		function(){alert("Activity Updates Stopped");},
		function(){alert("Error while Stopping activity updates !");}, 
		"ActivityRecognition", "StopActivityUpdates", []
		);
};
