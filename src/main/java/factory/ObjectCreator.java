package factory;

import services.BingSearchService;
import services.GoogleSearchService;
import services.SearchService;
import services.YahooSearchService;

public class ObjectCreator {

    public static SearchService getSearchEngineService() {
        if (AppProperties.searchEngine == AppProperties.SearchEngine.Google) {
            return new GoogleSearchService();
        }

        if (AppProperties.searchEngine == AppProperties.SearchEngine.Yahoo) {
            return new YahooSearchService();
        }

        if (AppProperties.searchEngine == AppProperties.SearchEngine.Bing) {
            return new BingSearchService();
        }
        return null;
    }
}
