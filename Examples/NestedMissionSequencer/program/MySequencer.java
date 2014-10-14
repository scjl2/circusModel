package myTest;

import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;

public class MySequencer extends MissionSequencer<Mission> {
    private Mission[] missionArray;
    private int count = 0;

    public MySequencer(PriorityParameters priority, StorageParameters storage) {
	super(priority, storage,"OM--ms");
//	super(priority, storage);
	// initialize missions here
	missionArray = new Mission[2];
	missionArray[0] = new TopMission1();
	// missionArray[2] = new TopMission3();
    }

    @Override
    protected Mission getNextMission() {
	if (count == 0) {
	    count++;
	    return missionArray[0];
	}
//	if (count == 1 || count == 2) {
//	    count++;
//	    return missionArray[1];
//	}
	
	return null;

    }
}
