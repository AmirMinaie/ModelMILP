package MultiMap;

public class MapAmir<K, V> {

    private K[] key;
    private V value;
    private String Name;
    private double amount;

    public MapAmir(String Name, V value, K... key) {
        this.key = key;
        this.value = value;
        this.Name = Name;
        this.amount = Double.NaN;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {

        String re = this.Name + "(";

        for (int i = 0; i < key.length - 1; i++)
            re += key[i] + ",";
        if (!Double.isNaN(this.amount))
            re += key[key.length - 1] + ") = " + this.amount;
        else
            re += key[key.length - 1] + ") = " + this.value;


        return re;
    }
}