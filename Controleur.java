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

	public static void main(String[] a)
	{
		new Controleur();
	}
}

// Garder les index de la case selectionnée
// Dessiner un rectangle gris à cet endroit