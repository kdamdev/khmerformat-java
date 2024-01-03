package dev.kdam.khmerformat.enums;

/**
 * បើគិតតាមចន្ទគតិដំណើរនៃព្រះចន្ទមានខ្នើតមានរនោច ។
 * ការរាប់ថ្ងៃ ចាប់ផ្តើមរាប់ពីថ្ងៃ ១កើត ដល់ ១៥កើត បន្ទាប់មកថ្ងៃ ១រោច ដល់ ១៤រោចក្នុងខែសេស ឬ ១៥រោចក្នុងខែគូ ។
 */
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
