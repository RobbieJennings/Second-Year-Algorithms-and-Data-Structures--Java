import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TSTTest {

	@Test
	public void testConstructor(){
		new TST<String>();
	}

	@Test
	public void testEmpty(){
		TST<Long> trie = new TST<>();
		assertEquals("size of an empty trie should be 0",0, trie.size());
		assertFalse("searching an empty trie should return false",trie.contains(""));
		assertNull("getting from an empty trie should return null",trie.get(""));
		trie.put("", (long) 1);
		assertEquals("putting an empty key onto a tree should do nothing",0,trie.size());
		assertNull("search for null prefix should return null",trie.keysWithPrefix(""));
	}

	@Test
	public void testSize() {
		TST<String> tree = new TST<String>();
		tree.put("ONE", "one");
		tree.put("TWO", "two");
		tree.put("THREE", "three");
		assertEquals("size of non empty tree should be 3", 3, tree.size());
	}

	@Test
	public void testGet() {
		TST<String> tree = new TST<String>();
		tree.put("ONE", "one");
		tree.put("TWO", "two");
		tree.put("THREE", "three");
		assertEquals("Getting value from non empty tree at index 1", "two", tree.get("TWO"));
	}

	@Test
	public void testLinkedList() {
		TST<String> tree = new TST<String>();
		tree.put("ONE", "one");
		tree.put("TWO", "two");
		tree.put("THREE", "three");
		assertEquals("Checking put into an empty tree", "[TWO, THREE]", tree.keysWithPrefix("T").toString());
	}

	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("BUSES_SERVICE_0.json"));
		JSONArray array = (JSONArray) obj;

		TST<Integer> trie = new TST<Integer>();
		for(int i = 0; i < array.size(); i++)
		{
			JSONObject jsonobj = (JSONObject) array.get(i);
			String destination = jsonobj.get("Destination").toString();
			if(trie.contains(destination))
			{
				trie.put(destination, trie.get(destination) + 1);
			}
			else
			{
				trie.put(destination, 1);
			}
		};

		System.out.println(trie.size());
		System.out.println(trie.contains("SOUTHSIDE"));
		LinkedList<String> list = trie.keysWithPrefix("DOWN");
		int n = 0;
		for(int i = 0; i < list.size(); i++)
		{
			n += trie.get(list.get(i));
		}
		System.out.println(n);
		System.out.println();

		TST<Long> trie2 = new TST<Long>();
		File file = new File("google-books-common-words.txt");
		Scanner inputScanner = new Scanner(file);
		while(inputScanner.hasNext())
		{
			trie2.put(inputScanner.next(), inputScanner.nextLong());
		}
		inputScanner.close();

		System.out.println(trie2.size());
		System.out.println(trie2.get("ALGORITHM"));
		System.out.println(trie2.contains("EMOJI"));
		System.out.println(trie2.contains("BLAH"));
		LinkedList<String> list2 = trie2.keysWithPrefix("TEST");
		System.out.print(list2.size());
	}
}