package services;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import factory.AppProperties;
import models.SearchRequest;

import java.util.List;

/**
 * Created by uukeshov on 19.10.2016.
 */
public class GoogleSearchService implements SearchService {
    private SearchRequest searchRequest;

    public SearchRequest search(String searchText) {
        Customsearch customsearch = new Customsearch.Builder(new NetHttpTransport(), new JacksonFactory(), null).setApplicationName("your application name").build();
        List<Result> resultList = null;
        searchRequest = new SearchRequest();
        try {
            Customsearch.Cse.List list = customsearch.cse().list(searchText);
            list.setKey("AIzaSyBoco2nGdw31ucf3YnbO6T2IomIbC35FJo");
            list.setCx("009164519098532791002:xdehbvvsflk");
            Search search = list.execute();
            resultList = search.getItems();

            for (int i = 0; i < resultList.size(); i++) {
                final Result aResult = resultList.get(i);
                searchRequest.setUrl(aResult.get("link").toString());
                searchRequest.setTitle(aResult.get("title").toString());
                searchRequest.setProvider(AppProperties.searchEngine.toString());
                return searchRequest;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
