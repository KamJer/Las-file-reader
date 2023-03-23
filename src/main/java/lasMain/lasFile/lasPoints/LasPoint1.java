package lasMain.lasFile.lasPoints;

import java.io.DataInputStream;
import java.io.IOException;

import endianUtilities.LittleEndianUtilities;
import lasMain.lasFile.LasFile;

public class LasPoint1 extends LasPoint0 {
	
	private double gpsTime;

	public LasPoint1(DataInputStream dis, LasFile owner) {
		super(dis, owner);
		try {
//			reading GPS Time
			byte[] gpsTimeBuffer = new byte[8];
			dis.read(gpsTimeBuffer);
			this.gpsTime = LittleEndianUtilities.littleEndianDouble(gpsTimeBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] getPointAsBytes() {
		byte[] allBytes = super.getPointAsBytes();
		int index = allBytes.length;
		
		byte[] gpsTimeBuffer = java.nio.ByteBuffer.allocate(8).putDouble(gpsTime).array();
		for (int i = gpsTimeBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = gpsTimeBuffer[i];
			System.out.print("pointSourceID ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		return allBytes;
	}

	public double getGpsTime() {
		return gpsTime;
	}
}
