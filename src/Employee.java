public class Employee extends Person {
    // 2个实例变量（Part 1要求）
    private String employeeId;
    private String responsibleRideType;

    // 默认构造方法（Part 1要求）
    public Employee() {}

    // 带参构造方法（同时初始化父类属性，Part 1要求）
    public Employee(String name, int age, String idCard, String employeeId, String responsibleRideType) {
        super(name, age, idCard); // 调用父类构造方法
        this.employeeId = employeeId;
        this.responsibleRideType = responsibleRideType;
    }

    // 所有实例变量的getter和setter（Part 1要求）
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

    // 重写toString()用于打印员工信息
    @Override
    public String toString() {
        return super.toString() + "，员工编号：" + employeeId + "，负责设施类型：" + responsibleRideType;
    }
}