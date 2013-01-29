import processing.core.*;

public class Mapping extends PApplet {
	PImage mapImage;
	Table locationTable;
	int rowCount;

	public void setup() {
		size(640, 400);
		mapImage = loadImage("http://benfry.com/writing/map/map.png");
		
		// 各州の座標を保持するファイルをもとにデータの表を作成
		locationTable = new Table("http://benfry.com/writing/map/locations.tsv");
		// 行数は頻繁に使われるため，グローバル変数に保存
		rowCount = locationTable.getRowCount();
	}

	public void draw() {
		background(255);
		image(mapImage, 0, 0);
		
		// 楕円の描画属性
		smooth();
		fill(192, 0, 0);
		noStroke();
		
		// 位置ファイルの全行についてループして点を描く
		for( int row = 0; row < rowCount; row++)
		{
			float x = locationTable.getFloat(row, 1);
			float y = locationTable.getFloat(row, 2);
			ellipse(x, y, 9, 9);
		}
	}
}