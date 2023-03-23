package lasMain.lasFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import lasMain.lasFile.lasPoints.LasPoint0;
import lasMain.lasFile.lasPoints.LasPoint1;
import lasMain.lasFile.lasPoints.LasPoint2;
import lasMain.lasFile.lasPoints.LasPoint3;

public abstract class LasFile {
	protected String filepath;
	protected File file;
	
	protected Header12 header;
	protected List<VariableLengthRecords> variable = new ArrayList<VariableLengthRecords>();
	protected List<LasPoint0> pointList = new ArrayList<LasPoint0>();
	
	public LasFile(String filepath) {
		this.filepath = filepath;
		this.file = new File(filepath);
	}
	
	public void loadFile() {
		loadHeader();
		loadPoints();
	}
	
	public void loadHeader() {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
//			reading header
			this.header = new Header12(dis);
			dis.skipBytes(2);
//			reading veriableLeanghtRecords
			for (int i = 0; i < header.getVaraibleLengthRecords(); i++) {
				variable.add(new VariableLengthRecords(dis));
			}
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadPoints() {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			dis.skip(header.getOffsetPoint());
//			reading points
			for (int i = 0; i < header.getNumberPointRecords(); i++) {
				switch (header.getFormatId()) {
				case 0:
//					creating points when format id is 0
					pointList.add(new LasPoint0(dis, this));
					break;
				case 1:
//					creating points when format id is 1
					pointList.add(new LasPoint1(dis, this));
					break;
				case 2:
//					creating points when format id is 2
					pointList.add(new LasPoint2(dis, this));
					break;
				case 3:
//					creating points when format id is 3
					pointList.add(new LasPoint3(dis, this));
					break;
				}
			}
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadPoint(int point) {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			dis.skip(header.getOffsetPoint());
			dis.skip(point * header.getRecordsLength());
//			reading points
			for (int i = 0; i < header.getNumberPointRecords(); i++) {
				switch (header.getFormatId()) {
				case 0:
//					creating points when format id is 0
					pointList.add(new LasPoint0(dis, this));
					break;
				case 1:
//					creating points when format id is 1
					pointList.add(new LasPoint1(dis, this));
					break;
				case 2:
//					creating points when format id is 2
					pointList.add(new LasPoint2(dis, this));
					break;
				case 3:
//					creating points when format id is 3
					pointList.add(new LasPoint3(dis, this));
					break;
				}
			}
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract boolean write(String filepath);

	public String getFilepath() {
		return filepath;
	}

	public File getFile() {
		return file;
	}

	public Header12 getHeader() {
		return header;
	}

	public List<VariableLengthRecords> getVariable() {
		return variable;
	}

	public List<LasPoint0> getPointList() {
		return pointList;
	}
}
