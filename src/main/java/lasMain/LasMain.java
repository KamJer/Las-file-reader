package lasMain;

import lasMain.lasFile.LasFile12;

public class LasMain {
	
	public static void main(String[] args) {
		LasFile12 testFile = new LasFile12("C:\\LAStools\\data\\LAS_clouds\\praca mgr\\4676_283649_N-33-131-C-b-2-4-3.las");
		testFile.loadHeader();
		testFile.loadPoints();
		testFile.write("test.las");
	}
}
