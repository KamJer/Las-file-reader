package lasMain.lasFile;

import java.io.FileOutputStream;
import java.util.List;

import lasMain.lasFile.lasPoints.LasPoint0;

public class LasFile12 extends LasFile{
	public LasFile12(String filepath) {
		super(filepath);
	}
	
	@Override
	public boolean write(String filepath) {
		try (FileOutputStream fos = new FileOutputStream(filepath)){
			fos.write(header.getHeaderAsBytes());
			for (int i = 0; i < pointList.size(); i++) {
				fos.write(pointList.get(i).getPointAsBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void add(LasPoint0 point) {
		this.pointList.add(point);
	}

	public Header12 getHeader() {
		return header;
	}

	public void setHeader(Header12 header) {
		this.header = header;
	}

	public List<LasPoint0> getPointList() {
		return pointList;
	}

	public void setPointList(List<LasPoint0> pointList) {
		this.pointList = pointList;
	}

	public String getFilepath() {
		return filepath;
	}
}
