package by.snitovets.flowershopweb.logic;

import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Илья on 11.07.2014.
 */
public class MessageManager {

    public static final String WRONG_TYPE_PARSER_EXCEPTION = "messages.jsp.error.wrong_purser";
    private static final String BUNDLE_NAME = "resources.message";
    private static MessageManager instance;
    private static Lock lock = new ReentrantLock();
    private ResourceBundle resourceBundle;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new MessageManager();
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
