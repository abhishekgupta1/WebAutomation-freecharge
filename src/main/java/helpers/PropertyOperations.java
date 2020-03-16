package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyOperations {
    private static final Logger log = LoggerFactory.getLogger(PropertyOperations.class);

    public void writeToProp(String property, String value, String filename) throws IOException {
        log.info("Writing property:" + property + " to file: " + filename);
        FileInputStream in = new FileInputStream(filename);
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(filename);
        props.setProperty(property, value);
        props.store(out, null);
        out.close();
    }

    public String readFromProps(String property, String filename) throws IOException {
        log.info("Reading property:" + property + " from file: " + filename);
        Properties props = new Properties();
        props.load(new FileInputStream(filename));
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            String value = props.getProperty(name);
            if (name.equals(property)) {
                return value;
            }
        }
        return null;
    }
}