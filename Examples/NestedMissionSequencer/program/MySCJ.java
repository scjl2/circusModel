package simpleNestedSequencer;

import javax.safetycritical.Launcher;
import javax.safetycritical.StorageParameters;
import javax.scj.util.Const;

public class MySCJ {

    public static StorageParameters storageParameters;
    public static StorageParameters storageParameters_Handlers;

    public static void main(String[] args) {
	 storageParameters = new StorageParameters(150 * 1000, new long[] { Const.HANDLER_STACK_SIZE },
	 Const.PRIVATE_MEM_SIZE-25*1000, Const.IMMORTAL_MEM_SIZE-50*1000, Const.MISSION_MEM_SIZE-100*1000);
	
	 storageParameters_Handlers = new StorageParameters(Const.PRIVATE_MEM_SIZE-30*1000, new long[] { Const.HANDLER_STACK_SIZE },
	 Const.PRIVATE_MEM_SIZE-30*1000, Const.IMMORTAL_MEM_SIZE-50*1000, Const.MISSION_MEM_SIZE-100*1000);

	new Launcher(new MyApp(), 2);
    }

}
