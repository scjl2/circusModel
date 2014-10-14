package myTest;


import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;

public class SecondMissionSequencer extends MissionSequencer<Mission>{
    private Mission myMission;
    private int count = 0;
    public  SecondMissionSequencer(PriorityParameters priority, StorageParameters storage) {
	super(priority, storage,"2ms");
//	super(priority, storage);
	myMission = new MyMission2();
    }
    
    @Override
    protected Mission getNextMission() {
//	  return myMission;
	if (count == 0) {
	    count ++;
//	    devices.Console.println("2ed sequencer: has next misison");
	    
	    return myMission;
	}
	else{
//	    devices.Console.println("2ed sequencer: has no more misison");
	    return null;
	}
    }
    
}
