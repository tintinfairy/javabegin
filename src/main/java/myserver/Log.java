package myserver;
import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Log {
    public Logger logger;
<<<<<<< HEAD
    FileHandler fh;
    public Log(String file_name) throws SecurityException, IOException{
        File f = new File(file_name);
        if(!f.exists()) f.createNewFile();
        fh = new FileHandler(file_name,true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

}
=======
            FileHandler fh;
            public Log(String file_name) throws SecurityException, IOException{
                File f = new File(file_name);
                if(!f.exists()) f.createNewFile();
                fh = new FileHandler(file_name,true);
                logger = Logger.getLogger("test");
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
            }

}

>>>>>>> 8dfea58d22d3c60948c37dfa2cd28eb4b994c362
