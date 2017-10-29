package model;

/**
 * Created by Shashank Kushwah on 29/10/2017.
 */
public enum TradeType {
    BUY("B"),
    SELL("S");

    private String type;

    TradeType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static TradeType fromString(String type) {

        if (type != null) {
            for (TradeType tmp : TradeType.values()) {
                if (type.equalsIgnoreCase(tmp.type)) {
                    return tmp;
                }
            }

            throw new IllegalArgumentException("No enumeration constant with type " + type + " found!");
        } else {
            throw new NullPointerException("Null pointer exception.");
        }
    }

    @Override
    public String toString() {
        return this.type;
    }
}

