import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GeekBrainsCourseCollector {


    public static String getTitle(Document doc) {
        String title = doc.title();
        return ("Title:" + title);

    }

    public static String getDescription(Document doc) {
        String description = doc.select("meta[name=description]").first().attr("content");
        return ("Description:" + description);

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

    public static String executePost(String targetURL, String content) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(content.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(content);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
