package com.ncu.docker.image;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if(args.length < 1) {
        	System.err.println("Please provide valid Url...");
        }
        String url = args[0];
        for(String link : getUrls(url)) {
        	System.out.println(link);
        }
    }
    
    private static Set<String> getUrls(String url) throws IOException{
    	Document doc = Jsoup.connect(url).data("query","Docker")
    			.userAgent("Mozilaa").cookie("auth", "token")
    			.timeout(3000).get();
    	Set<String> links = new HashSet<String>();
    	Elements e = doc.select("a[href]");
    	for(Element element : e) {
    		links.add(element.attr("href"));
    	}
    	return links;
    }
}
