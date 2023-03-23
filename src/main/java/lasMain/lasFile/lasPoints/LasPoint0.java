package lasMain.lasFile.lasPoints;

import java.io.DataInputStream;
import java.io.IOException;

import endianUtilities.LittleEndianUtilities;
import lasMain.lasFile.LasFile;

public class LasPoint0 {

	public final static int CLASSIFICATION_CREATED_NEVER_CLASSIFIED = 0;
	public final static int CLASSIFICATION_UNCLASSIFIED = 1;
	public final static int CLASSIFICATION_GROUND = 2;
	public final static int CLASSIFICATION_LOW_VEGETATION = 3;
	public final static int CLASSIFICATION_MEDIUM_VEGETATION = 4;
	public final static int CLASSIFICATION_HIGH_VEGETATION = 5;
	public final static int CLASSIFICATION_BUILDING = 6;
	public final static int CLASSIFICATION_LOW_POINT = 7;
	public final static int CLASSIFICATION_MODEL_KEY_POINT = 8;
	public final static int CLASSIFICATION_WATER = 9;
	public final static int CLASSIFICATION_OVERLAP_POINTS = 12;
	
	private LasFile owner;
	
	private int x, y, z;
	
	private short intensity;
	
	private short returnNumber;
	
	private short numberReturns;
	
	private boolean scanDiractionFlag;
	
	private boolean flightLineEndge;
	
	private short classification;
	
	private boolean synthetic;
	
	private boolean keyPoint;
	
	private boolean withheld;
	
	private int scanAngleRank;
	
	private int userData;
	
	private short pointSourceID;
	
