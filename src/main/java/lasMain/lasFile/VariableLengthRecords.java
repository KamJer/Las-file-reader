package lasMain.lasFile;

import java.io.DataInputStream;
import java.io.IOException;

import endianUtilities.LittleEndianUtilities;

public class VariableLengthRecords {
	private byte[] reserved;
	private char[] userID;
	private short recordID;
	private short lengthAfterHeader;
	private char[] description;
	
	public VariableLengthRecords(DataInputStream dis) {
		try {
//			reading reserved
			this.reserved = new byte[2];
			for (int i = 0; i < description.length; i++) {
				this.reserved[i] = dis.readByte();
			}
//			reading user ID
			this.userID = new char[16];
			for (int i = 0; i < userID.length; i++) {
				userID[i] = (char) dis.readByte();
			}
//			reading record ID
			byte[] recordIDBuffer = new byte[2];
			dis.read(recordIDBuffer);
			this.recordID = LittleEndianUtilities.getShort(recordIDBuffer);
//			reading record Length after header
			byte[] lengthAfterHeaderBuffer = new byte[2];
			dis.read(lengthAfterHeaderBuffer);
			this.lengthAfterHeader = LittleEndianUtilities.getShort(lengthAfterHeaderBuffer);
//			reading description
			this.description = new char[32];
			for (int i = 0; i < description.length; i++) {
				this.description[i] = (char) dis.readByte();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getReserved() {
		return reserved;
	}

	public void setReserved(byte[] reserved) {
		this.reserved = reserved;
	}
	
	public char[] getUserID() {
		return userID;
	}

	public void setUserID(char[] userID) {
		this.userID = userID;
	}

	public short getRecordID() {
		return recordID;
	}

	public void setRecordID(short recordID) {
		this.recordID = recordID;
	}

	public short getLengthAfterHeader() {
		return lengthAfterHeader;
	}

	public void setLengthAfterHeader(short lengthAfterHeader) {
		this.lengthAfterHeader = lengthAfterHeader;
	}

	public char[] getDescription() {
		return description;
	}

	public void setDescription(char[] description) {
		this.description = description;
	}
}
