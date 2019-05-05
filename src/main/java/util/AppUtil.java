package util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtil {

    private static final Logger log = Logger.getLogger(AppUtil.class);
    private static final String CAN_T_LOAD_PROPERTIES_FROM_CLASSPATH = "Can't load properties from classpath: ";

    private AppUtil() {
    }

    public static void loadProperties(Properties props, String classPathUrl) {
        try (InputStream in = AppUtil.class.getClassLoader().getResourceAsStream(classPathUrl)) {
            props.load(in);
        } catch (IOException e) {
            log.warn(CAN_T_LOAD_PROPERTIES_FROM_CLASSPATH + classPathUrl, e);
            throw new IllegalArgumentException(CAN_T_LOAD_PROPERTIES_FROM_CLASSPATH + classPathUrl, e);
        }
    }

}
