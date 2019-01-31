package colruyt.rearulmgtdmnejb.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;

public class MockData {
	InputStream inputStream;
	private static MockData instance = null;

	private MockData() {
		// This is to prevent instantiation.
	}

	public static MockData getInstance() {
		if (instance == null) {
			instance = new MockData();
		}
		return instance;
	}

	public JSONArray getJsonValues(String fileName) throws IOException, ParseException   {
		JSONParser parser = new JSONParser();
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName +".json");
		return (JSONArray) parser.parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
	}

}
