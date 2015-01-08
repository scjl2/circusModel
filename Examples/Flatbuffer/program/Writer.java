package scjlevel2examples.flatbuffer;

import javax.realtime.PriorityParameters;
import javax.safetycritical.ManagedThread;
import javax.safetycritical.StorageParameters;

public class Writer extends ManagedThread
{
	private final FlatBufferMission fbMission;
	private final Reader reader;
	private int i=1;

	public Writer(PriorityParameters priority, StorageParameters storage,
			FlatBufferMission fbMission, Reader reader)
	{
		super(priority, storage, "Writer");

		this.fbMission = fbMission;
		this.reader = reader;
	}

	public synchronized void notifyWriter()
	{
		notify();
	}

	public synchronized void run()
	{
		System.out.println("Writer!");

		while (!fbMission.terminationPending())
		{
			try
			{
				while (!fbMission.bufferEmpty())
				{
					wait();
				}

				fbMission.write(i);
				i++;

				reader.notifyReader();
			}
			catch (InterruptedException ie)
			{
				//Handle Interruption	
			}
		}

	}
}
