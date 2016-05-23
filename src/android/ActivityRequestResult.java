package cordova.plugin.activity.recognition;

public class ActivityRequestResult 
{
	public String ActivityType;
	public int Propability;
	
	public ActivityRequestResult()
	{
		ActivityType = "NoActivityYet";
		Propability = 0;
	}
}