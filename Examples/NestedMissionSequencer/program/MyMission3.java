import javax.realtime.Clock;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;

public class MyMission3 extends Mission 
{	
	@Override
	protected void initialize() 
	{
		MyPEH myPEH = new MyPEH(new PriorityParameters(10), new PeriodicParameters(new RelativeTime(Clock.getRealtimeClock()),
				new RelativeTime(1000, 0, Clock.getRealtimeClock())), MySCJ.storageParameters_Handlers, this);
		myPEH.register();
	}

	@Override
	public long missionMemorySize() 
	{
		return 50000;
	}
}
