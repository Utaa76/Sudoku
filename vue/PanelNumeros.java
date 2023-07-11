package sudoku.vue;

import sudoku.Controleur;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;

public class PanelNumeros extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton    btnValider;
	private Integer    numSelect;

	public PanelNumeros(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(315, 0));

		this.ctrl = ctrl;

		this.btnValider = new JButton("Valider");

		this.btnValider.addActionListener(this);

		this.add(this.btnValider, BorderLayout.SOUTH);

		GereSouris gs = new GereSouris();

		this.addMouseListener      (gs);
		this.addMouseMotionListener(gs);

		this.repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setFont(new Font("", Font.PLAIN, 25));

		int x = 20;
		int y = 20;
		for (int i = 0 ; i < 9 ; i++)
		{
			if (i % 3 == 0)
			{
				x = 20;
				y += 90;
			}

			g.setColor(new Color(160, 200, 255));

			if (this.numSelect != null && i+1 == this.numSelect)
				g.fillRect(x, y, 75, 75);
			else
				g.drawRect(x, y, 75, 75);
			
			g.setColor(new Color(40, 100, 255));
			g.drawString(i+1 + "", x+31, y+45);

			x+= 90;
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		this.ctrl.ecrireNumero(this.numSelect);
		this.numSelect = null;
	}
	
	public class GereSouris extends MouseAdapter
	{
		private static final int TAILLE = 9;
		private Rectangle[] ensHitbox;

		public GereSouris()
		{
			this.ensHitbox = new Rectangle[GereSouris.TAILLE];

			int x = 20;
			int y = 20;
			for (int i = 0 ; i < GereSouris.TAILLE ; i++)
			{
				if (i % 3 == 0)
				{
					x = 20;
					y += 90;
				}

				this.ensHitbox[i] = new Rectangle(x, y, 75, 75);

				x+= 90;
			}
		}

		public void mouseClicked(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Integer posCase = this.getCoordCase(posX, posY);

			if (posCase != null)
			{
				PanelNumeros.this.numSelect = posCase+1;
			}
			else
			{
				PanelNumeros.this.numSelect = null;
			}
			
			PanelNumeros.this.repaint();
		}

		public void mouseMoved(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Integer posCase = this.getCoordCase(posX, posY);

			if (posCase != null)
				PanelNumeros.this.setCursor(new Cursor(Cursor.HAND_CURSOR   ));
			else
				PanelNumeros.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			

			PanelNumeros.this.repaint();
		}

		public Integer getCoordCase(int x, int y)
		{
			Integer[] posCase = new Integer[2];
			for (int i = 0 ; i < GereSouris.TAILLE ; i++)
			{
				if (this.ensHitbox[i].contains(x,y))
				{
					return i;
				}
			}

			return null;
		}
	}
}
