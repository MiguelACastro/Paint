import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	
	private ArrayList<Trazo> trazos = new ArrayList<Trazo>();
	
	private Color color = Color.BLACK;
	private BasicStroke grosor = new BasicStroke(3);
	
	public PaintPanel() {
		this.setBackground(Color.WHITE);
		
		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				trazos.getLast().addPoint(new Point(e.getX(), e.getY()));
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				trazos.add(new Trazo(color, grosor));
			}
		};
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setGrosor(int grosor) {
		this.grosor = new BasicStroke(grosor);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (Iterator iterator = trazos.iterator(); iterator.hasNext();) {
			Trazo trazo = (Trazo) iterator.next();
			
			g2d.setColor(trazo.getColor());
			g2d.setStroke(trazo.getGrosor());
			
			ArrayList<Point> puntos = trazo.getPuntos();
			if(puntos.size()>1) {
				for (int i = 1; i < puntos.size(); i++) {
					Point p1 = puntos.get(i-1);
					Point p2 = puntos.get(i);
					g2d.drawLine(p1.x,p1.y,p2.x,p2.y);
				}
			}
		}
	}
}
