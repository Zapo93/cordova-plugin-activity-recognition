package cordova.plugin.activity.recognition;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class ActivityRecognition extends CordovaPlugin {
	
	public static ActivityRequestResult Activity;
	public static ActivityRecognitionInit API = new ActivityRecognitionInit();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("GetActivity")) {
            String message = args.getString(0);
            this.GetActivity(message, callbackContext);
            return true;
        }
        return false;
    }

    private void GetActivity(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } 
        else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
