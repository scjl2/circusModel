/** Spacecraft - Mode Change Example
* 
* 	This mission handles events when the craft is landing 
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

public class LandMission extends ModeMission
{
	
	private final int NORM_PRIORITY = PriorityScheduler.instance().getMaxPriority();
	/**
	 * The craft's distance form the ground in Metres
	 */
	private double distanceFromGround;
	/**
	 * The craft's airSpeed in Knots 
	 */
	private double airSpeed;
	/**
	 * Is the parachute deployed?
	 */
	private boolean parachuteDeployed;
	/**
	 * Is the landing gear deployed?
	 */
	private boolean LandingGearDeployed;

	/**
	 * Initialise the mission
	 */
	@Override
	protected void initialize()
	{
		
		StorageParameters storageParametersSchedulable= new StorageParameters(Const.PRIVATE_MEM_SIZE-30*1000, new long[] { Const.HANDLER_STACK_SIZE },
				 Const.PRIVATE_MEM_SIZE-30*1000, Const.IMMORTAL_MEM_SIZE-50*1000, Const.MISSION_MEM_SIZE-100*1000);
		
/* ***Start this mission's handlers */
		GroundDistanceMonitor groundDistanceMonitor = new GroundDistanceMonitor(
				new PriorityParameters(NORM_PRIORITY),
				new PeriodicParameters(new RelativeTime(0, 0), new RelativeTime(10, 0)),
				storageParametersSchedulable,
				 this);
		groundDistanceMonitor.register();
		
		AirSpeedMonitor airSpeedMonitor = new AirSpeedMonitor(
				new PriorityParameters(NORM_PRIORITY),
				new PeriodicParameters(new RelativeTime(0,0), new RelativeTime(10, 0)),
				storageParametersSchedulable,
				 this);

			airSpeedMonitor.register();
		
		LandingGearHandler landingHandler = new LandingGearHandler(new PriorityParameters(5),
				new AperiodicParameters(), storageParametersSchedulable, "Landing Handler", this);

		landingHandler.register();
		
		ParachuteHandler parachuteHandler = new ParachuteHandler(new PriorityParameters(5),
				new AperiodicParameters(), storageParametersSchedulable, "Parachute Handler", this);

		parachuteHandler.register();

//		AperiodicSimulator landSim = new AperiodicSimulator(
//				new PriorityParameters(5),
//				new PeriodicParameters(new RelativeTime(10, 0)),
//				new StorageConfigurationParameters(20480000, 20480000, 20480000),
//				10000, landEvent);

/* ***MainMission Handlers below*** */
/* Uncomment to make this work at Level 1
		   EnvironmentMonitor envMon =
		  new EnvironmentMonitor(new PriorityParameters(5), new
		  PeriodicParameters(new RelativeTime(10, 0)), new
		  StorageConfigurationParameters(20480000, 20480000, 20480000),10000);
		  
		  ControlHandler conHan = new ControlHandler(new PriorityParameters(5),
		  new AperiodicParameters(), new
		  StorageConfigurationParameters(20480000, 20480000, 20480000),10000);
		  
		  ControlEvent conEvent = new ControlEvent(conHan);
		  
		  AperiodicSimulator sim2 = new AperiodicSimulator(new
		  PriorityParameters(5), new PeriodicParameters(new RelativeTime(10,
		  0)), new StorageConfigurationParameters(20480000, 20480000,
		  20480000),10000, conEvent);
*/
	}

	/**
	 * Returns the size of this mission's memory 
	 */
	@Override
	public long missionMemorySize()
	{
		return Const.MISSION_MEM_SIZE_DEFAULT;
	}

	/**
	 * called when landing gear is deployed,
	 * sets <code>LandingGearDeployed</code> to <code>true</code>
	 */
	public synchronized void deployLandingGear()
	{
		LandingGearDeployed = true;
		
	}

	/**
	 * sets <code>distanceFromGround<code> to the supplied <code>double</code>
	 * @param distanceFromGround the distance the craft is from the ground in metres 
	 */
	public synchronized void setGroundDistance(double distanceFromGround)
	{
		this.distanceFromGround = distanceFromGround;		
	}

	/**
	 * Sets the <code>airSpeed</code> to the supplied <code>double</code>
	 * @param airSpeed the craft's air speed in knots
	 */
	public void setAirSpeed(double airSpeed)
	{
		this.airSpeed=airSpeed;		
	}

	/**
	 * Deploys the parachute
	 */
	public void deployParachute()
	{
		//Assuming that 0.0 IS wheels on the runway
		//This would likely be a helper method  
		if(distanceFromGround == 0.0)
		{
			//actually deploy parachute then set this variable
			parachuteDeployed=true;
		}
	}

}
