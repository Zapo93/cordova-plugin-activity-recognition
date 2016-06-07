package cordova.plugin.activity.recognition;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;


public class ActivityRecognitionIntentService extends IntentService 
{
	public ActivityRecognitionIntentService()
	{
        super("ActivityRecognitionIntentService");
    }
	
	@Override
	protected void onHandleIntent(Intent intent) 
	{
		if(ActivityRecognitionResult.hasResult(intent)) 
		{
			ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
			DetectedActivity CurrentActivity = result.getMostProbableActivity();
			
			if(CurrentActivity.getConfidence() >= 51)
			{
				switch(CurrentActivity.getType())
				{
					case DetectedActivity.IN_VEHICLE : ActivityRecognitionPlugin.Activity.ActivityType = "In Vechicle";break;
					case DetectedActivity.IN_VEHICLE : ActivityRecognitionPlugin.Activity.ActivityType = "In Vechicle";break;
					case DetectedActivity.IN_VEHICLE : ActivityRecognitionPlugin.Activity.ActivityType = "In Vechicle";break;
					case DetectedActivity.IN_VEHICLE : ActivityRecognitionPlugin.Activity.ActivityType = "In Vechicle";break;
					case DetectedActivity.IN_VEHICLE : ActivityRecognitionPlugin.Activity.ActivityType = "In Vechicle";break;
					case DetectedActivity.IN_VEHICLE : ActivityRecognitionPlugin.Activity.ActivityType = "In Vechicle";break;
				}
				ActivityRecognitionPlugin.Activity.Propability = CurrentActivity.getConfidence();	
			}
			else
				ActivityRecognitionPlugin.Activity.ActivityType = "NotConfidentEnough";
			
		}
	}
    
}
