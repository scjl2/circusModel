package myTest;


import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;

public class ThirdMissionSequencer extends MissionSequencer<Mission>{
    private Mission myMission;
    private int count = 0;
    public  ThirdMissionSequencer(PriorityParameters priority, StorageParameters storage) {
	super(priority, storage,"3ms");
//	super(priority, storage);
	myMission = new MyMission3();
    }
    
    @Override
    protected Mission getNextMission() {
//	return myMission;
	if (count == 0) {
	    count ++;
//	    devices.Console.println("3rd sequencer: has next misison");
	    
	    return myMission;
	}
	else{
//	    devices.Console.println("3rd sequencer: has no more misison");
	    return null;
	}
    }
    
}
