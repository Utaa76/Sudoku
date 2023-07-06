package sudoku.metier;

import sudoku.Controleur;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Grille
{
	private static final int TAILLE = 9;

	private char[][]          grille;
	private ArrayList<Region> ensRegion;

	private Controleur ctrl;

	public Grille(Controleur ctrl, int niveau)
	{
		this.grille    = new char[9][9];
		this.ensRegion = new ArrayList<>();

		this.genererGrille(niveau);

		this.ctrl = ctrl;
	}

	public void genererGrille(int niveau)
	{
		try
		{
			Scanner sc = new Scanner(new File("./src/grille" + niveau + ".data"));

			int numLigne = 0;
			while (sc.hasNextLine())
			{
				String[] ligne = sc.nextLine().split("\t");

				for (int i = 0 ; i < Grille.TAILLE ; i++)
				{
					if (!ligne[i].equals("0"))
						this.grille[numLigne][i] = ligne[i].charAt(0);
					else
						this.grille[numLigne][i] = '_';
				}

				numLigne++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Ptit soucis");
			e.printStackTrace();
		}

		this.remplirRegions();
	}

	public void remplirRegions()
	{
		for (int i = 0 ; i < Grille.TAILLE ; i += 3)
			for (int j = 0 ; j < Grille.TAILLE ; j += 3)
				this.remplirRegion(i, j);
	}

	public void remplirRegion(int x, int y)
	{
		char[][] region = new char[3][3];

		for (int i = 0 ; i < 3 ; i++)
		{
			for (int j = 0 ; j < 3 ; j++)
			{
				region[i][j] = this.grille[x+i][y+j];
			}
		}

		this.ensRegion.add(new Region(region));
	}

	public boolean placer(int numero, int x, int y)
	{
		if (numero < 1 || numero > 9 || grille[x][y] != '_') return false;

		grille[x][y] = ("" + numero).charAt(0);
		return true;
	}

	public boolean estValide()
	{
		// Vérifier les lignes
		for (char[] ligne : this.grille)
		{
			for (int i = 0 ; i < Grille.TAILLE ; i++)
			{
				for (int j = 0 ; j < Grille.TAILLE ; j++)
				{
					if ((ligne[i] == ligne[j] && i != j) || ligne[i] == '_') return false;
				}
			}
		}

		// Vérifier les colonnes
		char[][] grilleInversee = new char[9][9];

		for (int i = 0 ; i < Grille.TAILLE ; i++)
			for (int j = 0 ; j < Grille.TAILLE ; j++)
				grilleInversee[j][i] = grille[i][j];

		for (char[] ligne : grilleInversee)
		{
			for (int i = 0 ; i < Grille.TAILLE ; i++)
			{
				for (int j = 0 ; j < Grille.TAILLE ; j++)
				{
					if ((ligne[i] == ligne[j] && i != j) || ligne[i] == '_') return false;
				}
			}
		}

		// Vérifier les régions
		for (Region reg : this.ensRegion)
			if (!reg.verifierRegion()) return false;

		return true;
	}

	public String toString()
	{
		String sRet = "";
		for (char[] ligneGrille : this.grille)
		{
			for (int i = 0 ; i < Grille.TAILLE ; i++)
			{
				sRet += ligneGrille[i] + " ";
			}
			sRet += "\n";
		}

		return sRet;
	}

	/* Controleur */
	public int getCase(int x, int y)
	{
		return Character.getNumericValue(this.grille[x][y]);
	}
}
