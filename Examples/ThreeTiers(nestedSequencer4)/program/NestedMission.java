package nestedSequencer4;

import javax.safetycritical.Mission;
import javax.safetycritical.annotate.Level;
import javax.safetycritical.annotate.SCJAllowed;

import devices.Console;

class NestedMission extends Mission
{
	@Override
	@SCJAllowed(Level.SUPPORT)
	protected void initialize()
	{
		Console.println("Nested Mission initialize");
		
		NestedMissionSequencerA nestedSequencerA = new NestedMissionSequencerA(MyApp.pri, MyApp.storage, "Nested Mission Sequencer A");
		nestedSequencerA.register();
		
		NestedMissionSequencerB nestedSequencerB = new NestedMissionSequencerB(MyApp.pri, MyApp.storage, "Nested Mission Sequencer B");
		nestedSequencerB.register();
		
	}

	@Override
	@SCJAllowed(Level.SUPPORT)
	public long missionMemorySize()
	{
		return 1048576;
	}
}