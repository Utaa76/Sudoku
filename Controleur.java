package sudoku;

import sudoku.metier.*;
import sudoku.vue.*;

public class Controleur
{
	private Grille   metier;
	private FrameJeu vue;

	public Controleur()
	{
		this.metier = new Grille  (this, 1);
		this.vue    = new FrameJeu(this);
	}

	public int getCase(int x, int y)
	{
		return this.metier.getCase(y, x);
	}

	public void ecrireNumero(int numero)
	{
		int coords[] = this.vue.getCoordSelect();

		this.metier.placer(numero, coords[1], coords[0]);

		this.vue.maj();
	}

	public void reinitialiser()
	{
		this.metier.genererGrille(1);
		this.vue.maj();
	}

	public boolean verifier()
	{
		return this.metier.estValide();
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}

// Mettre les numéros écrits en bleu
// Pouvoir modifier les numéros écrits
// Mettre en place un ctrl z
