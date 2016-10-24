package utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by uukeshov on 19.10.2016.
 */
public class Utils {
    private static Pattern URL_PATTERN;
    private Matcher matcher;

    static {
        URL_PATTERN = Pattern.compile(Constants.URL_PATTERN);
    }

    public String getUrl(String url) {
        String domainName = "";
        matcher = URL_PATTERN.matcher(url);
        if (matcher.find()) {
            domainName = matcher.group(0).toLowerCase().trim();
        }
        return domainName;
    }

    public static boolean isInternetConnectionExist(String site) {
        Socket sock = new Socket();
        InetSocketAddress addr = new InetSocketAddress(site, 80);
        try {
            sock.connect(addr, 3000);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
            }
        }
    }
}
