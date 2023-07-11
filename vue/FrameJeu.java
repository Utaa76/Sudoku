package sudoku.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import sudoku.Controleur;
import sudoku.vue.PanelNumeros;

public class FrameJeu extends JFrame
{
	private Controleur   ctrl;
	private PanelGrille  panelGrille;
	private PanelNumeros panelNums;

	public FrameJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Sudoku");
		this.setSize(850, 600);
		this.setLocationRelativeTo(null);
		//this.setLayout(new FlowLayout());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panelGrille = new PanelGrille (this.ctrl);
		this.panelNums   = new PanelNumeros(this.ctrl);

		this.add(this.panelGrille, BorderLayout.CENTER);
		this.add(this.panelNums  , BorderLayout.EAST  );

		this.setVisible(true);
	}

	public int[] getCoordSelect()
	{
		return this.panelGrille.getCoordSelect();
	}

	public void maj()
	{
		this.panelGrille.repaint();
		this.panelNums  .repaint();
	}
}