	public LasPoint0(DataInputStream dis, LasFile owner) {
		this.owner = owner;
		try {
//			reading X
			byte[] xBuffer = new byte[4];
			dis.read(xBuffer);
			this.x = LittleEndianUtilities.getInteger(xBuffer);
//			reading Y
			byte[] yBuffer = new byte[4];
			dis.read(yBuffer);
			this.y = LittleEndianUtilities.getInteger(yBuffer);
//			reading Z
			byte[] zBuffer = new byte[4];
			dis.read(zBuffer);
			this.z = LittleEndianUtilities.getInteger(zBuffer);
//			reading intensity
			byte[] intensityBuffer = new byte[2];
			dis.read(intensityBuffer);
			this.intensity = LittleEndianUtilities.getShort(intensityBuffer);
//			reading byte in with return number, number of returns and scan direction flag is held
			byte returnNumberByte = dis.readByte();
			int bit0 = (returnNumberByte >> 0) & 1;
			int bit1 = (returnNumberByte >> 1) & 1;
			int bit2 = (returnNumberByte >> 2) & 1;
			int bit3 = (returnNumberByte >> 3) & 1;
			int bit4 = (returnNumberByte >> 4) & 1;
			int bit5 = (returnNumberByte >> 5) & 1;
			int bit6 = (returnNumberByte >> 6) & 1;
			int bit7 = (returnNumberByte >> 7) & 1;
//			saving return number
			this.returnNumber = (short) Integer.parseInt(String.valueOf(bit2) + String.valueOf(bit1) + String.valueOf(bit0), 2);
//			saving number of returns
			this.numberReturns = (short) Integer.parseInt(String.valueOf(bit5) + String.valueOf(bit4) + String.valueOf(bit3), 2);
//			saving scan flag
			switch (bit6) {
			case 0:
				this.scanDiractionFlag = false;
				break;
			case 1:
				this.scanDiractionFlag = true;
				break;
			}
//			saving edge of flight line
			switch (bit7) {
			case 0:
				this.flightLineEndge = false;
				break;
			case 1:
				this.flightLineEndge = true;
				break;
			}
//			reading byte in with classification is held and its properties
			byte classification = dis.readByte();
			bit0 = (classification >> 0) & 1;
			bit1 = (classification >> 1) & 1;
			bit2 = (classification >> 2) & 1;
			bit3 = (classification >> 3) & 1;
			bit4 = (classification >> 4) & 1;
			bit5 = (classification >> 5) & 1;
			bit6 = (classification >> 6) & 1;
			bit7 = (classification >> 7) & 1;
//			saving classification 
			this.classification = Short.parseShort(String.valueOf(bit4) + String.valueOf(bit3) + String.valueOf(bit2) + String.valueOf(bit1) + String.valueOf(bit0), 2);
//			saving synthetic
			switch (bit5) {
			case 0:
				this.synthetic = false;
				break;
			case 1:
				this.synthetic = true;
				break;
			}
//			saving key - point
			switch (bit6) {
			case 0:
				this.keyPoint = false;
				break;
			case 1:
				this.keyPoint = true;
				break;
			}
//			saving withheld
			switch (bit7) {
			case 0:
				this.withheld = false;
				break;
			case 1:
				this.withheld = true;
				break;
			}
//			reading scan angle rank
			this.scanAngleRank = dis.readByte();
//			reading user data
			this.userData = dis.readByte();
//			reading point source ID
			byte[] pointSourceIDBuffer = new byte[2];
			dis.read(pointSourceIDBuffer);
			this.pointSourceID = LittleEndianUtilities.getShort(pointSourceIDBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getPointAsBytes() {
		System.out.println("nowy pkt");
		byte[] allBytes = new byte[owner.getHeader().getRecordsLength()];
		int index = 0;
		
		byte[] xBuffer = java.nio.ByteBuffer.allocate(4).putInt(x).array();
		for (int i = xBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = xBuffer[i];
			System.out.print("x ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] yBuffer = java.nio.ByteBuffer.allocate(4).putInt(y).array();
		for (int i = yBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = yBuffer[i];
			System.out.print("y ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] zBuffer = java.nio.ByteBuffer.allocate(4).putInt(z).array();
		for (int i = zBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = zBuffer[i];
			System.out.print("z ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] intensityBuffer = java.nio.ByteBuffer.allocate(2).putShort(intensity).array();
		for (int i = intensityBuffer.length - 1; i >= 0 ; i--) {
			allBytes[index] = intensityBuffer[i];
			System.out.print("source ID ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		String binery;
		
		byte[] returnNumberBuffer = java.nio.ByteBuffer.allocate(2).putShort(returnNumber).array();
		byte returnNumberByte = returnNumberBuffer[1];
		int bit0 = (returnNumberByte >> 0) & 1;
		int bit1 = (returnNumberByte >> 1) & 1;
		int bit2 = (returnNumberByte >> 2) & 1;
		int bit3 = (returnNumberByte >> 3) & 1;
		int bit4 = (returnNumberByte >> 4) & 1;
		int bit5 = (returnNumberByte >> 5) & 1;
		int bit6 = (returnNumberByte >> 6) & 1;
		int bit7 = (returnNumberByte >> 7) & 1;
		
		binery = String.valueOf(bit7) + String.valueOf(bit6) + String.valueOf(bit5);
		
		byte[] numberReturnsBuffer = java.nio.ByteBuffer.allocate(2).putShort(numberReturns).array();
		byte numberReturnsByte = returnNumberBuffer[1];
		
		bit5 = (numberReturnsByte >> 5) & 1;
		bit6 = (numberReturnsByte >> 6) & 1;
		bit7 = (numberReturnsByte >> 7) & 1;
		
		binery = String.valueOf(bit7) + String.valueOf(bit6) + String.valueOf(bit5);
		
		if (scanDiractionFlag) {
			binery += "1";
		} else {
			binery += "0";
		}
		
		if (flightLineEndge) {
			binery += "1";
		} else {
			binery += "0";
		}
		
		allBytes[index] = Byte.parseByte(binery, 2);
		index++;
		
		String bineryClassification;
		
		byte[] classificationBuffer = java.nio.ByteBuffer.allocate(2).putShort(classification).array();
		byte classificationByte = classificationBuffer[1];
		
		bit0 = (classificationByte >> 0) & 1;
		bit1 = (classificationByte >> 1) & 1;
		bit2 = (classificationByte >> 2) & 1;
		bit3 = (classificationByte >> 3) & 1;
		bit4 = (classificationByte >> 4) & 1;
		bit5 = (classificationByte >> 5) & 1;
		bit6 = (classificationByte >> 6) & 1;
		bit7 = (classificationByte >> 7) & 1;
		
		bineryClassification = String.valueOf(bit7) + String.valueOf(bit6) + String.valueOf(bit5) + String.valueOf(bit4); 
		
		if (synthetic) {
			bineryClassification += 1; 
		} else {
			bineryClassification += 0; 
		}
		
		if (keyPoint) {
			bineryClassification += 1; 
		} else {
			bineryClassification += 0; 
		}
		
		if (withheld) {
			bineryClassification += 1; 
		} else {
			bineryClassification += 0; 
		}
		
		allBytes[index] = Byte.parseByte(bineryClassification, 2);
		index++;
		
		byte[] scanAngleRankBuffer = java.nio.ByteBuffer.allocate(4).putInt(scanAngleRank).array();
		allBytes[index] = scanAngleRankBuffer[4];
		System.out.print("scanAngleRank ");
		System.out.format("0x%x", allBytes[index]);
		System.out.println(" : " + index);
		index++;
		
		byte[] userDataBuffer = java.nio.ByteBuffer.allocate(4).putInt(userData).array();
		allBytes[index] = userDataBuffer[4];
		System.out.print("scanAngleRank ");
		System.out.format("0x%x", allBytes[index]);
		System.out.println(" : " + index);
		index++;
		
		byte[] pointSourceIdBuffer = java.nio.ByteBuffer.allocate(2).putShort(pointSourceID).array();
		for (int i = pointSourceIdBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = pointSourceIdBuffer[i];
			System.out.print("pointSourceID ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		return allBytes;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public short getIntensity() {
		return intensity;
	}

	public void setIntensity(short intensity) {
		this.intensity = intensity;
	}

	public short getReturnNumber() {
		return returnNumber;
	}

	public void setReturnNumber(short returnNumber) {
		this.returnNumber = returnNumber;
	}

	public short getNumberReturns() {
		return numberReturns;
	}

	public void setNumberReturns(short numberReturns) {
		this.numberReturns = numberReturns;
	}

	public boolean isScanDiractionFlag() {
		return scanDiractionFlag;
	}

	public void setScanDiractionFlag(boolean scanDiractionFlag) {
		this.scanDiractionFlag = scanDiractionFlag;
	}

	public boolean isSynthetic() {
		return synthetic;
	}

	public void setSynthetic(boolean synthetic) {
		this.synthetic = synthetic;
	}

	public boolean isKeyPoint() {
		return keyPoint;
	}

	public void setKeyPoint(boolean keyPoint) {
		this.keyPoint = keyPoint;
	}

	public boolean isWithheld() {
		return withheld;
	}

	public void setWithheld(boolean withheld) {
		this.withheld = withheld;
	}

	public int getScanAngleRank() {
		return scanAngleRank;
	}

	public int getUserData() {
		return userData;
	}

	public void setUserData(char userData) {
		this.userData = userData;
	}

	public short getPointSourceID() {
		return pointSourceID;
	}

	public void setPointSourceID(short pointSourceID) {
		this.pointSourceID = pointSourceID;
	}

	public boolean isFlightLineEndge() {
		return flightLineEndge;
	}

	public void setFlightLineEndge(boolean flightLineEndge) {
		this.flightLineEndge = flightLineEndge;
	}

	public short getClassification() {
		return classification;
	}

	public void setClassification(short classification) {
		this.classification = classification;
	}
}
