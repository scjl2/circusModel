package myTest;


import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;

public class FirstSequencer extends MissionSequencer<Mission>{
    private Mission myMission;
    private int count = 0;
    public FirstSequencer(PriorityParameters priority, StorageParameters storage) {
	super(priority, storage,"1ms");
//	super(priority, storage);
	myMission = new MyMission1();
    }

    @Override
    protected Mission getNextMission() {
//	devices.Console.println("		second sequencer is running. ");
//	return myMission;
	if (count == 0) {
	    count ++;
//	    devices.Console.println("1st sequencer: has next misison");
	    
	    return myMission;
	}
	else{
//	    devices.Console.println("1st sequencer: has no more misison");
	    return null;
	}
    }

}
