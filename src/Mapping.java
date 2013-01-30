import processing.core.*;

public class Mapping extends PApplet {
	PImage mapImage;
	Table locationTable;
	int rowCount;

	Table dataTable;
	float dataMin = MAX_FLOAT;
	float dataMax = MIN_FLOAT;

	public void setup() {
		size(640, 400);
		PFont font = loadFont("Serif-12.vlw");
		textFont(font);
		mapImage = loadImage("http://benfry.com/writing/map/map.png");

		// 各州の座標を保持するファイルをもとにデータの表を作成
		locationTable = new Table("http://benfry.com/writing/map/locations.tsv");
		// 行数は頻繁に使われるため，グローバル変数に保存
		rowCount = locationTable.getRowCount();

		// データ表に読み込む
		dataTable = new Table("http://benfry.com/writing/map/random.tsv");

		// 最大値と最小値を求める
		for (int row = 0; row < rowCount; row++) {
			float value = dataTable.getFloat(row, 1);
			if (value > dataMax) {
				dataMax = value;
			}
			if (value < dataMin) {
				dataMin = value;
			}
		}
	}

	public void draw() {
		background(255);
		image(mapImage, 0, 0);

		// 楕円の描画属性
		smooth();
		fill(192, 0, 0);
		noStroke();

		// 位置ファイルの全行についてループして点を描く
		for (int row = 0; row < rowCount; row++) {
			String abbrev = dataTable.getRowName(row);
			float x = locationTable.getFloat(row, 1);
			float y = locationTable.getFloat(row, 2);
			drawData(x, y, abbrev);
		}
	}

	void drawData(float x, float y, String abbrev) {
		// 州のデータ値を取得
		float value = dataTable.getFloat(abbrev, 1);
		float radius = 0;
		
		if(value >= 0)
		{
			radius = map(value, 0, dataMax, 1.5f, 15);
			fill(color(0, 0, 255));
		}
		else
		{
			 radius = map(value, 0, dataMin, 1.5f, 15);
			fill(color(255, 0, 0));
		}
		ellipseMode(RADIUS);
		ellipse(x, y, radius, radius);
		
		if(dist(x, y, mouseX, mouseY) < radius + 2)
		{
			fill(0);
			textAlign(CENTER);
			
			text(value + " (" + abbrev + ")", x, y -radius - 4);
		}
		
	}
}