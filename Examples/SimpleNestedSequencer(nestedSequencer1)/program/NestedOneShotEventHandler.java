package nestedSequencer1;

import javax.safetycritical.OneShotEventHandler;
import javax.safetycritical.StorageParameters;
import javax.realtime.AperiodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.HighResolutionTime;

import devices.Console;

public class NestedOneShotEventHandler extends OneShotEventHandler
{
	Mission m;

	public NestedOneShotEventHandler(PriorityParameters priority, HighResolutionTime time, AperiodicParameters release, StorageParameters storage,
		Mission controllignMission)
	{
		super(priority, time, release, storage);
		m = controllignMission;
	}

	@Override
	public void handleAsyncEvent()
	{
		Console.println("Nested One-Shot: Release");
		m.requestTermination();
	}
}
