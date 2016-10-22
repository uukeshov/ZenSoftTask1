package services;


import factory.AppProperties;
import main.Constants;
import models.SearchResponse;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class BingSearchService implements SearchService {
    private SearchResponse searchResponse;

    public SearchResponse search(String searchText) {
        searchResponse = new SearchResponse();
        String accountKey = Constants.BingAccountKey;
        searchText = searchText.replaceAll(" ", "%20");

        byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
        String accountKeyEnc = new String(accountKeyBytes);
        URL url;
        try {
            url = new URL(
                    Constants.BingQueryString + searchText + "%27&$top=50&$format=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            final JSONObject json = new JSONObject(response.toString());
            final JSONObject d = json.getJSONObject("d");
            final JSONArray results = d.getJSONArray("results");
            final int resultsLength = results.length();
            for (int i = 0; i < resultsLength; i++) {
                final JSONObject aResult = results.getJSONObject(i);
                searchResponse.setUrl(aResult.get("Url").toString());
                searchResponse.setTitle(aResult.get("Title").toString());
                searchResponse.setProvider(AppProperties.searchEngine.toString());
                return searchResponse;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

}
