package services;

import factory.AppProperties;
import main.Constants;
import models.SearchRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Utils;

import java.io.IOException;

/**
 * Created by uukeshov on 19.10.2016.
 */
public class YahooSearchService implements SearchService {
    private Utils utils;
    private SearchRequest searchRequest;
    private String searchEngine = Constants.Yahoo;

    public SearchRequest search(String searchText) {
        utils = new Utils();
        searchRequest = new SearchRequest();
        String request = searchEngine + searchText;
        try {
            Document doc = Jsoup
                    .connect(request)
                    .userAgent(Constants.UserAgent)
                    .timeout(5000).get();
            Elements links = doc.select("h3[class=title]").select("a[href]");
            for (Element link : links) {
                searchRequest.setUrl(utils.getUrl(link.attr("href")));
                searchRequest.setTitle(link.text());
                searchRequest.setProvider(String.valueOf(AppProperties.searchEngine));
                return searchRequest;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchRequest;
    }
}
