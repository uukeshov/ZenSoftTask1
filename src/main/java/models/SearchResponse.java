package models;

/**
 * Created by uukeshov on 19.10.2016.
 */
public class SearchResponse {
    private String title;
    private String url;
    private String provider;

    public SearchResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
