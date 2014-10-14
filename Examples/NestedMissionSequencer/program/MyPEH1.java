import javax.safetycritical.PeriodicEventHandler;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;

private class MyPEH extends PeriodicEventHandler 
{
		private int count = 0;
		Mission m;

		public MyPEH(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m) 
		{
			super(priority, release, storage, "mission1--peh");
			this.m = m;

		}

		@Override
		public void handleAsyncEvent() 
		{			
			count++;

			if (count == 10) 
			{
				m.requestTermination();
			}
		}
}
