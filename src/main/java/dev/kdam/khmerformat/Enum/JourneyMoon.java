package dev.kdam.khmerformat.Enum;

public enum JourneyMoon {
    WAXING("កើត"),
    WANING("រោច");
    private final String label;
    JourneyMoon(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
