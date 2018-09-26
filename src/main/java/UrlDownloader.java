import java.io.*;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UrlDownloader {

    public static void main(String[] args) throws IOException {

        int id = 1;
        Writer writer = new FileWriter("Output.json");


        while (id <= 200) {

            String url = "https://geekbrains.ru/courses/" + Integer.toString(id);
            Connection.Response response = Jsoup.connect(url).followRedirects(false).execute();
            if (response.statusCode() != 302) {

                System.out.println("https://geekbrains.ru/courses/" + id);
                Document doc = Jsoup.connect(url).get();

                // getting title
                String title = doc.title();
                System.out.println("Title:" + title);

                //getting description
                String description = doc.select("meta[name=description]").first().attr("content");
                System.out.println("Description:" + description);

                Gson gson = new GsonBuilder().create();
                gson.toJson(new Course(title, description), writer);
            }


            id++;
        }
        writer.close();
    }

}
