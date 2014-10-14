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
			
			devices.Console.println("Mission 2: PEH1: " + count);
//			devices.Console.println("2ed ms: " + this.getMission().getSequencer().getManagedMemory().memoryRemaining());
			count++;
			if (count == 10) {
				m.requestTermination();
				devices.Console.println("Mission T");
			}
		}

		public synchronized void waitForGo() {
			devices.Console.println("before wait");
			try {
				wait();
			} catch (InterruptedException e) {
			}
			devices.Console.println("after wait");
		}

		public synchronized void go() {
			devices.Console.println("before notify");
			notify();
			devices.Console.println("after notify");
		}
	}
