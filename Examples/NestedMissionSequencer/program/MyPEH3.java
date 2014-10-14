import javax.safetycritical.PeriodicEventHandler;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;

private class MyPEH extends PeriodicEventHandler 
{
	int count = 0;
	Mission m;

	public MyPEH(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m) 
	{
		super(priority, release, storage, "mission3--peh1");
		this.m = m;			
	}

	public void handleAsyncEvent() 
	{
		count++;

		if (count == 10) 
        {
			m.requestTermination();			
		}
	}
}
