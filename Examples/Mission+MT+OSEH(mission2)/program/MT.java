package mission2;

import javax.realtime.PriorityParameters;
import javax.safetycritical.ManagedThread;
import javax.safetycritical.StorageParameters;

import devices.Console;

class MT extends ManagedThread
{
	public MT(PriorityParameters pri, StorageParameters storage)
	{
		super(pri, storage);
	}



	
	

	@Override
	public void run()
	{
		Console.println("MT Release");
		Console.println("MT Performing Actions");
		
	}
}