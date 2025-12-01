public class Visitor extends Person {
    // 2 instance variables (required by Part 1)
    private String visitorType; // e.g.: Adult, Child, Elderly
    private boolean hasFastPass; // Whether the visitor has a fast pass

    // Default constructor (required by Part 1)
    public Visitor() {}

    // Parameterized constructor (initializes parent class properties simultaneously, required by Part 1)
    public Visitor(String name, int age, String idCard, String visitorType, boolean hasFastPass) {
        super(name, age, idCard); // Call parent class constructor
        this.visitorType = visitorType;
        this.hasFastPass = hasFastPass;
    }

    // Getters and setters for all instance variables (required by Part 1)
    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public boolean isHasFastPass() {
        return hasFastPass;
    }

    public void setHasFastPass(boolean hasFastPass) {
        this.hasFastPass = hasFastPass;
    }

    // Override toString() to print visitor information
    @Override
    public String toString() {
        return super.toString() + ", Visitor Type: " + visitorType + ", Has Fast Pass: " + (hasFastPass ? "Yes" : "No");
    }
}