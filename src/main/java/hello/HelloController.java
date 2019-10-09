package hello;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/getObject")
    public String getObject() {
    	return "my name na uvuvwevwevwe";
    	
    }
    
   
    
    	
//    	JsonObject convertedObject = null;
//    	try {
//    		    		
//    		URL url = new URL("https://api.pandascore.co/dota2/matches/upcoming");
//    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//    		conn.setRequestMethod("GET");
//    		conn.setRequestProperty("Accept", "application/json");
//
//    		if (conn.getResponseCode() != 200) {
//    			throw new RuntimeException("Failed : HTTP error code : "
//    					+ conn.getResponseCode());
//    		}
//
//    		BufferedReader br = new BufferedReader(new InputStreamReader(
//    			(conn.getInputStream())));
//
//    		String output;
//    		System.out.println("Output from Server .... \n");
//    		while ((output = br.readLine()) != null) {
//    			System.out.println(output);
//    		}
//    		
//    		Gson g = new Gson();
//    		convertedObject = new Gson().fromJson(output, JsonObject.class);
//
//
//    		conn.disconnect();
//
//    	  } catch (MalformedURLException e1) {
//
//    		e1.printStackTrace();
//
//    	  } catch (IOException e2) {
//
//    		e2.printStackTrace();
//
//    	  }
//    	return convertedObject;
//    	
//    }
    
}
