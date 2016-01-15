package mission2;

import javax.realtime.AperiodicParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

class MissionA extends Mission
{
	@Override
	@SCJAllowed(Level.SUPPORT)
	protected void initialize()
	{
		AperiodicParameters apParams = new AperiodicParameters(new RelativeTime(5, 0), null);
		OSEH OneShot = new OSEH(MyApp.pri, new RelativeTime(60,0),apParams, MyApp.storage, this);
		OneShot.register();
		
		
		MT thread = new MT(MyApp.pri, MyApp.storage);
		thread.register();
		
		
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public long missionMemorySize()
	{
		return 1048576;
	}
	
}
