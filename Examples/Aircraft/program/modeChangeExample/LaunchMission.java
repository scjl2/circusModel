/** Spacecraft - Mode Change Example
* 
*   This mission deals with launching the craft 
*   
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;


import javax.realtime.AperiodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.PriorityScheduler;
import javax.safetycritical.StorageParameters;
import javax.scj.util.Const;
import javax.realtime.PeriodicParameters;


public class LaunchMission extends ModeMission 
{
	/**
	 * This variable represents if the craft is able to launch, when the countdown reaches 0
	 */
	private volatile boolean launch;

	/**
	 * Called when the craft is ok to launch
	 * sets <code>launch</code> to <code>true</code>
	 */
	public void goodToLaunch()
	{
		launch = true;	
	}

	/**
	 * Returns the <code>launch</code> variable
	 * @return <code>launch</code>
	 */
	public boolean canLaunch()
	{
		return launch;
	}

	/**
	 * initialises the mission
	 */
	@Override
	protected void initialize()
	{
		StorageParameters storageParametersSchedulable= new StorageParameters(Const.PRIVATE_MEM_SIZE-30*1000, new long[] { Const.HANDLER_STACK_SIZE },
				 Const.PRIVATE_MEM_SIZE-30*1000, Const.IMMORTAL_MEM_SIZE-50*1000, Const.MISSION_MEM_SIZE-100*1000);

		
		//Initially false because the conditions haven't been checked yet
		launch = false;

		//Load the handlers for this mission
		//Note these handlers are passed a reference to this mission so they can update the 
		//ready to launch variable with the two methods above
		LaunchConditionsMonitor launchConditionsMonitor =
				new LaunchConditionsMonitor
					(new PriorityParameters(PriorityScheduler.instance().getMaxPriority()),
					new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(500, 0)),
					storageParametersSchedulable, 
				
					this);
		
		launchConditionsMonitor.register();

		LaunchHandler launchHandler = 
			new LaunchHandler(new PriorityParameters(5),
            				new AperiodicParameters(), 
            				storageParametersSchedulable, "Launch Handler", this);

		launchHandler.register();

	LaunchCountdown launchCountdown = new LaunchCountdown(new PriorityParameters(5), 
				new PeriodicParameters(new RelativeTime(0, 0),new RelativeTime(1000, 0)),
				storageParametersSchedulable, 5, launchHandler);

	launchCountdown.register();
	
/* ***MainMission Handlers below ***
/* ***Uncomment this to make it work at Level 1 ***
	EnvironmentMonitor envMon = new EnvironmentMonitor(new PriorityParameters(5), 
				new PeriodicParameters(new RelativeTime(500, 0)),
				new StorageConfigurationParameters(20480000, 20480000, 20480000),10000);

		ControlHandler conHan = new ControlHandler(new PriorityParameters(5),
            				new AperiodicParameters(), 
					new StorageConfigurationParameters(20480000, 20480000, 20480000),10000);

		ControlEvent conEvent = new ControlEvent(conHan);

		AperiodicSimulator sim = new AperiodicSimulator(new PriorityParameters(5), 
				new PeriodicParameters(new RelativeTime(1000, 0)),
				new StorageConfigurationParameters(20480000, 20480000, 20480000),10000, conEvent);
*/
	}

	/**
	 * Returns the size of the mission's memory
	 */
	@Override
	public long missionMemorySize()
	{
		return Const.MISSION_MEM_SIZE_DEFAULT;
	}

 
}
