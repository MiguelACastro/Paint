import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Linea extends Pintable{
	private int x1, y1, x2, y2;

	public Linea(int x1, int y1, int x2, int y2, Color color, BasicStroke grosor) {
		super(color, grosor);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void pintar(Graphics2D g2d) {
		super.pintar(g2d);
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	

}