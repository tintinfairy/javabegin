import java.io.*;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UrlDownloader {


    public static String getTitle(Document doc){
        String title = doc.title();
        return("Title:" + title);

    }

    public static String getDescription(Document doc){
        String description = doc.select("meta[name=description]").first().attr("content");
        return("Description:" + description);

    }

    public static void outputInf() throws IOException {

        int id = 1;
        Writer writer = new FileWriter("Output.json");


        while (id <= 100) {

            String url = "https://geekbrains.ru/courses/" + Integer.toString(id);
            Connection.Response response = Jsoup.connect(url).followRedirects(false).execute();
            if (response.statusCode() != 302) {

                //System.out.println("https://geekbrains.ru/courses/" + id);
                Document doc = Jsoup.connect(url).get();

                // getting title
                getTitle(doc);

                //getting description
                getDescription(doc);

                Gson gson = new GsonBuilder().create();
                gson.toJson(new Course(getTitle(doc), getDescription(doc)), writer);
            }


            id++;
            writer.flush();
        }
        writer.close();
    }

}
