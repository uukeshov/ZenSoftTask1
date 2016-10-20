package services;

import models.SearchRequest;

/**
 * Created by uukeshov on 19.10.2016.
 */
public interface SearchService {
    public SearchRequest search(String searchText);
}
