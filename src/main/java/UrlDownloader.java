import java.io.*;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UrlDownloader {

    public static void main(String[] args) throws IOException {

        String url = "https://geekbrains.ru/courses/4";
        Document doc = Jsoup.connect(url).get();

        // gettig title
        String title = doc.title();
        System.out.println("Title: " + title);
        System.out.println();

        //getting description
        String description = doc.select("meta[name=description]").first().attr("content");
        System.out.println("Description : " + description);
        Writer writer = new FileWriter("Output.json");
        Gson gson = new GsonBuilder().create();
        gson.toJson(new Course(title, description), writer);

        writer.close();
    }

}
