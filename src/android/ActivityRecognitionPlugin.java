package cordova.plugin.activity.recognition;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

import android.app.PendingIntent;
import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.location.ActivityRecognition;


/**
 * This class echoes a string called from JavaScript.
 */
public class ActivityRecognitionPlugin extends CordovaPlugin implements ConnectionCallbacks, OnConnectionFailedListener
{
	
	// public static ActivityRequestResult Activity;
	// public static ActivityRecognitionInit API = new ActivityRecognitionInit();
	private boolean Connected = false;
	
	public GoogleApiClient mApiClient;
    public CallbackContext callback ;
	private PendingIntent pendingIntent;

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
           callback.error("Not Connected.");
        } 
    }
	
	private void Connect() 
	{
		Connected = true;
        if(  mApiClient == null || !mApiClient.isConnected() )
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
        if(mApiClient!= null && mApiClient.isConnected())
		{
			Connected = false;
			mApiClient.disconnect();
			callback.success();
		}
		else
		{
			callback.error("Not Connected !");
		}
    }
	
	private void StartActivityUpdates(int interval) 
	{
        if(mApiClient != null && mApiClient.isConnected())
		{
			Intent intent = new Intent( cordova.getActivity(), ActivityRecognitionIntentService.class );
			pendingIntent = PendingIntent.getService( cordova.getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
			ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates( mApiClient, interval, pendingIntent );
			callback.success(interval);
		}
		else
		{
			callback.error("Not Connected");
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
	
	@Override
	public void onDestroy() 
	{
		if(mApiClient!= null && mApiClient.isConnected())
		{
			Connected = false;
			mApiClient.disconnect();
		}
	}
	
}
