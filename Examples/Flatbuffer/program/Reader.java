package scjlevel2examples.flatbuffer;

import javax.realtime.PriorityParameters;
import javax.safetycritical.ManagedThread;
import javax.safetycritical.StorageParameters;

public class Reader extends ManagedThread
{
	private final Writer writer;
	private final FlatBufferMission fbMission;

	public Reader(PriorityParameters priority, StorageParameters storage,
			FlatBufferMission fbMission, Writer writer)
	{
		super(priority, storage, "Reader");

		this.fbMission = fbMission;
		this.writer = writer;
	}

	public synchronized void notifyReader()
	{
		notify();
	}

	public synchronized void run()
	{
		System.out.println("Reader!");

		while (!fbMission.terminationPending())
		{
			try
			{
				while (fbMission.bufferEmpty())
				{
					wait();
				}

				System.out.println("I Read: " + fbMission.read());

				writer.notify();
			}
			catch (InterruptedException ie)
			{
				//Handle Interruption
			}

		}
	}
}
