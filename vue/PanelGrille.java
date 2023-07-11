package sudoku.vue;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import sudoku.Controleur;

public class PanelGrille extends JPanel
{
	private Controleur ctrl;
	private Integer    colCaseSelect;
	private Integer    ligCaseSelect;

	public PanelGrille(Controleur ctrl)
	{
		this.ctrl = ctrl;

		GereSouris gs = new GereSouris();

		this.addMouseListener      (gs);
		this.addMouseMotionListener(gs);
		
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Case selectionnée
		g.setColor(Color.lightGray);

		if (colCaseSelect != null)
			g.fillRect(50 + this.colCaseSelect * 50, 50 + this.ligCaseSelect * 50, 50, 50);

		// Traits fins
		g.setColor(new Color(135, 172, 255));

		for (int i = 50 ; i <= 500 ; i += 50)
		{
			g.drawLine(i, 50, i, 500);
			g.drawLine(50, i, 500, i);
		}

		// Traits gras
		g.setColor(new Color(40, 100, 255));

		for (int i = 50 ; i <= 500 ; i += 150)
		{
			g.drawLine(i-1, 50, i-1, 500);
			g.drawLine(i  , 50, i, 500  );
			g.drawLine(i+1, 50, i+1, 500);
			g.drawLine(50, i-1, 500, i-1);
			g.drawLine(50, i  , 500, i  );
			g.drawLine(50, i+1, 500, i+1);
		}

		// Chiffres
		g.setColor(Color.black);
		g.setFont(new Font("", Font.PLAIN, 25));

		for (int x = 68 ; x <= 500 ; x += 50)
		{
			for (int y = 85 ; y <= 500 ; y += 50)
			{
				int valCase = this.ctrl.getCase((x-50)/50, (y-50)/50);

				if (valCase > 0)
				{
					g.drawString("" + valCase, x, y);
				}
			}
		}
	}

	public int[] getCoordSelect()
	{
		int[] tabRet = new int[2];

		tabRet[0] = this.colCaseSelect;
		tabRet[1] = this.ligCaseSelect;

		return tabRet;
	}

	public class GereSouris extends MouseAdapter
	{
		private static final int TAILLE = 9;
		private Rectangle[][] ensHitbox;

		public GereSouris()
		{
			this.ensHitbox = new Rectangle[GereSouris.TAILLE][GereSouris.TAILLE];

			for (int i = 0 ; i < GereSouris.TAILLE ; i++)
			{
				for (int j = 0 ; j < GereSouris.TAILLE ; j++)
				{
					this.ensHitbox[i][j] = new Rectangle(50+(i*50), 50+(j*50), 50, 50);
				}
			}
		}

		public void mouseClicked(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Integer[] posCase = this.getCoordCase(posX, posY);

			if (posCase != null && PanelGrille.this.ctrl.getCase((posX-50)/50, (posY-50)/50) <= 0)
			{
				PanelGrille.this.colCaseSelect = posCase[0];
				PanelGrille.this.ligCaseSelect = posCase[1];
			}
			else
			{
				PanelGrille.this.colCaseSelect = null;
				PanelGrille.this.ligCaseSelect = null;
			}
			
			PanelGrille.this.repaint();
		}

		public void mouseMoved(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Integer[] posCase = this.getCoordCase(posX, posY);

			if (posCase != null && PanelGrille.this.ctrl.getCase((posX-50)/50, (posY-50)/50) <= 0)
				PanelGrille.this.setCursor(new Cursor(Cursor.HAND_CURSOR   ));
			else
				PanelGrille.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			

			PanelGrille.this.repaint();
		}

		public Integer[] getCoordCase(int x, int y)
		{
			Integer[] posCase = new Integer[2];
			for (int i = 0 ; i < GereSouris.TAILLE ; i++)
			{
				for (int j = 0 ; j < GereSouris.TAILLE ; j++)
				{
					if (this.ensHitbox[i][j].contains(x,y))
					{
						posCase[0] = i;
						posCase[1] = j;

						return posCase;
					}
				}
			}

			return null;
		}
	}
}
