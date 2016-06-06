var exec = require('cordova/exec');

exports.GetActivity = function(success) {
    exec(
		success,
		function(msg){alert("Error getting activity!" + msg);}, 
		"ActivityRecognitionPlugin", "GetActivity", []
		);
};


exports.Connect = function() {
    exec(
		function(){alert("Connected !");},
		function(msg){alert("Error : " + msg);}, 
		"ActivityRecognitionPlugin", "Connect", []
		);
};

exports.Dissconnect = function() {
    exec(
		function(){alert("Disconnected !");},
		function(){alert("Error while Dissconnecting");}, 
		"ActivityRecognitionPlugin", "Dissconnect", []
		);
};

exports.StartActivityUpdates = function(interval) {
    exec(
		function(Interv){alert("Activity Updates Started ! Interval = " + Interv);},
		function(msg){alert("Error while Starting activity updates!  "+ msg);}, 
		"ActivityRecognitionPlugin", "StartActivityUpdates", [interval]
		);
};

exports.StopActivityUpdates = function() {
    exec(
		function(){alert("Activity Updates Stopped");},
		function(){alert("Error while Stopping activity updates !");}, 
		"ActivityRecognitionPlugin", "StopActivityUpdates", []
		);
};
