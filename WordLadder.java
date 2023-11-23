import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordLadder {
	private static String start;
	private static String end;
	private static StringMap T;			// This map stores the dictionary of words.
	private static StringMap R;
	private static final int INF = 9999999;		// This map keeps track of all the words that are visited during breadth-first-search.
										// The key field is the word that is visited, and its value field can hold the predecessor pointer.
	private static Queue Q;				// A queue to perform the breadth-first-search.
	private static int dist = 0;

	public static void main(String [] args) throws IOException {

		// Loading the dictionary of words into the StringMap T.
		T = new StringMap();
		File file = new File("dictionary4");
		Scanner f = new Scanner(file);
		while (f.hasNext()) {
			String word = f.nextLine();
			T.insert(word, "");
		}
		f.close();

		Scanner kb = new Scanner(System.in);
		System.out.print("Enter the start word: ");
		start = kb.nextLine();
		System.out.print("Enter the end word: ");
		end = kb.nextLine();

		// Solution to find the shortest set of words that transforms the start word to the end word.

		Q = new Queue();
		R = new StringMap();

		QNode init = new QNode(0, start);
		Q.enqueue(init);
		//R.insert(start, start);
		boolean find = bfs(init);
		

		if (find == true)
		{
			
			System.out.println("Yay! A word ladder is possible.");
			System.out.println(start);
			print(end);
		}
		else
			System.out.println("Duh! Impossible.");
	}

	public static void print(String k) //current word, lastword
    {
        if (k.equals(start)) return;
		StringNode s = R.find(k);
        print(s.getValue());  
        System.out.println(k);
    }

	public static boolean bfs(QNode n)
	{ 
		QNode i = null;
		while (Q.isEmpty() == false)  //While Q is nonempty:
		{
			i = new QNode(Q.dequeue()); //i = next node in Q
			if (i.getWord().equals(end))
				return true;

			findWord(i);
		}
		return false;
	}

	public static void findWord(QNode n) 
	{
		for (int i = 0; i < n.getWord().length(); i++)
		{
		  StringBuffer wordBuffer = new StringBuffer(n.getWord());

		  for (int j = 96; j <= 122; j++)
		  {
			char c = (char) j;
			wordBuffer.setCharAt(i, c);

			if ((T.find(wordBuffer.toString()) != null) && (R.find(wordBuffer.toString()) == null)) //checks its a word in the dictionary and is not in R
			{
				R.insert(wordBuffer.toString(), n.getWord());
				Q.enqueue(new QNode(n.getDist()+1, wordBuffer.toString()));
			}
		  }
		}
	}
}
