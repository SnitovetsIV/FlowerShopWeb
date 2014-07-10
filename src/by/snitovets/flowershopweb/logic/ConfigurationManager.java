package by.snitovets.flowershopweb.logic;

import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Илья on 06.07.2014.
 */
public class ConfigurationManager {

    public static final String XML_FILE_PATH = "config.dao.xml.path";
    public static final String XSD_FILE_PATH = "config.dao.xsd.path";
    public static final String ERROR_PAGE_PATH = "config.page.error";
    public static final String INDEX_PAGE_PATH = "config.page.index";
    public static final String DEFAULT_LANGUAGE = "config.default.language";
    private static final String BUNDLE_NAME = "resources.config";
    private static ConfigurationManager instance;
    private static Lock lock = new ReentrantLock();
    private ResourceBundle resourceBundle;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new ConfigurationManager();
                    instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getString(key);
    }

}
