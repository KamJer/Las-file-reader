package lasMain.lasFile;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import endianUtilities.LittleEndianUtilities;

public class Header12 {
	private char[] fileSignature;
	private short sourceID;
	private short globalEncoding;
	private int data1;
	private short data2;
	private short data3;
	private char[] data4;
	private byte vM;
	private byte vm;
	private char[] systemID;
	private char[] generatingSoftware;
	private short day;
	private short year;
	private short headerSize;
	private int offsetPoint;
	private int lengthRecords;
	private byte formatId;
	private short recordsLength;
	private int numberPointRecords;
	private int[] numberPointsbyReturn;

	private double xScale;
	private double yScale;
	private double zScale;

	private double xOffset;
	private double yOffset;
	private double zOffset;

	private double minX;
	private double maxX;

	private double maxY;
	private double minY;

	private double maxZ;
	private double minZ;

	public Header12(DataInputStream dis) {
		try {
//			saving file signature
			this.fileSignature = new char[4];
			for (int i = 0; i < fileSignature.length; i++) {
				this.fileSignature[i] = (char) dis.readByte();
			}
			System.out.println(fileSignature[0] + " : " + fileSignature[1] + " : " + fileSignature[2] + " : " + fileSignature[3]);
//			saving file source ID
			byte[] sourceIDBuffer = new byte[2];
			dis.read(sourceIDBuffer);
			this.sourceID = LittleEndianUtilities.getShort(sourceIDBuffer);
			System.out.println("sourceID " + sourceID);
//			reading global encoding
			byte[] globalEncodingBuffer = new byte[2];
			dis.read(globalEncodingBuffer);
			this.globalEncoding = LittleEndianUtilities.getShort(globalEncodingBuffer);
			System.out.println("globalEncoding " + globalEncoding);
//			reading GUID data 1
			byte[] data1Buffer = new byte[4];
			dis.read(data1Buffer);
			this.data1 = LittleEndianUtilities.getInteger(data1Buffer);
			System.out.println("data1 " + data1);
//			reading GUID data 2
			byte[] data2Buffer = new byte[2];
			dis.read(data2Buffer);
			this.data2 = LittleEndianUtilities.getShort(data2Buffer);
			System.out.println("data2 " + data2);
//			reading GUID data 3
			byte[] data3Buffer = new byte[2];
			dis.read(data3Buffer);
			this.data3 = LittleEndianUtilities.getShort(data3Buffer);
			System.out.println("data3 " + data3);
//			reading GUID data 4
			this.data4 = new char[8];
			for (int i = 0; i < data4.length; i++) {
				this.data4[i] = (char) dis.readByte();
				System.out.print("data4 " + data4[i]);
			}
			System.out.println();
//			reading Version Major
			this.vM = dis.readByte();
			System.out.println("vM " + vM);
//			reading Version minor
			this.vm = dis.readByte();
			System.out.println("vm " + vm);
//			reading system identifier
			this.systemID = new char[32];
			for (int i = 0; i < systemID.length; i++) {
				systemID[i] = (char) dis.readByte();
				System.out.print("systemID" + systemID[i]);
			}
//			reading generating software
			System.out.println();
			this.generatingSoftware = new char[32];
			for (int i = 0; i < generatingSoftware.length; i++) {
				generatingSoftware[i] = (char) dis.readByte();
				System.out.print("generatingSoftware " + generatingSoftware[i]);
			}
			System.out.println();
//			reading day
			byte[] dayBuffer = new byte[2];
			dis.read(dayBuffer);
			this.day = LittleEndianUtilities.getShort(dayBuffer);
			System.out.println("day " + day);
//			reading year
			byte[] yearBuffer = new byte[2];
			dis.read(yearBuffer);
			this.year = LittleEndianUtilities.getShort(yearBuffer);
			System.out.println("year " + year);
//			reading header size
			byte[] headerSizeBuffer = new byte[2];
			dis.read(headerSizeBuffer);
			this.headerSize = LittleEndianUtilities.getShort(headerSizeBuffer);
			System.out.println("headersize " + headerSize);
//			reading offset to point data
			byte[] offsetPointBuffer = new byte[4];
			dis.read(offsetPointBuffer);
			this.offsetPoint = LittleEndianUtilities.getInteger(offsetPointBuffer);
			System.out.println("offsetPoint " + offsetPoint);
//			reading Number of Variable Length Records 
			byte[] lengthRecordsBuffer = new byte[4];
			dis.read(lengthRecordsBuffer);
			this.lengthRecords = LittleEndianUtilities.getInteger(lengthRecordsBuffer);
			System.out.println("lengthRecords " + lengthRecords);
//			reading Point Data Format ID (0-99 for spec) 
			this.formatId = dis.readByte();
			System.out.println("formatId " + formatId);
//			reading Point Data Record Length
			byte[] recordsLengthBuffer = new byte[2];
			dis.read(recordsLengthBuffer);
			this.recordsLength = LittleEndianUtilities.getShort(recordsLengthBuffer);
			System.out.println("recordsLength " + recordsLength);
//			reading Number of point records
			byte[] numberPointRecordsBuffer = new byte[4];
			dis.read(numberPointRecordsBuffer);
			this.numberPointRecords = LittleEndianUtilities.getInteger(numberPointRecordsBuffer);
			System.out.println("numberPointRecords " + numberPointRecords);
//			reading Number of points by return
			this.numberPointsbyReturn = new int[5];
			for (int i = 0; i < numberPointsbyReturn.length; i++) {
				byte[] numberPointsbyReturnBuffer = new byte[4];
				dis.read(numberPointsbyReturnBuffer);
				this.numberPointsbyReturn[i] = LittleEndianUtilities.getInteger(numberPointsbyReturnBuffer);
				System.out.println("numberPointsbyReturn " + i + " " + numberPointsbyReturn[i]);
			}
//			reading X scale factor
			byte[] xScaleBuffer = new byte[8];
			dis.read(xScaleBuffer);
			this.xScale = LittleEndianUtilities.littleEndianDouble(xScaleBuffer);
			System.out.println("xScale " + xScale);
//			reading Y scale factor
			byte[] yScaleBuffer = new byte[8];
			dis.read(yScaleBuffer);
			this.yScale = LittleEndianUtilities.littleEndianDouble(yScaleBuffer);
			System.out.println("yScale " + yScale);
//			reading Z scale factor
			byte[] zScaleBuffer = new byte[8];
			dis.read(zScaleBuffer);
			this.zScale = LittleEndianUtilities.littleEndianDouble(zScaleBuffer);
			System.out.println("zScale " + zScale);
			
//			reading X offset
			byte[] xOffsetBuffer = new byte[8];
			dis.read(xOffsetBuffer);
			this.xOffset = LittleEndianUtilities.littleEndianDouble(xOffsetBuffer);
			System.out.println("xOffset " + xOffset);
//			reading Y offset
			byte[] yOffsetBuffer = new byte[8];
			dis.read(yOffsetBuffer);
			this.yOffset = LittleEndianUtilities.littleEndianDouble(yOffsetBuffer);
			System.out.println("yOffset " + yOffset);
//			reading Z offset
			byte[] zOffsetBuffer = new byte[8];
			dis.read(zOffsetBuffer);
			this.zOffset = LittleEndianUtilities.littleEndianDouble(zOffsetBuffer);
			System.out.println("zOffset " + zOffset);
			
//			reading Max X
			byte[] maxXBuffers = new byte[8];
			dis.read(maxXBuffers);
			this.maxX = LittleEndianUtilities.littleEndianDouble(maxXBuffers);
			System.out.println("maxX " + maxX);
//			reading Min X
			byte[] minXBuffers = new byte[8];
			dis.read(minXBuffers);
			this.minX = LittleEndianUtilities.littleEndianDouble(minXBuffers);
			System.out.println("minX " + minX);
//			reading Max Y
			byte[] maxYBuffer = new byte[8];
			dis.read(maxYBuffer);
			this.maxY = LittleEndianUtilities.littleEndianDouble(maxYBuffer);
			System.out.println("minX " + maxY);
//			reading Max Y
			byte[] minYBuffer = new byte[8];
			dis.read(minYBuffer);
			this.minY = LittleEndianUtilities.littleEndianDouble(minYBuffer);
			System.out.println("minX " + minY);
//			reading Max Z
			byte[] maxZBuffer = new byte[8];
			dis.read(maxZBuffer);
			this.maxZ = LittleEndianUtilities.littleEndianDouble(maxZBuffer);
			System.out.println("maxZ " + maxZ);
//			reading Max Z
			byte[] minZBuffer = new byte[8];
			dis.read(minZBuffer);
			this.minZ = LittleEndianUtilities.littleEndianDouble(minZBuffer);
			System.out.println("minZ " + minZ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getHeaderAsBytes() {
		byte[] allBytes = new byte[headerSize];
		int index = 0;
				
		for (int i = 0; i < fileSignature.length; i++) {
			byte[] fileSignatureBuffer = ByteBuffer.allocate(2).putChar(fileSignature[i]).array();
			for (int j = 1; j < fileSignatureBuffer.length; j++) {
				allBytes[index] = fileSignatureBuffer[j];
				System.out.print("file signature");
				System.out.format("0x%x", allBytes[index]);
				System.out.println(" : " + index);
				index++;
			}
		}
		
		byte[] sourceIDBuffer = java.nio.ByteBuffer.allocate(2).putShort(sourceID).array();
		for (int i = sourceIDBuffer.length - 1; i >= 0 ; i--) {
			allBytes[index] = sourceIDBuffer[i];
			System.out.print("source ID ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] globalEncodingBuffer = java.nio.ByteBuffer.allocate(2).putShort(globalEncoding).array();
		for (int i = globalEncodingBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = globalEncodingBuffer[i];
			System.out.print("global encoding ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] data1Buffer = java.nio.ByteBuffer.allocate(4).putInt(data1).array();
		for (int i = data1Buffer.length - 1; i >= 0; i--) {
			allBytes[index] = data1Buffer[i];
			System.out.print("data1 ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] data2Buffer = java.nio.ByteBuffer.allocate(2).putShort(data2).array();
		for (int i = data2Buffer.length - 1; i >= 0; i--) {
			allBytes[index] = data2Buffer[i];
			System.out.print("data2 ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] data3Buffer = java.nio.ByteBuffer.allocate(2).putShort(data3).array();
		for (int i = data3Buffer.length - 1; i >= 0; i--) {
			allBytes[index] = data3Buffer[i];
			System.out.print("data3 ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		for (int i = 0; i < data4.length; i++) {
			byte[] data4Buffer = java.nio.ByteBuffer.allocate(2).putChar(data4[i]).array();
			for (int j = data4Buffer.length - 1; j >= 1; j--) {
				allBytes[index] = data4Buffer[j];
				System.out.print("data4 ");
				System.out.format("0x%x", allBytes[index]);
				System.out.println(" : " + index);
				index++;
			}
		}
		
		allBytes[index] = vM;
		System.out.print("vM ");
		System.out.format("0x%x", allBytes[index]);
		System.out.println(" : " + index);
		index++;
		
		allBytes[index] = vm;
		System.out.print("vm ");
		System.out.format("0x%x", allBytes[index]);
		System.out.println(" : " + index);
		index++;
		
		for (int i = 0; i < systemID.length; i++) {
			byte[] systemIDBuffer = java.nio.ByteBuffer.allocate(2).putChar(systemID[i]).array();
			for (int j = systemIDBuffer.length - 1; j >= 1; j--) {
				allBytes[index] = systemIDBuffer[j];
				System.out.print("System ID ");
				System.out.format("0x%x", allBytes[index]);
				System.out.println(" : " + index);
				index++;
			}
		}
		
		for (int i = 0; i < generatingSoftware.length; i++) {
			byte[] generatingSoftwareBuffer = java.nio.ByteBuffer.allocate(2).putChar(generatingSoftware[i]).array();
			for (int j = generatingSoftwareBuffer.length - 1; j >= 1; j--) {
				allBytes[index] = generatingSoftwareBuffer[j];
				System.out.print("System ID ");
				System.out.format("0x%x", allBytes[index]);
				System.out.println(" : " + index);
				index++;
			}
		}
		
		byte[] dayBuffer = java.nio.ByteBuffer.allocate(2).putShort(day).array();
		for (int i = dayBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = dayBuffer[i];
			System.out.print("day ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] yearBuffer = java.nio.ByteBuffer.allocate(2).putShort(year).array();
		for (int i = yearBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = yearBuffer[i];
			System.out.print("year ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] headerSizeBuffer = java.nio.ByteBuffer.allocate(2).putShort(headerSize).array();
		for (int i = headerSizeBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = headerSizeBuffer[i];
			System.out.print("headerSize ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		byte[] offsetPointBuffer = java.nio.ByteBuffer.allocate(4).putInt(offsetPoint).array();
		for (int i = offsetPointBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = offsetPointBuffer[i];
			System.out.print("offsetPoint ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] lengthRecordsBuffer = java.nio.ByteBuffer.allocate(4).putInt(lengthRecords).array();
		for (int i = lengthRecordsBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = lengthRecordsBuffer[i];
			System.out.print("lengthRecords ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		allBytes[index] = formatId;
		System.out.print("formatId ");
		System.out.format("0x%x", allBytes[index]);
		System.out.println(" : " + index);
		index++;
		
		byte[] recordsLengthBuffer = java.nio.ByteBuffer.allocate(2).putShort(recordsLength).array();
		for (int i = recordsLengthBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = recordsLengthBuffer[i];
			System.out.print("recordsLength ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] numberPointRecordsBuffer = java.nio.ByteBuffer.allocate(4).putInt(numberPointRecords).array();
		for (int i = numberPointRecordsBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = numberPointRecordsBuffer[i];
			System.out.print("numberPointRecords ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		for (int i = 0; i < numberPointsbyReturn.length; i++) {
			byte[] numberPointsbyReturnBuffer = java.nio.ByteBuffer.allocate(4).putInt(numberPointsbyReturn[i]).array();
			for (int j = numberPointsbyReturnBuffer.length - 1; j >= 0; j--) {
				allBytes[index] = numberPointsbyReturnBuffer[j];
				System.out.print("numberPointsbyReturn ");
				System.out.format("0x%x", allBytes[index]);
				System.out.println(" : " + index);
				index++;
			}
		}
		
		byte[] xScaleBuffer = java.nio.ByteBuffer.allocate(8).putDouble(xScale).array();
		for (int i = xScaleBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = xScaleBuffer[i];
			System.out.print("xScale ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] yScaleBuffer = java.nio.ByteBuffer.allocate(8).putDouble(yScale).array();
		for (int i = yScaleBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = yScaleBuffer[i];
			System.out.print("yScale ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] zScaleBuffer = java.nio.ByteBuffer.allocate(8).putDouble(zScale).array();
		for (int i = zScaleBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = zScaleBuffer[i];
			System.out.print("zScale ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] xOffsetBuffer = java.nio.ByteBuffer.allocate(8).putDouble(xOffset).array();
		for (int i = xOffsetBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = xOffsetBuffer[i];
			System.out.print("xOffset ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] yOffsetBuffer = java.nio.ByteBuffer.allocate(8).putDouble(yOffset).array();
		for (int i = yOffsetBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = yOffsetBuffer[i];
			System.out.print("yOffset ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] zOffsetBuffer = java.nio.ByteBuffer.allocate(8).putDouble(zOffset).array();
		for (int i = zOffsetBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = zOffsetBuffer[i];
			System.out.print("zOffset ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] maxXBuffer = java.nio.ByteBuffer.allocate(8).putDouble(maxX).array();
		for (int i = maxXBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = maxXBuffer[i];
			System.out.print("maxX ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] minXBuffer = java.nio.ByteBuffer.allocate(8).putDouble(minX).array();
		for (int i = minXBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = minXBuffer[i];
			System.out.print("minX ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] maxYBuffer = java.nio.ByteBuffer.allocate(8).putDouble(maxY).array();
		for (int i = maxYBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = maxYBuffer[i];
			System.out.print("maxY ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] minYBuffer = java.nio.ByteBuffer.allocate(8).putDouble(minY).array();
		for (int i = minYBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = minYBuffer[i];
			System.out.print("minY ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] maxZBuffer = java.nio.ByteBuffer.allocate(8).putDouble(maxZ).array();
		for (int i = maxZBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = maxZBuffer[i];
			System.out.print("maxZ ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		
		byte[] minZBuffer = java.nio.ByteBuffer.allocate(8).putDouble(minZ).array();
		for (int i = minZBuffer.length - 1; i >= 0; i--) {
			allBytes[index] = minZBuffer[i];
			System.out.print("minZ ");
			System.out.format("0x%x", allBytes[index]);
			System.out.println(" : " + index);
			index++;
		}
		return allBytes;
	}
	
	/**
	 * File Signature: The file signature must contain the four characters “LASF”, and it is required by 
	 * the LAS specification. These four characters can be checked by user software as a quick look initial determination of file type.
	 * <From: https://www.asprs.org/divisions-committees/lidar-division/laser-las-file-format-exchange-activities> 
	 * @return file signature
	 */
	public char[] getFileSignature() {
		return fileSignature;
	}
	
	/**
	 * sets file signature 
	 * @param fileSignature - signature to set file signature 
	 */
	public void setFileSignature(char[] fileSignature) {
		this.fileSignature = fileSignature;
	}
	
	/**
	 * File Source ID (Flight Line Number if this file was derived from an original flight line): This field should be set to a value between 1 and 65,535, inclusive. 
	 * A value of zero (0) is interpreted to mean that an ID has not been assigned. In this case, processing software is free to assign any valid number. Note that this scheme allows a LIDAR project to contain up to 65,535 unique sources. A source can be considered an original flight line or it can be the result of merge and/or extract operations. 
	 * <From: https://www.asprs.org/divisions-committees/lidar-division/laser-las-file-format-exchange-activities> 
	 * @return source ID
	 */
	public short getSourceID() {
		return sourceID;
	}
	
	
	public void setSourceID(short sourceID) {
		this.sourceID = sourceID;
	}
	
	/**
	 * Global Encoding: This is a bit field used to indicate certain global properties about the file. In LAS 1.2 (the version in which this field was introduced), only the low bit is defined (this is the bit, that if set, would have the unsigned integer yield a value of 1). 
	 * <From: https://www.asprs.org/divisions-committees/lidar-division/laser-las-file-format-exchange-activities>
	 * @return
	 */
	public short getGlobalEncoding() {
		return globalEncoding;
	}

	public void setGlobalEncoding(short globalEncoding) {
		this.globalEncoding = globalEncoding;
	}
	
	/**
	 * Project ID (GUID data): The four fields that comprise a complete Globally Unique Identifier (GUID) are now reserved for use as a Project Identifier (Project ID). The field remains optional. The time of assignment of the Project ID is at the discretion of processing software. The Project ID should be the same for all files that are associated with a unique project. By assigning a Project ID and using a File Source ID (defined above) every file within a project and every point within a file can be uniquely identified, globally. 
	 * @return first GUID data 
	 */
	public int getGUIDData1() {
		return data1;
	}
	
	
	/**
	 * Project ID (GUID data): The four fields that comprise a complete Globally Unique Identifier (GUID) are now reserved for use as a Project Identifier (Project ID). The field remains optional. The time of assignment of the Project ID is at the discretion of processing software. The Project ID should be the same for all files that are associated with a unique project. By assigning a Project ID and using a File Source ID (defined above) every file within a project and every point within a file can be uniquely identified, globally. 
	 * @return second GUID data 
	 */
	public short getGUIDData2() {
		return data2;
	}

	public void setGUIDData2(short data2) {
		this.data2 = data2;
	}
	
	/**
	 * Project ID (GUID data): The four fields that comprise a complete Globally Unique Identifier (GUID) are now reserved for use as a Project Identifier (Project ID). The field remains optional. The time of assignment of the Project ID is at the discretion of processing software. The Project ID should be the same for all files that are associated with a unique project. By assigning a Project ID and using a File Source ID (defined above) every file within a project and every point within a file can be uniquely identified, globally. 
	 * @return third GUID data 
	 */
	public short getGUIDData3() {
		return data3;
	}

	public void setGUIDData3(short data3) {
		this.data3 = data3;
	}
	
	/**
	 * Project ID (GUID data): The four fields that comprise a complete Globally Unique Identifier (GUID) are now reserved for use as a Project Identifier (Project ID). The field remains optional. The time of assignment of the Project ID is at the discretion of processing software. The Project ID should be the same for all files that are associated with a unique project. By assigning a Project ID and using a File Source ID (defined above) every file within a project and every point within a file can be uniquely identified, globally. 
	 * @return forth GUID data 
	 */
	public char[] getGUIDData4() {
		return data4;
	}
	
	public void setGUIDData4(char[] data4) {
		this.data4 = data4;
	}
	
	/**
	 * Version Number: The version number consists of a major and minor field. The major and minor fields combine to form the number that indicates the format number of the current specification itself. For example, specification number 1.2 (this version) would contain 1 in the major field and 2 in the minor field. 
	 * @return
	 */
	public byte getVersionMajor() {
		return vM;
	}

	public void setVersionMajor(byte vM) {
		this.vM = vM;
	}

	public byte getVersionMinor() {
		return vm;
	}

	public void setVersionMinor(byte vm) {
		this.vm = vm;
	}

	public char[] getSystemID() {
		return systemID;
	}

	public void setSystemID(char[] systemID) {
		this.systemID = systemID;
	}

	public char[] getGeneratingSoftware() {
		return generatingSoftware;
	}

	public void setGeneratingSoftware(char[] generatingSoftware) {
		this.generatingSoftware = generatingSoftware;
	}

	public short getDay() {
		return day;
	}

	public void setDay(short day) {
		this.day = day;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

//	public int getHeaderSize() {
//		return headerSize;
//	}
//
//	public void setHeaderSize(short headerSize) {
//		this.headerSize = headerSize;
//	}

	public long getOffsetPoint() {
		return offsetPoint;
	}

	public void setOffsetPoint(int offsetPoint) {
		this.offsetPoint = offsetPoint;
	}

	public long getVaraibleLengthRecords() {
		return lengthRecords;
	}

	public void setVaraibleLengthRecords(int lengthRecords) {
		this.lengthRecords = lengthRecords;
	}

	public byte getFormatId() {
		return formatId;
	}

	public void setFormatId(byte formatId) {
		this.formatId = formatId;
	}

	public short getRecordsLength() {
		return recordsLength;
	}

	public void setRecordsLength(short recordsLength) {
		this.recordsLength = recordsLength;
	}

	public long getNumberPointRecords() {
		return numberPointRecords;
	}

	public void setNumberPointRecords(int numberPointRecords) {
		this.numberPointRecords = numberPointRecords;
	}

	public int[] getNumberPointsbyReturn() {
		return numberPointsbyReturn;
	}

	public double getxScale() {
		return xScale;
	}

	public void setxScale(double xScale) {
		this.xScale = xScale;
	}

	public double getyScale() {
		return yScale;
	}

	public void setyScale(double yScale) {
		this.yScale = yScale;
	}

	public double getzScale() {
		return zScale;
	}

	public void setzScale(double zScale) {
		this.zScale = zScale;
	}

	public double getxOffset() {
		return xOffset;
	}

	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public double getyOffset() {
		return yOffset;
	}

	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	public double getzOffset() {
		return zOffset;
	}

	public void setzOffset(double zOffset) {
		this.zOffset = zOffset;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxZ() {
		return maxZ;
	}

	public void setMaxZ(double maxZ) {
		this.maxZ = maxZ;
	}

	public double getMinZ() {
		return minZ;
	}

	public void setMinZ(double minZ) {
		this.minZ = minZ;
	}

	public long getData1() {
		return data1;
	}

	public short getData2() {
		return data2;
	}

	public short getData3() {
		return data3;
	}

	public char[] getData4() {
		return data4;
	}

	public byte getvM() {
		return vM;
	}

	public byte getVm() {
		return vm;
	}

	public int getHeaderSize() {
		return headerSize;
	}

	public long getLengthRecords() {
		return lengthRecords;
	}
}
