package dev.kdam.Utils;

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
