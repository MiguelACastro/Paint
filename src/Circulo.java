import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circulo extends Pintable{
	private int x, y, radio;

	public Circulo(int x, int y, int radio, Color color, BasicStroke grosor) {
		super(color, grosor);
		this.x = x;
		this.y = y;
		this.radio = radio;
	}

	@Override
	public void pintar(Graphics2D g2d) {
		super.pintar(g2d);
		g2d.drawOval(x-radio/2, y-radio/2, radio, radio);
	}
	
}
