package chain_of_responsibility.enums;

/**
 * Enum representing different currency denominations supported by the ATM
 */
public enum CurrencyDenomination {
    THOUSAND(1000, "₹1000"),
    FIVE_HUNDRED(500, "₹500"),
    TWO_HUNDRED(200, "₹200"),
    HUNDRED(100, "₹100");

    private final int value;
    private final String displayName;

    CurrencyDenomination(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }
}