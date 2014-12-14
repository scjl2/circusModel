/** Spacecraft - Mode Change Example
* 
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;



/**
 * Handler for monitoring the conditions which have to be true for the craft to begin cruising
 * @author Matt Luckcuck
 *
 */
public class CruiseConditionsMonitor extends PeriodicEventHandler
{
	private final CruiseMission mission;
	
	public CruiseConditionsMonitor(PriorityParameters priority, PeriodicParameters periodic,
			StorageParameters storage, String name, CruiseMission mission)
	{
		super(priority, periodic, storage, name);	
		this.mission = mission;
	}

    /**
    *   Called when the handler is fired
    */
	@Override
	public void handleAsyncEvent()
	{
		System.out.println("Checking Cruise Conditions");
		//Check sensors to make sure an engine burn is safe
		mission.setOkToCruise(true);
	}

}
