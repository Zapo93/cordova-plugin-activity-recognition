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
	private Boolean Connected = false;
	public CallbackContext callback ;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callback = callbackContext;
		if (action.equals("GetActivity"))
		{
            this.GetActivity();
            return true;
        }
		if (action.equals("Connect"))
		{
            this.Connect();
            return true;
        }
		if (action.equals("Dissconnect"))
		{
            this.Dissconnect();
            return true;
        }
		if (action.equals("StartActivityUpdates"))
		{
			int interval = args.getInt(0);
            this.StartActivityUpdates(interval);
            return true;
        }
		if (action.equals("StopActivityUpdates"))
		{
            this.StopActivityUpdates();
            return true;
        }
		
        return false;
    }

    private void GetActivity() 
	{
        if (Connected == true)
		{
            callback.success();
        } 
        else 
		{
            callback.error("Expected one non-empty string argument.");
        }
    }
	
	private void Connect() 
	{
        if(Connected == false)
		{
			Connected = true;
			callback.success();
		}
		else
		{
			callback.error();
		}
    }
	
	private void Dissconnect() 
	{
        if(Connected == true)
		{
			Connected = false;
			callback.success();
		}
		else
		{
			callback.error();
		}
    }
	
	private void StartActivityUpdates(int interval) 
	{
        if(Connected == true)
		{
			callback.success(interval);
		}
		else
		{
			callback.error();
		}
    }
	
	private void StopActivityUpdates() 
	{
        if(Connected == true)
		{
			callback.success();
		}
		else
		{
			callback.error();
		}
    }
	
	
}
