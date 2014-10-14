package myTest;

import javax.realtime.Clock;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;

public class MyMission3 extends Mission {
	/**
	 * This mission has 2 PEHs and one APEH. the first PEH will call the waitForObject method and wake up by time out. The PEH will executes
	 * about 1000 times.
	 */
	
	@Override
	protected void initialize() {
		MyPEH myPEH = new MyPEH(new PriorityParameters(10), new PeriodicParameters(new RelativeTime(Clock.getRealtimeClock()),
				new RelativeTime(1000, 0, Clock.getRealtimeClock())), MySCJ.storageParameters_Handlers, this);
		myPEH.register();
		
//		PeriodicEventHandler myPEH1 = new MyPEH1(new PriorityParameters(12), new PeriodicParameters(new RelativeTime(
//				Clock.getRealtimeClock()), new RelativeTime(1000, 0, Clock.getRealtimeClock())), MySCJ.storageParameters_Handlers, this, myPEH);
//		myPEH1.register();
	}

	@Override
	public long missionMemorySize() {
		return 50000;
	}

	private class MyPEH extends PeriodicEventHandler {
		int count = 0;
		Mission m;

		public MyPEH(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m) {
			super(priority, release, storage, "mission3--peh1");
			this.m = m;
			
		}

		@Override
		public void handleAsyncEvent() {
			devices.Console.println("									Mission 3: PEH1: " + count);
			count++;
			if (count == 10) {
				m.requestTermination();
				devices.Console.println("									Mission T");
			}
		}
	}

	private class MyPEH1 extends PeriodicEventHandler {
		int count = 0;

		public MyPEH1(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m, MyPEH peh) {
			super(priority, release, storage, "mission3--peh2");
		}

		@Override
		public void handleAsyncEvent() {
			devices.Console.println("									Mission 3: PEH2: " + count);
			count++;
		}
	}
}
