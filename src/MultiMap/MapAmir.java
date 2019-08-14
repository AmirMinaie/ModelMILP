package MultiMap;

public class MapAmir<K, V> {

    private K[] key;
    private V value;

    public MapAmir(V value, K... key) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public K[] getKey() {
        return key;
    }

    public void setValue(V value) {
        this.value = value;
    }

}