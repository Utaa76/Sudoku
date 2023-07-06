package sudoku.vue;

import javax.swing.JFrame;
import sudoku.Controleur;

public class FrameJeu extends JFrame
{
	private Controleur ctrl;
	private PanelJeu   panelJeu;

	public FrameJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Sudoku");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panelJeu = new PanelJeu(this.ctrl);

		this.add(this.panelJeu);

		this.setVisible(true);
	}
}
