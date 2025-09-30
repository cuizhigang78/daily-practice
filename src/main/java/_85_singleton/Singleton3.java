package _85_singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 饿汉3
 */
public class Singleton3 {
    public static final Singleton3 INSTANCE;
    private String info;

    static {
        String info = "";
        Properties properties = new Properties();
        try {
            properties.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        info = properties.getProperty("info");
        INSTANCE = new Singleton3(info);
    }

    private Singleton3() {

    }

    public Singleton3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
