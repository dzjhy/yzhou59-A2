public class Visitor extends Person {
    // 2个实例变量（Part 1要求）
    private String visitorType; // 如：成人、儿童、老人
    private boolean hasFastPass; // 是否有快速通行证

    // 默认构造方法（Part 1要求）
    public Visitor() {}

    // 带参构造方法（同时初始化父类属性，Part 1要求）
    public Visitor(String name, int age, String idCard, String visitorType, boolean hasFastPass) {
        super(name, age, idCard); // 调用父类构造方法
        this.visitorType = visitorType;
        this.hasFastPass = hasFastPass;
    }

    // 所有实例变量的getter和setter（Part 1要求）
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

    // 重写toString()用于打印游客信息
    @Override
    public String toString() {
        return super.toString() + "，游客类型：" + visitorType + "，是否有快速通行证：" + (hasFastPass ? "是" : "否");
    }
}