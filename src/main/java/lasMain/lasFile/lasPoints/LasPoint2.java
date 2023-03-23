package lasMain.lasFile.lasPoints;

import java.io.DataInputStream;
import java.io.IOException;

import endianUtilities.LittleEndianUtilities;
import lasMain.lasFile.LasFile;

public class LasPoint2 extends LasPoint0 {

	private short red, green, blue;
	
	public LasPoint2(DataInputStream dis, LasFile owner) {
		super(dis, owner);
		try {
//			read color of points
			byte[] redBuffer = new byte[2];
			dis.read(redBuffer);
			this.red = LittleEndianUtilities.getShort(redBuffer);
			
			byte[] greenBuffer = new byte[2];
			dis.read(greenBuffer);
			this.green = LittleEndianUtilities.getShort(greenBuffer);
			
			byte[] blueBuffer = new byte[2];
			dis.read(blueBuffer);
			this.blue = LittleEndianUtilities.getShort(blueBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] getPointAsBytes() {
		byte[] allBytes = super.getPointAsBytes();
		int index = allBytes.length;
		
		byte[] redBuffer = java.nio.ByteBuffer.allocate(2).putShort(red).array();
		for (int i = redBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = redBuffer[i];
			System.out.print("pointSourceID ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] greenBuffer = java.nio.ByteBuffer.allocate(2).putShort(green).array();
		for (int i = greenBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = greenBuffer[i];
			System.out.print("pointSourceID ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] blueBuffer = java.nio.ByteBuffer.allocate(2).putShort(blue).array();
		for (int i = blueBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = blueBuffer[i];
			System.out.print("pointSourceID ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		return allBytes;
	}
	
	public short getRed() {
		return red;
	}

	public short getGreen() {
		return green;
	}

	public short getBlue() {
		return blue;
	}
}
