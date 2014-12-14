/** Spacecraft - Mode Change Example
*    
*    This Handler monitors the air speed of the Spacecraft
*    
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;


public class AirSpeedMonitor extends PeriodicEventHandler
{
	/**
	 * A reference to this handler's controlling mission
	 */
	private final LandMission mission;
	
	/**
	 * Class constructor
	 * 
	 * @param	priorityParameters	the priority parameters for this handler
	 * @param	periodicParameters	the periodic parameters for this handler
	 * @param 	storageConfigurationParameters	the storage parameters for this handler
	 * @param	size	the size of the private memory for this handler
	 * @param	landMission	the controlling mission of this handler
	 *
	 */
	public AirSpeedMonitor(PriorityParameters priorityParameters,
			PeriodicParameters periodicParameters,
			StorageParameters storageConfigurationParameters,
			 LandMission landMission)
	{
		super(priorityParameters, periodicParameters, storageConfigurationParameters);
		
		mission = landMission;		 
	}

	/**
	 * The method the infrastructure calls when the handler is released
	 */
	@Override
	public void handleAsyncEvent()
	{
		mission.setAirSpeed(0.0);		
	}
}
