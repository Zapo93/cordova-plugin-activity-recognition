package cordova.plugin.activity.recognition;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.ConnectionResult;


/**
 * This class echoes a string called from JavaScript.
 */
public class ActivityRecognition extends CordovaPlugin implements ConnectionCallbacks, OnConnectionFailedListener
{
	
	// public static ActivityRequestResult Activity;
	// public static ActivityRecognitionInit API = new ActivityRecognitionInit();
	private boolean Connected = false;
	
	public GoogleApiClient mApiClient;
    public CallbackContext callback ;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException 
	{
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

    private void GetActivity( ) 
    {
        if (Connected == true)
		{
           callback.success("Success!");
        } 
        else 
		{
           callback.error("Error Activity.");
        } 
    }
	
	private void Connect() 
	{
        if( !mApiClient.isConnected() || mApiClient == null)
		{
			mApiClient = new GoogleApiClient.Builder(cordova.getActivity())
            .addApi(ActivityRecognition.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build();
 
			mApiClient.connect();
		}
		else
			callback.error("Already Connected!");
    }
	
	@Override
    public void onConnected( Bundle bundle) 
	{
        callback.success(); 
    }
 
    @Override
    public void onConnectionSuspended(int i)
	{
 
    }
 
    @Override
    public void onConnectionFailed( ConnectionResult connectionResult)
	{
		callback.error("Connection Failed !");
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
			callback.error("Dissconnect");
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
			callback.error("StartActivityUpdates");
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
			callback.error("StopActivityUpdates");
		}
    }
	
	
}
