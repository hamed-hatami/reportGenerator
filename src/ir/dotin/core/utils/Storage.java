package ir.dotin.core.utils;

import java.util.Hashtable;


public class Storage<K, V> extends Hashtable<K, V> {

    private V defaultValue;

    public Storage(V defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public synchronized V get(Object key) {
        if (key != null) {
            V val = super.get(key);
            if (val != null) {
                return val;
            }
        }
        return defaultValue;
    }
}
