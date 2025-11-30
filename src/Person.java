public abstract class Person {
    // 3个实例变量（Part 1要求）
    private String name;
    private int age;
    private String idCard;

    // 默认构造方法（Part 1要求）
    public Person() {}

    // 带参构造方法（Part 1要求）
    public Person(String name, int age, String idCard) {
        this.name = name;
        this.age = age;
        this.idCard = idCard;
    }

    // 所有实例变量的getter和setter（Part 1要求）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    // 重写toString()用于打印人员信息
    @Override
    public String toString() {
        return "姓名：" + name + "，年龄：" + age + "，身份证号：" + idCard;
    }
}