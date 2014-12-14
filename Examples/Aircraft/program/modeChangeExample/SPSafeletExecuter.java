/** Spacecraft - Mode Change Example
* 
* 	Executes the application's <code>Safelet</code>
* 
*   @author Matt Luckcuck <ml881@york.ac.uk>
*/
package modeChangeExample;


import javax.safetycritical.Launcher;
import javax.safetycritical.Mission;
import javax.safetycritical.Safelet;


public class SPSafeletExecuter extends Launcher
{
    /**
    * Class Constructor
    *   @param arg0 The Safelet to execute
    */
	public SPSafeletExecuter(Safelet<Mission> safelet)
	{
		super(safelet, 2);	
	}
    
    /**
    *   Runs the Safelet, which starts the application
    */
	public static void main (String [] args)
	{
		new SPSafeletExecuter(new SPSafelet()).run();
	}
}
