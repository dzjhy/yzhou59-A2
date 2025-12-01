public class Employee extends Person {
    // 2 instance variables (required by Part 1)
    private String employeeId;
    private String responsibleRideType;

    // Default constructor (required by Part 1)
    public Employee() {}

    // Parameterized constructor (initializes parent class properties simultaneously, required by Part 1)
    public Employee(String name, int age, String idCard, String employeeId, String responsibleRideType) {
        super(name, age, idCard); // Call parent class constructor
        this.employeeId = employeeId;
        this.responsibleRideType = responsibleRideType;
    }

    // Getters and setters for all instance variables (required by Part 1)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getResponsibleRideType() {
        return responsibleRideType;
    }

    public void setResponsibleRideType(String responsibleRideType) {
        this.responsibleRideType = responsibleRideType;
    }

    // Override toString() to print employee information
    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + ", Responsible Ride Type: " + responsibleRideType;
    }
}