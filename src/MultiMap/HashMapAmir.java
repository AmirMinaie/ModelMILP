package MultiMap;

import java.util.ArrayList;

public class HashMapAmir<K, V> {
    private int size;
    private String name;
    private int DEFAULT_CAPACITY = 16;
    @SuppressWarnings("unchecked")
    private ArrayList<MapAmir<K, V>> values = new ArrayList<>();
    private int numberKey;

    public HashMapAmir(int numberkey, String name) {
        this.numberKey = numberkey;
        this.name = name;
    }

    public MapAmir getMap(K... Key) {
        if (Key.length != this.numberKey)
            throw new NullPointerException();

        for (MapAmir m : values) {
            boolean b = true;
            for (int i = 0; i < numberKey; i++) {
                if (!m.getKey()[i].equals(Key[i])) {
                    b = false;
                    break;
                }
            }

            if (b)
                return m;

        }
        throw new NullPointerException();
    }


    public V get(K... Key) {


//        if (Key.length != this.numberKey) {
//            String E = name + "(";
//            for (int i = 0; i < Key.length - 1; i++)
//                E += Key[i] + ",";
//            E += Key[Key.length - 1] + ")";
//            throw new IllegalArgumentException("not Equal number key and legheth key:" + E);
//        }

        for (MapAmir m : values) {
            boolean b = true;
            for (int i = 0; i < numberKey; i++) {
                if (!m.getKey()[i].equals(Key[i])) {
                    b = false;
                    break;
                }
            }

            if (b)
                return (V) m.getValue();

        }

        String E = name + "(";
        for (int i = 0; i < Key.length - 1; i++)
            E += Key[i] + ",";
        E += Key[Key.length - 1] + ")";

        //region throw Exception
//        throw new IllegalArgumentException("Parametr: " + E);
        System.err.println("Parametr: " + E);
        //endregion

        return values.get(1).getValue();
    }

    public Object gets(String... Key) {

        if (Key.length != this.numberKey)
            throw new NullPointerException();

        HashMapAmir resalte = new HashMapAmir(numberKey, name);

        for (MapAmir m : values) {

            boolean b = true;
            for (int i = 0; i < numberKey; i++) {
                if (!m.getKey()[i].equals(Key[i]))
                    if (Key[i] != ":") {
                        b = false;
                        break;
                    }
            }
            if (b) {
                resalte.put(m.getValue(), m.getKey());
            }
        }
        return resalte;
    }

    public void put(V value, K... Key) {

        boolean add = true;
//
//        for (MapAmir m : values) {
//            boolean b = true;
//
//            for (int i = 0; i < numberKey; i++) {
//                if (!m.getKey()[i].equals(Key[i])) {
//                    b = false;
//                    break;
//                }
//            }
//
//            if (b)
//                add = false;
//
//        }
//        if (add) {
            values.add(new MapAmir<>(this.name, value, Key));
//        }
    }

    public int size() {
        size = values.size();
        return size;
    }

    public int getNumberKey() {
        return numberKey;
    }

    public String getName() {
        return name;
    }

    public ArrayList<MapAmir<K, V>> getValus() {
        return values;
    }
}
