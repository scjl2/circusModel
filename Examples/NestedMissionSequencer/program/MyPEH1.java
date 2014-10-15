package simpleNestedSequencer;

import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;

public class MyPEH1 extends PeriodicEventHandler 
{
		private int count = 0;
		Mission m;

		public MyPEH1(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m) 
		{
			super(priority, release, storage, "mission1--peh");
			this.m = m;

		}

		@Override
		public void handleAsyncEvent() 
		{		
			devices.Console.println(getName() + ":handleAsyncEvent");
			count++;

			if (count == 10) 
			{
				m.requestTermination();
			}
		}
}
