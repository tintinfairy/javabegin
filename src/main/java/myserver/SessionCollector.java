package myserver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionCollector {

    public static final SessionCollector INSTANCE = new SessionCollector();
    private Map<Long, Session> hashmap = new ConcurrentHashMap<Long, Session>();

    public Map<Long, Session> getHashMap() {
        return hashmap;
    }

    public Map<Long, Session> setHashMap(Long client_id, Session session) {
        hashmap.put(client_id, session);
        return hashmap;
    }


    public static SessionCollector getInstance() {
        return INSTANCE;
    }
}
