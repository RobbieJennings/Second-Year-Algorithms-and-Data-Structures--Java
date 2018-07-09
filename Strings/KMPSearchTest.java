import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class KMPSearchTest {

	@Test
	public void testConstructor(){
		new KMPSearch();
	}

	@Test
	public void testEmpty(){
		assertEquals("Empty text or pattern is invalid",-1,KMPSearch.searchFirst("",""));
		assertEquals("Empty text or pattern is invalid",0,KMPSearch.searchAll("",""));
		assertFalse("Empty text or pattern is invalid",KMPSearch.contains("",""));
	}

	@Test
	public void testNoMatches(){
		assertEquals("No occurrence of pattern in txt",-1,KMPSearch.searchFirst("abrakedabra","bob"));
		assertEquals("No occurrence of pattern in txt",0,KMPSearch.searchAll("abrakedabra","bob"));
		assertFalse("No occurrence of pattern in txt",KMPSearch.contains("abrakedabra","bob"));
	}

	@Test 
	public void testSingleMatch(){
		assertEquals("1 occurrence of pattern in txt",3,KMPSearch.searchFirst("blobobibodibolobs","bob"));
		assertEquals("1 occurrence of pattern in txt",1,KMPSearch.searchAll("blobobibodibolobs","bob"));
		assertTrue("1 occurrence of pattern in txt",KMPSearch.contains("blobobibodibolobs","bob"));
	}

	@Test
	public void testMultipleMatches(){
		assertEquals("3 occurrences of pattern in txt",0,KMPSearch.searchFirst("bobobsdobobsdobobd","bob"));
		assertEquals("3 occurrences of pattern in txt",3,KMPSearch.searchAll("bobobsdobobsdobobd","bob"));
		assertTrue("3 occurrences of pattern in txt",KMPSearch.contains("bobobsdobobsdobobd","bob"));
	}

	@Test
	public void testMultipleMatchesSpacesOverlap(){
		assertEquals("Expexted at index 2",2,KMPSearch.searchFirst("bobobo bobobo bobobo","bobo bo"));
		assertEquals("2 occurrences of pattern in txt",2,KMPSearch.searchAll("bobobo bobobobo bobobo","bobo bo"));
		assertTrue("2 occurrences of pattern in txt",KMPSearch.contains("bobobo bobobobo bobobo","bobo bo"));
	}

	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("BUSES_SERVICE_0.json"));
		JSONArray array = (JSONArray) obj;
		String busses = array.toString();

		System.out.println(KMPSearch.searchAll(busses, "VehicleNo"));
		System.out.println(KMPSearch.contains(busses, "\"VehicleNo\":\"16555\""));
		for(int i = 0; i < array.size(); i++)
		{
			JSONObject jsonObj = (JSONObject) array.get(i);
			String bus = jsonObj.toString();
			if(KMPSearch.contains(bus, "\"Destination\":\"HAMPTON PARK\""))
			{
				System.out.println(i);
				break;
			}
		}
		System.out.print(KMPSearch.contains(busses, "\"VehicleNo\":\"9043409\""));
	}
}