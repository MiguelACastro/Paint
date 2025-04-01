import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Cuadrado extends Pintable{

	private int x, y, width, height;

	public Cuadrado(int x, int y, int width, int height, Color color, BasicStroke grosor) {
		super(color, grosor);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void pintar(Graphics2D g2d) {
		super.pintar(g2d);
		g2d.drawRect(x-width/2, y-height/2, width, height);
	}
	
}
