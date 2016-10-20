package services;

import factory.AppProperties;
import main.Constants;
import models.SearchRequest;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BingSearchService implements SearchService {
    private SearchRequest searchRequest;
    private String accountKey = Constants.BingAccountKey;

    public SearchRequest search(String searchText) {
        searchRequest = new SearchRequest();
        searchText = searchText.replaceAll(" ", "%20");

        byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
        String accountKeyEnc = new String(accountKeyBytes);
        URL url;
        try {
            url = new URL(
                    Constants.BingQueryString + searchText + Constants.responseFormat);
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

            try (final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                final StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                final JSONObject json = new JSONObject(response.toString());
                final JSONObject d = json.getJSONObject("d");
                final JSONArray results = d.getJSONArray("results");
                final int resultsLength = results.length();
                for (int i = 0; i < resultsLength; i++) {
                    final JSONObject aResult = results.getJSONObject(i);
                    searchRequest.setUrl(aResult.get("Url").toString());
                    searchRequest.setTitle(aResult.get("Title").toString());
                    searchRequest.setTitle(aResult.toString());
                    searchRequest.setProvider(AppProperties.searchEngine.toString());
                    return searchRequest;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return searchRequest;
    }
}
