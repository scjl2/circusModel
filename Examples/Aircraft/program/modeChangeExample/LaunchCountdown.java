/** Spacecraft - Mode Change Example
* 
* 	This handler counts down from a given value to zero
* 
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;

import javax.realtime.*;
import javax.safetycritical.*;

public class LaunchCountdown extends PeriodicEventHandler
{
	/**
	 * The event to be fired
	 */
	private final AperiodicEventHandler event;
	/**
	 * The starting value of the countdown
	 */
	private int countdown;
	
	/**
	 * Class Constructor
	 * @param priority priority paramters
	 * @param periodic periodic parameters
	 * @param storage storage paramters
	 * @param size private memory size
	 * @param countdown starting value of countdown
	 * @param ae the event to be fired
	 */
	public LaunchCountdown(PriorityParameters priority, PeriodicParameters periodic,
			StorageParameters storage, int countdown, AperiodicEventHandler ae)
	{
		super(priority, periodic, storage);
		event = ae;
		this.countdown = countdown;
	}

	/**
	 * Called when this event is fired, if the countdown is 0 then fire the launch event
	 */
	@Override
	public void handleAsyncEvent()
	{
		System.out.println("LaunchCountdown");
		//if the count has reached 0 then fire the launch event
		//else decrement the count
		if(countdown == 0)
		{
			System.out.println("" + countdown);
			System.out.println("Launching");
			event.release();
		}
		else
		{
			System.out.println("" + countdown);
			countdown --;
		}
	}
}
