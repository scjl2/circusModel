package myTest;

import javax.realtime.Clock;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.Services;
import javax.safetycritical.StorageParameters;
import javax.scj.util.Priorities;

public class MyMission2 extends Mission {
	/**
	 * This mission has three PEHs. The first PEH will call waitForObject method and the second will call notify. The thrid PEH is used to
	 * counting and terminate the mission. The waitForObject method and notify method could run about 1000 times.
	 */
//	Monitor lock = new Monitor(Priorities.NORM_PRIORITY);

	@Override
	protected void initialize() {
		MyPEH myPEH = new MyPEH(new PriorityParameters(20), new PeriodicParameters(new RelativeTime(Clock.getRealtimeClock()),
				new RelativeTime(1000, 0, Clock.getRealtimeClock())), MySCJ.storageParameters_Handlers, this);
		myPEH.register();
		
//		PeriodicEventHandler myPEH1 = new MyPEH1(new PriorityParameters(12), new PeriodicParameters(new RelativeTime(
//				Clock.getRealtimeClock()), new RelativeTime(1000, 0, Clock.getRealtimeClock())), MySCJ.storageParameters_Handlers, myPEH);
//		myPEH1.register();
		
//		Services.setCeiling(myPEH, Priorities.NORM_PRIORITY);
	}

	@Override
	public long missionMemorySize() {
		return 50000;
	}

	private class MyPEH extends PeriodicEventHandler {
		int count = 0;
		Mission m;

		public MyPEH(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m) {
			super(priority, release, storage, "mission2--peh1");
			this.m = m;
			
		}

		@Override
		public void handleAsyncEvent() {
//			Services.setCeiling(this, Priorities.NORM_PRIORITY);
//			waitForGo();
			
			devices.Console.println("						Mission 2: PEH1: " + count);
//			devices.Console.println("2ed ms: " + this.getMission().getSequencer().getManagedMemory().memoryRemaining());
			count++;
			if (count == 10) {
				m.requestTermination();
				devices.Console.println("						Mission T");
			}
		}

		public synchronized void waitForGo() {
			devices.Console.println("						before wait");
			try {
				wait();
			} catch (InterruptedException e) {
			}
			devices.Console.println("						after wait");
		}

		public synchronized void go() {
			devices.Console.println("						before notify");
			notify();
			devices.Console.println("						after notify");
		}
	}

	private class MyPEH1 extends PeriodicEventHandler {
		int count = 0;
		MyPEH handler;

		public MyPEH1(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, MyPEH peh) {
			super(priority, release, storage, "mission2--peh2");
			this.handler = peh;
		}

		@Override
		public void handleAsyncEvent() {
//			handler.go();

			devices.Console.println("						Mission 2: PEH2: " + count);
			count++;
		}
	}

}
