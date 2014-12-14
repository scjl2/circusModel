/** Spacecraft - Mode Change Example
* 
 * This mission handles events when the craft is cruising -- not launching,
 * orbiting, or landing.
* 
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;


import javax.realtime.AbsoluteTime;
import javax.realtime.AperiodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.PriorityScheduler;
import javax.safetycritical.Services;
import javax.safetycritical.StorageParameters;
import javax.scj.util.Const;
import javax.realtime.PeriodicParameters;


public class CruiseMission extends ModeMission
{
	/**
	 * Boolean representing if it is safe to burn the engines
	 */
	private boolean okToCruise = true;
	
	/**
	 * Desired duration of the burn
	 */
	private AbsoluteTime burnDuration;
	
	@Override
	protected void initialize()
	{
		StorageParameters storageParametersSchedulable= new StorageParameters(Const.PRIVATE_MEM_SIZE-30*1000, new long[] { Const.HANDLER_STACK_SIZE },
				 Const.PRIVATE_MEM_SIZE-30*1000, Const.IMMORTAL_MEM_SIZE-50*1000, Const.MISSION_MEM_SIZE-100*1000);
		
		/**
		 * Then length of time to burn the engines for
		 */
		burnDuration = new AbsoluteTime();
/*  *** Start the Schedulable Objects *** */
 
 
		/**
		 * Handler for monitoring the cruising conditions and updating <code>okToCruise</code> 
		 */
		CruiseConditionsMonitor crusieConditionsMonitor = 
				new CruiseConditionsMonitor(
					new PriorityParameters(PriorityScheduler.instance().getMaxPriority()) ,
					new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(10, 0)),
					storageParametersSchedulable,
					"Cruise Controller", this);
		crusieConditionsMonitor.register();
		/**
		 * Handler for responding to the burn being activated
		 */
		BurnActivationHandler burnActivationHandler = 
				new BurnActivationHandler(new PriorityParameters(5),
				new AperiodicParameters(),
				storageParametersSchedulable,
				"Burn Activation Handler",
				this);
		burnActivationHandler.register();

		
		/**
		 * Handler for activating the engine burn when requested
		 */
		BurnDurationHandler burnDurationHandler = 
				new BurnDurationHandler(new PriorityParameters(5),
				new AperiodicParameters(),
				storageParametersSchedulable,
				"Burn Duration Handler",
				this);
		burnDurationHandler.register();
	
		/**
		 * Handler simulating a button push to activate the burn
		 */
		AperiodicSimulator cruiseSim = new AperiodicSimulator(
				new PriorityParameters(5),
				new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(10, 0)),
				storageParametersSchedulable,
				burnActivationHandler);
		cruiseSim.register();
/* MainMission handlers below ***
/*
* Uncomment here to make this mission work at  Level1 
		EnvironmentMonitor envMon = new
		  EnvironmentMonitor(new PriorityParameters(5), new
		  	PeriodicParameters(new RelativeTime(10, 0)), new
		  			StorageConfigurationParameters(20480000, 20480000, 20480000),10000);
		  
		  ControlHandler conHan = new ControlHandler(new PriorityParameters(5),
		  	new AperiodicParameters(), new
		  			StorageConfigurationParameters(20480000, 20480000, 20480000),10000);
		  
		 ControlEvent conEvent = new ControlEvent(conHan);
		  
		 AperiodicSimulator sim2 = new AperiodicSimulator
		 	(new PriorityParameters(5), new PeriodicParameters
		 		(new RelativeTime(10,0)), new StorageConfigurationParameters
		 									(20480000, 20480000,20480000),10000, conEvent);
*/
	}

	/**
	 * returns the mission's private memory size
	 */
	@Override
	public long missionMemorySize()
	{
		return Const.MISSION_MEM_SIZE_DEFAULT;
	}

	/**
	 * returns <code> oKToCruise</code> 
	 * 
	 * @return true if it is ok to activate the burn, false if it is not
	 */
	public boolean isOkToCruise()
	{
		return okToCruise;
	}

	/**
	 *  Sets <code>okToCruise</code>
	 *  
	 * @param okToCruise new boolean value for <code>okToCruise</code>
	 */
	public void setOkToCruise(boolean okToCruise)
	{
		this.okToCruise = okToCruise;
	}

	/**
	 * Sets the duration of the burn
	 * 
	 * @param millis burn duration millisecond part
	 * @param nanos burn duration nanosecond part 
	 */
	public synchronized void setBurnDuration(long millis, int nanos)
	{
		burnDuration.set(millis, nanos);
	}

	/**
	 * activates the engine burn
	 */
	public void activateBurn()
	{
		System.out.println("Burning Engines!");
		//actually activate the engines here
		Services.delay(burnDuration); 
	}

}
