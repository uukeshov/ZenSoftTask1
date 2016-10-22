package factory;

/**
 * Created by Andrew on 17.06.2016.
 */
public class AppProperties {

    public enum SearchEngine {
        Google("Google"), Yahoo("Yahoo"), Bing("Bing");
        public final String searchProvider;

        SearchEngine(String searchProvider) {
            this.searchProvider = searchProvider;
        }
    }

    public static SearchEngine searchEngine = SearchEngine.Yahoo;

    public void alter(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }
}
