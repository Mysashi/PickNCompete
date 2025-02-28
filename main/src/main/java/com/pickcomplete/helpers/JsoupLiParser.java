package com.pickcomplete.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupLiParser {

    public static void parseLi() throws IOException {
        Document doc = Jsoup.connect("http://localhost:8080/event").get();
        Elements elements = doc.select("li");
        System.out.println(elements.toString());

    }
}
