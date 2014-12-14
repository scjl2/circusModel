/** Spacecraft - Mode Change Example
* 
* 	This class monitors the craft's environment -- Oxygen levels, internal pressure, fuel levels etc.
* 
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;


public class EnvironmentMonitor extends PeriodicEventHandler
{
	/**
	 * Class Constructor	
	 * @param priority priority parameters
	 * @param periodic periodic parameters
	 * @param storage storage parameters
	 * @param size private memory size
	 */
	public EnvironmentMonitor(PriorityParameters priority, PeriodicParameters periodic,
			StorageParameters storage, String name)
	{
		super(priority, periodic, storage, name);
	}
 
	/**
	 * Called when the handler is fired
	 */
	@Override
	public void handleAsyncEvent()
	{
		System.out.println("Checking Environment");
		
		//**Obviously these is for testing purposes only toggle the true and false values to test behaviour

		//if environment conditions are bad
		//if(true)
		
		//if environment conditions are fine
		if(false)
		{
			//To get here the environment conditions should be below safe levels
			//Calls for the Mission sequence to be terminated -- in the full application this would prompt the 
			//Mission Sequencer to load an emergency mission to remedy the situation
			//For now, the application merely terminates
			Mission.getCurrentMission().requestTermination();
		}
	}

}
