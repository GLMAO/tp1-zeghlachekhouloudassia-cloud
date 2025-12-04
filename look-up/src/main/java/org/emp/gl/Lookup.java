package org.emp.gl;

import java.util.HashMap;
import java.util.Map;

public class Lookup {

    private static Lookup OBJ = null;
    private static final Object lock = new Object();

    private Map<Class, Object> services = new HashMap<>();

    private Lookup() {
        System.out.println("Création de Lookup (version typée)");
    }

    public static Lookup getInstance() {
        if (OBJ == null) {
            synchronized (lock) {
                if (OBJ == null) {
                    OBJ = new Lookup();
                }
            }
        }
        return OBJ;
    }

    // Enregistrer un service
    public <T> void subscribeService(Class<? super T> type, T instance) {
        if (!type.isInstance(instance)) {
            throw new IllegalArgumentException("Type mismatch!");
        }
        services.put(type, instance);
    }

    // Récupérer un service
    public <T> T getService(Class<T> type) {
        return type.cast(services.get(type));
    }
}
