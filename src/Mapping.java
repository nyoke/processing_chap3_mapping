import java.awt.Color;

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
		
		//　データ値を2から40の間の数にリマップ
		float mapped = map(value, dataMin, dataMax, 2, 40);
		
		// 楕円を描画
		// その1 楕円の大きさでマップ
		//ellipse(x, y, mapped, mapped);
		
		// その2 楕円の色を2色間で変化
		float percent = norm(value, dataMin, dataMax);
		// RGBの場合（変化が大きい場合にはこっちを使う）
		// int between = lerpColor(color(255, 0, 0), color(0, 0, 255) , percent);
		// HSBを使う場合（変化が小さい場合にはこっちの方がよい）
		int between = lerpColor(color(255, 0, 0), color(0, 0, 255) , percent, HSB);

		fill(between);
		ellipse(x, y, 15, 15);
		
	}
}