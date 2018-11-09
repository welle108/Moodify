import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Base64;
import com.google.gson.*;

public class testconnection {
    public static void main(String[] args) {
		
		String client_id = "2fc975d657f54a18828abf965dde398a";
		String client_secret = "486c4e184db14be798e7402551a18b5a";
		String auth = client_id + ":" + client_secret;
		String token = "";
		
        try {

            URL auth_url = new URL ("https://accounts.spotify.com/api/token/?grant_type=client_credentials");
            HttpURLConnection auth_conn = (HttpURLConnection) auth_url.openConnection();
			auth_conn.setRequestMethod("POST");
			auth_conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			auth_conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));
			
            if (auth_conn.getResponseCode() != 200) {
                throw new RuntimeException("ERROR CODE " + auth_conn.getResponseCode());
            }

            //BufferedReader reader = new BufferedReader(new InputStreamReader((auth_conn.getInputStream())));

            JsonObject token_obj = (JsonObject) new JsonParser().parse(new InputStreamReader(auth_conn.getInputStream()));
            /*
            String output;
            while ((output = reader.readLine()) != null) {
                System.out.println(output);
            }
            */

            token = token_obj.get("access_token").getAsString();
            System.out.println(token);
            auth_conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		try {
            URL search_url = new URL ("https://api.spotify.com/v1/search?q=Eptic&type=track");
            HttpURLConnection conn = (HttpURLConnection) search_url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + token);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("ERROR CODE " + conn.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = reader.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}