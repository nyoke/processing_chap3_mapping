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

		// �e�B�̍��W��ێ�����t�@�C�������ƂɃf�[�^�̕\���쐬
		locationTable = new Table("http://benfry.com/writing/map/locations.tsv");
		// �s���͕p�ɂɎg���邽�߁C�O���[�o���ϐ��ɕۑ�
		rowCount = locationTable.getRowCount();

		// �f�[�^�\�ɓǂݍ���
		dataTable = new Table("http://benfry.com/writing/map/random.tsv");

		// �ő�l�ƍŏ��l�����߂�
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

		// �ȉ~�̕`�摮��
		smooth();
		fill(192, 0, 0);
		noStroke();

		// �ʒu�t�@�C���̑S�s�ɂ��ă��[�v���ē_��`��
		for (int row = 0; row < rowCount; row++) {
			String abbrev = dataTable.getRowName(row);
			float x = locationTable.getFloat(row, 1);
			float y = locationTable.getFloat(row, 2);
			drawData(x, y, abbrev);
		}
	}

	void drawData(float x, float y, String abbrev) {
		// �B�̃f�[�^�l���擾
		float value = dataTable.getFloat(abbrev, 1);
		
		//�@�f�[�^�l��2����40�̊Ԃ̐��Ƀ��}�b�v
		float mapped = map(value, dataMin, dataMax, 2, 40);
		
		// �ȉ~��`��
		// ����1 �ȉ~�̑傫���Ń}�b�v
		//ellipse(x, y, mapped, mapped);
		
		// ����2 �ȉ~�̐F��2�F�Ԃŕω�
		float percent = norm(value, dataMin, dataMax);
		// RGB�̏ꍇ�i�ω����傫���ꍇ�ɂ͂��������g���j
		// int between = lerpColor(color(255, 0, 0), color(0, 0, 255) , percent);
		// HSB���g���ꍇ�i�ω����������ꍇ�ɂ͂������̕����悢�j
		int between = lerpColor(color(255, 0, 0), color(0, 0, 255) , percent, HSB);

		fill(between);
		ellipse(x, y, 15, 15);
		
	}
}