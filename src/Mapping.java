import processing.core.*;

public class Mapping extends PApplet {
	PImage mapImage;
	Table locationTable;
	int rowCount;

	public void setup() {
		size(640, 400);
		mapImage = loadImage("http://benfry.com/writing/map/map.png");
		
		// �e�B�̍��W��ێ�����t�@�C�������ƂɃf�[�^�̕\���쐬
		locationTable = new Table("http://benfry.com/writing/map/locations.tsv");
		// �s���͕p�ɂɎg���邽�߁C�O���[�o���ϐ��ɕۑ�
		rowCount = locationTable.getRowCount();
	}

	public void draw() {
		background(255);
		image(mapImage, 0, 0);
		
		// �ȉ~�̕`�摮��
		smooth();
		fill(192, 0, 0);
		noStroke();
		
		// �ʒu�t�@�C���̑S�s�ɂ��ă��[�v���ē_��`��
		for( int row = 0; row < rowCount; row++)
		{
			float x = locationTable.getFloat(row, 1);
			float y = locationTable.getFloat(row, 2);
			ellipse(x, y, 9, 9);
		}
	}
}