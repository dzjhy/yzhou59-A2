public abstract class Person {
    // 3 instance variables (required by Part 1)
    private String name;
    private int age;
    private String idCard;

    // Default constructor (required by Part 1)
    public Person() {}

    // Parameterized constructor (required by Part 1)
    public Person(String name, int age, String idCard) {
        this.name = name;
        this.age = age;
        this.idCard = idCard;
    }

    // Getters and setters for all instance variables (required by Part 1)
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

    // Override toString() to print person information
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", ID Card Number: " + idCard;
    }
}