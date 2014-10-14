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

public class MyMission1 extends Mission {

	@Override
	protected void initialize() {
		MyPEH myPEH = new MyPEH(new PriorityParameters(5), new PeriodicParameters(new RelativeTime(Clock.getRealtimeClock()),
				new RelativeTime(1000, 0, Clock.getRealtimeClock())), MySCJ.storageParameters_Handlers, this);
		myPEH.register();

//		PeriodicEventHandler myPEH1 = new MyPEH1(new PriorityParameters(12), new PeriodicParameters(new RelativeTime(
//				Clock.getRealtimeClock()), new RelativeTime(1000, 0, Clock.getRealtimeClock())), MySCJ.storageParameters_Handlers, myPEH);
//		myPEH1.register();
	}

	@Override
	public long missionMemorySize() {
		return 50000;
	}

	private class MyPEH extends PeriodicEventHandler {
		private int count = 0;
		Mission m;

		public MyPEH(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m) {
			super(priority, release, storage, "mission1--peh");
			this.m = m;

		}

		@Override
		public void handleAsyncEvent() {
			devices.Console.println("			Mission 1: PEH1: " + count);
			count++;
			if (count == 10) {
				m.requestTermination();
				devices.Console.println("			Mission T");
			}
		}
	}

	private class MyPEH1 extends PeriodicEventHandler {
		private int count = 0;

		public MyPEH1(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, MyPEH peh) {
			super(priority, release, storage, "mission1--peh1");
		}

		@Override
		public void handleAsyncEvent() {
			devices.Console.println("			Mission 1: PEH2: " + count);
			count++;
		}
	}
}
