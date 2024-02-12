package examSources;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe
{
	List<List<String>> board;

	public TicTacToe()
	{
		board = new ArrayList<List<String>>(3);
		for(int i = 0; i < 3; i++)
		{
			List<String> column = new ArrayList<String>(3);
			board.add(column);
			for(int j = 0; j < 3; j++)
			{
				board.get(i).add("-");
			}
		}
	}

	public boolean addToken(int row, int column, String token)
	{
		String currentToken = board.get(row).get(column);

		if(currentToken.equals("-"))
		{
			board.get(row).set(column, token);
			return true;
		}
		else
		{
			System.out.println("Element already filled");
			return false;
		}
	}

	public void printBoard()
	{
		String printLine = "";
		for(int i = 0; i < board.size(); i++)
		{
			for(int j = 0; j < board.get(0).size(); j++)
			{
				printLine += board.get(i).get(j) + "|";
			}
			printLine = printLine.substring(0, printLine.length()-1);	//remove the last | character
			System.out.println(printLine);
			printLine = "";
		}
	}
}
