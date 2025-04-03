import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circulo extends Pintable{
	private int x, y, radio;

	public Circulo(int x, int y, int radio, Color color, BasicStroke grosor, boolean esRellena) {
		super(color, grosor, esRellena);
		this.x = x;
		this.y = y;
		this.radio = radio;
	}

	public Circulo(int x, int y, int radio, Color color, BasicStroke grosor) {
		this(x, y, radio, color, grosor, false);
	}
	
	@Override
	public void pintar(Graphics2D g2d) {
		super.pintar(g2d);
		if(esRellena) {			
			g2d.fillOval(x-radio/2, y-radio/2, radio, radio);
		} else {
			g2d.drawOval(x-radio/2, y-radio/2, radio, radio);			
		}
	}
	
}
