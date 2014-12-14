/** Spacecraft - Mode Change Example
*
* This is the main mission, it represents the Spacecraft.
* It loads the persistent handlers and the sequencer for the modes.
*
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;

import javax.realtime.AperiodicParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.StorageParameters;
import javax.scj.util.Const;

public class MainMission extends Mission
{
    /**
    *   Initialises the Mission, loading the ModeChanger and the persistent handlers
    */
	@Override
	protected void initialize()
	{
		StorageParameters storageParameters = new StorageParameters(150 * 1000, new long[] { Const.HANDLER_STACK_SIZE },
				 Const.PRIVATE_MEM_SIZE-25*1000, Const.IMMORTAL_MEM_SIZE-50*1000, Const.MISSION_MEM_SIZE-100*1000);
				
		StorageParameters storageParametersSchedulable= new StorageParameters(Const.PRIVATE_MEM_SIZE-30*1000, new long[] { Const.HANDLER_STACK_SIZE },
				 Const.PRIVATE_MEM_SIZE-30*1000, Const.IMMORTAL_MEM_SIZE-50*1000, Const.MISSION_MEM_SIZE-100*1000);

		//Load the submission sequencer and persistent handlers
		SPModeChanger sPModeChanger= new SPModeChanger(new PriorityParameters(5), storageParameters );
		
		sPModeChanger.register();

		EnvironmentMonitor environmentMonitor = new EnvironmentMonitor(new PriorityParameters(5), 
				new PeriodicParameters(new RelativeTime(10, 0), null),
				storageParametersSchedulable,"Environment Monitor");

		environmentMonitor.register();
		
		ControlHandler controlHandler = new ControlHandler(new PriorityParameters(5),
            					new AperiodicParameters(), 
            					storageParametersSchedulable,"Control Handler");
		
		controlHandler.register();
		
		AperiodicSimulator controlSim = new AperiodicSimulator(new PriorityParameters(5), 
				new PeriodicParameters(new RelativeTime(10, 0), null),
				storageParametersSchedulable,controlHandler);
		
		controlSim.register();
	}
    
    /**
    * Returns the required size of this Mission's private memory
    */
	@Override
	public long missionMemorySize()
	{
		return Const.MISSION_MEM_SIZE_DEFAULT;
	}
 
}
