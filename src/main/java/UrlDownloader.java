import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UrlDownloader {

    public static void main(String[] args) throws IOException {

        String url = "https://geekbrains.ru/courses/3";
        Document doc = Jsoup.connect(url).get();

        // gettig title
        String title = doc.title();
        System.out.println("Title: " + title);

        //getting description
        String description = doc.select("meta[name=description]").first().attr("content");
        System.out.println("Description : " + description);

    }

}
