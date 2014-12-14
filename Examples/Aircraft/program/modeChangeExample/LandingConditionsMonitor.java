/** Spacecraft - Mode Change Example
* 
* 	Handler for monitoring the conditions which must be true for the craft to start landing
* 
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;


public class LandingConditionsMonitor extends PeriodicEventHandler
{
	/**
	 * Class Constructor
	 * @param priority the priority parameters of this handler
	 * @param periodic the periodic parameters of this handler
	 * @param storage the storage parameters of this handler
	 * @param size the private memory size for this handler
	 */
	public LandingConditionsMonitor(PriorityParameters priority, PeriodicParameters periodic,
			StorageParameters storage, String name)
	{
		super(priority, periodic, storage, name);
	}

	/**
	 * Called when this handler is fired
	 */
	@Override
	public void handleAsyncEvent()
	{
		System.out.println("Checking Landing Conditions");
		
	}

}
