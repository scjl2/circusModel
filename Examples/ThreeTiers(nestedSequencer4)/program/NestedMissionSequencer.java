package nestedSequencer4;

import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import devices.Console;

class NestedMissionSequencer extends MissionSequencer<Mission>
{
	private boolean notReleased = true;
	
	public NestedMissionSequencer(PriorityParameters priority, StorageParameters storage,
			String name) throws IllegalStateException
	{
		super(priority, storage, name);
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	protected Mission getNextMission()
	{
		Console.println(getName()+  " getNextMission");
		
		if (notReleased)
		{
			Mission mission = new NestedMission();
		
			notReleased = false;
			return mission;
		}
		else
		{
			return null;
		}
	}
	
}
