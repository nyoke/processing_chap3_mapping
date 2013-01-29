import processing.core.*;

public class Mapping extends PApplet {
	PImage mapImage;

	public void setup() {
		size(640, 400);
		mapImage = loadImage("http://benfry.com/writing/map/map.png");

	}

	public void draw() {
		background(255);
		image(mapImage, 0, 0);
	}
}