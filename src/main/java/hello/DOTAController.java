package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.validator.internal.util.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

@RestController
@RequestMapping(value = "/rest/dota")
public class DOTAController {

	@RequestMapping("/getname")
    public String getObject() {
    	return "my name na uvuvwevwevwe";
    	
    }
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllMatches() {
		JsonObject convertedObject = null;
		String jsonS = "";
		JSONObject jsonObject = null;
		List<JSONObject> entities;
		Set<Map.Entry<String, JsonElement>> entries;
		try {

			URL url = new URL("https://api.pandascore.co/dota2/matches?token=hQ_IH5YjOHweKpx0ti6_zzXD4qdJa4LXgSbO0TxmPiWcgd6qFxE");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");			

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
//			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
//				System.out.println(output);
		        jsonS+=output;
//		        System.out.println(jsonS);
			}
						
//			Gson g = new Gson();
//			convertedObject = g.fromJson(jsonS, JsonObject.class);
			JSONArray jsonArray = new JSONArray(jsonS);
			jsonObject = new JSONObject(jsonArray);
											
			
			conn.disconnect();

		} catch (MalformedURLException e1) {

			e1.printStackTrace();

		} catch (IOException e2) {

			e2.printStackTrace();

		}
		return ResponseEntity.status(HttpStatus.OK).body(jsonObject);
//		return ResponseEntity.ok(jsonObject);
//		return ResponseEntity.ok(convertedObject.getString("begin_at"));
//		return convertedObject.getJSONArray("content").getJSONObject(0).getString("article")
		
//		return ResponseEntity.ok(convertedObject.getJSONArray("games"));



	}
}
