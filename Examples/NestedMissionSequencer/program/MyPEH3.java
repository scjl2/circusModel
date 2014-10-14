private class MyPEH extends PeriodicEventHandler {
	int count = 0;
	Mission m;

	public MyPEH(PriorityParameters priority, PeriodicParameters release, StorageParameters storage, Mission m) {
		super(priority, release, storage, "mission3--peh1");
		this.m = m;			
	}

	public void handleAsyncEvent() {
		devices.Console.println("Mission 3: PEH1: " + count);
			count++;
			if (count == 10) {
				m.requestTermination();
				devices.Console.println("Mission T");
			}
		}
	}
