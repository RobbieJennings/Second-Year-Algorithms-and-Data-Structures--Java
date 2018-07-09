public class KMPSearch
{

	/*
	 * Bus Service Questions:
	 *
	 * 1. How many total vehicles is there information on?
	 *    987
	 *
	 * 2. Does the file contain information about the vehicle number 16555?
	 *    true
	 *
	 * 3. Locate the first record about a bus heading to HAMPTON PARK
	 *    76
	 *
	 * 4. Does the file contain information about the vehicle number 9043409?
	 *    false
	 */

	/*
	 * The method checks whether a pattern pat occurs at least once in String txt.
	 *
	 */
	public static boolean contains(String txt, String pat)
	{
		if(txt.isEmpty() || pat.isEmpty())
		{
			return false;
		}
		int M = pat.length();
		int N = txt.length();
		int i;
		int j;
		int[][] DFA = createDFA(pat, M);
		for(i = 0, j = 0; i < N && j < M; i++)
		{
			j = DFA[txt.charAt(i)][j];
			if(j == M)
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * The method returns the index of the first occurrence of a pattern pat in String txt.
	 * It should return -1 if the pat is not present
	 */
	public static int searchFirst(String txt, String pat)
	{
		if(txt.isEmpty() || pat.isEmpty())
		{
			return -1;
		}
		int M = pat.length();
		int N = txt.length();
		int i;
		int j;
		int[][] DFA = createDFA(pat, M);
		for(i = 0, j = 0; i < N && j < M; i++)
		{
			j = DFA[txt.charAt(i)][j];	  
		}
		if(j == M)
		{
			return i - M;
		}	
		return -1;
	}

	/*
	 * The method returns the number of non-overlapping occurrences of a pattern pat in String txt.
	 */
	public static int searchAll(String txt, String pat)
	{
		if(txt.isEmpty() || pat.isEmpty())
		{
			return 0;
		}
		int count = 0;
		int M = pat.length();
		int N = txt.length();
		int i;
		int j;
		int[][] DFA = createDFA(pat, M);
		for(i = 0, j = 0; i < N && j < M; i++)
		{
			j = DFA[txt.charAt(i)][j];
			if(j == M)
			{
				count++;
				j = 0;
			}
		}
		return count;
	}

	private static int[][] createDFA(String pat, int M)
	{
		int[][] DFA = new int[256][M];
		DFA[pat.charAt(0)][0] = 1;
		for(int x = 0, j = 1; j < M; j++)
		{
			for(int c = 0; c < 256; c++)
			{
				DFA[c][j] = DFA[c][x];
			}
			DFA[pat.charAt(j)][j] = j + 1;
			x = DFA[pat.charAt(j)][x];
		}
		return DFA;
	}
}