package sudoku.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Region
{
	private ArrayList<Character> alCases;

	public Region(char[][] ensCases)
	{
		this.alCases = new ArrayList<>();

		for (int i = 0 ; i < ensCases.length ; i++)
			for (int j = 0 ; j < ensCases.length ; j++)
				this.alCases.add(ensCases[i][j]);
	}

	public boolean verifierRegion()
	{
		List<Character> alCasesSansDoublons = this.alCases.stream().distinct().toList();

		return alCasesSansDoublons.size() == this.alCases.size(); // Pour savoir si la région est bonne, on regarde s'il y a des numéros doublons.
	}
}