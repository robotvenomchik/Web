package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Cat {
    private static String apiUrl = "https://catfact.ninja/fact";
    public static String getRandomCatFact(){
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int status = con.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();

                JSONObject json = new JSONObject(content.toString());
                return json.getString("fact");
            } else {
                System.out.println("Error: " + status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
