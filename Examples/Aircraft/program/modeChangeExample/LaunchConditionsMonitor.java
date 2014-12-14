/** Spacecraft - Mode Change Example
* 
* 	 handler monitors the conditions which must be true for the craft to launch
//Obviously this is simulated here
* 
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;


public class LaunchConditionsMonitor extends PeriodicEventHandler
{
	/**
	 * Controlling mission
	 */
	private final LaunchMission launchMission;
	/**
	 * This variable is for testing only and indicates the amount of releases the handler will have 
	 * before the launch conditions are 'good enough' to be true
	 */
	private int conditions = 20;
	
	/**
	 * Class Constructor
	 * @param	priorityParameters	the priority parameters for this handler
	 * @param	periodicParameters	the periodic parameters for this handler
	 * @param 	storageConfigurationParameters	the storage parameters for this handler
	 * @param	string	the size of the private memory for this handler
	 * @param launchMission controlling mission
	 */
	public LaunchConditionsMonitor(PriorityParameters priority, PeriodicParameters periodic,
			StorageParameters storage,  LaunchMission launchMission)
	{
		super(priority, periodic, storage);	
		this.launchMission = launchMission;	
	}

	/**
	 * Called when the handler is fired, sets <code>goodToLaunch</code>
	 */
	@Override
	public void handleAsyncEvent()
	{
		System.out.println("Checking Launch Conditions");
		//Here for testing, checks if the conditions are 'good enough' i.e. the value has been
		//reduced to 0. In the full application this would check sensors each release and
		//toggle the goodToLaunch value accordingly
		conditions --;
		if (conditions <= 0)
		{
			//Tells the other handlers, via the launch mission, that it is ok to launch
			launchMission.goodToLaunch();
		}
	}
}
