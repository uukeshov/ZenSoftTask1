package main;

import factory.ObjectCreator;
import services.SearchService;
import utils.Utils;

public class Intro {
    public static void main(String[] args) {
        Boolean internetStatus = Utils.isInternetConnectionExist("www.google.com");

        if (internetStatus) {
            SearchService searchService = ObjectCreator.getSearchEngineService();
            System.out.println(searchService.search("legend movie"));
        }

        if(!internetStatus){
            System.out.println("No internet connection!");
        }
    }
}
