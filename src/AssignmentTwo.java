import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class AssignmentTwo {
    // 主方法（严格遵循 Java 规范，确保 IDEA 识别为运行入口）
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();

        // 按作业要求顺序运行所有模块（Part 3-7）
        System.out.println("==================== Part 3 演示：等待队列 ====================");
        demo.partThree();

        System.out.println("\n==================== Part 4A 演示：乘坐记录 ====================");
        demo.partFourA();

        System.out.println("\n==================== Part 4B 演示：记录排序 ====================");
        demo.partFourB();

        System.out.println("\n==================== Part 5 演示：运行设施循环 ====================");
        demo.partFive();

        System.out.println("\n==================== Part 6 演示：导出记录到文件 ====================");
        demo.partSix();

        System.out.println("\n==================== Part 7 演示：从文件导入记录 ====================");
        demo.partSeven();
    }

    // Part 3：等待队列（添加、移除、打印）
    public void partThree() {
        // 创建员工（作为设施操作员）
        Employee operator = new Employee("张三", 30, "440101199401011234", "EMP001", "过山车操作员");
        // 创建游乐设施（极速过山车，容量2人）
        Ride rollerCoaster = new Ride("极速过山车", "刺激类", operator, 2);

        // 添加5名游客到等待队列
        rollerCoaster.addVisitorToQueue(new Visitor("李四", 25, "440101199901011234", "成人", false));
        rollerCoaster.addVisitorToQueue(new Visitor("王五", 18, "440101200601011234", "成人", true));
        rollerCoaster.addVisitorToQueue(new Visitor("赵六", 12, "440101201201011234", "儿童", false));
        rollerCoaster.addVisitorToQueue(new Visitor("孙七", 35, "440101198901011234", "成人", false));
        rollerCoaster.addVisitorToQueue(new Visitor("周八", 60, "440101196401011234", "老人", true));

        // 打印添加后的队列
        System.out.println("\n添加5名游客后的等待队列：");
        rollerCoaster.printQueue();

        // 移除1名游客（队列头部）
        rollerCoaster.removeVisitorFromQueue();

        // 打印移除后的队列
        System.out.println("\n移除队首1名游客后的等待队列：");
        rollerCoaster.printQueue();
    }

    // Part 4A：乘坐记录（添加、查询、遍历打印）
    public void partFourA() {
        // 创建游乐设施（梦幻旋转木马，容量4人）
        Ride carousel = new Ride("梦幻旋转木马", "亲子类", null, 4);

        // 创建5名游客并添加到乘坐记录
        Visitor v1 = new Visitor("钱九", 22, "440101200201011234", "成人", false);
        Visitor v2 = new Visitor("吴十", 8, "440101201601011234", "儿童", true);
        Visitor v3 = new Visitor("郑十一", 28, "440101199601011234", "成人", false);
        Visitor v4 = new Visitor("王十二", 5, "440101201901011234", "儿童", false);
        Visitor v5 = new Visitor("冯十三", 32, "440101199201011234", "成人", true);

        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        // 查询指定游客是否在记录中
        System.out.println("\n查询游客【吴十】是否在乘坐记录中：");
        carousel.checkVisitorFromHistory(v2);

        // 打印记录总数
        carousel.numberOfVisitors();

        // 遍历打印所有乘坐记录
        System.out.println("\n所有乘坐记录（Iterator 遍历）：");
        carousel.printRideHistory();
    }

    // Part 4B：乘坐记录排序（按年龄升序→游客类型排序）
    public void partFourB() {
        // 创建游乐设施（海盗船，容量3人）
        Ride pirateShip = new Ride("海盗船", "刺激类", null, 3);

        // 添加5名游客（年龄、类型随机）
        pirateShip.addVisitorToHistory(new Visitor("陈十四", 25, "440101199902021234", "成人", false));
        pirateShip.addVisitorToHistory(new Visitor("杨十五", 12, "440101201202021234", "儿童", true));
        pirateShip.addVisitorToHistory(new Visitor("黄十六", 25, "440101199903031234", "老人", false));
        pirateShip.addVisitorToHistory(new Visitor("朱十七", 30, "440101199402021234", "成人", true));
        pirateShip.addVisitorToHistory(new Visitor("秦十八", 18, "440101200602021234", "成人", false));

        // 排序前打印
        System.out.println("\n排序前（原始顺序）：");
        pirateShip.printRideHistory();

        // 使用自定义比较器排序（年龄升序，同年龄按 儿童→成人→老人 排序）
        VisitorComparator comparator = new VisitorComparator();
        pirateShip.sortRideHistory(comparator);

        // 排序后打印
        System.out.println("\n排序后（年龄升序→游客类型）：");
        pirateShip.printRideHistory();
    }

    // Part 5：运行设施循环（单次循环：从队列取游客→添加到记录→打印结果）
    public void partFive() {
        // 创建员工（作为水上设施操作员）
        Employee operator = new Employee("林十九", 28, "440101199603031234", "EMP002", "水上设施操作员");
        // 创建游乐设施（激流勇进，容量3人）
        Ride logFlume = new Ride("激流勇进", "水上类", operator, 3);

        // 批量添加10名游客到等待队列
        for (int i = 1; i <= 10; i++) {
            String visitorType = i % 3 == 0 ? "儿童" : (i % 3 == 1 ? "成人" : "老人");
            Visitor visitor = new Visitor(
                    "游客" + i,
                    20 + i,
                    "440101" + (2000 - i) + "03031234",
                    visitorType,
                    i % 2 == 0  // 偶数索引游客有快速通行证
            );
            logFlume.addVisitorToQueue(visitor);
        }

        // 运行前打印队列
        System.out.println("\n运行前等待队列（共10人）：");
        logFlume.printQueue();

        // 运行1次设施循环（按容量取游客，添加到乘坐记录）
        logFlume.runOneCycle();

        // 运行后打印队列（剩余游客）
        System.out.println("\n运行1次循环后等待队列（剩余7人）：");
        logFlume.printQueue();

        // 运行后打印乘坐记录（新增3人）
        System.out.println("\n运行1次循环后的乘坐记录（新增3人）：");
        logFlume.printRideHistory();
    }

    // Part 6：文件导出（相对路径，生成在项目根目录）
    public void partSix() {
        // 创建游乐设施（摩天轮，容量6人）
        Ride ferrisWheel = new Ride("摩天轮", "观景类", null, 6);

        // 添加5名游客到乘坐记录（用于导出）
        ferrisWheel.addVisitorToHistory(new Visitor("蒋二十", 22, "440101200204041234", "成人", false));
        ferrisWheel.addVisitorToHistory(new Visitor("沈二十一", 25, "440101199904041234", "成人", true));
        ferrisWheel.addVisitorToHistory(new Visitor("韩二十二", 15, "440101200904041234", "儿童", false));
        ferrisWheel.addVisitorToHistory(new Visitor("杨二十三", 35, "440101198904041234", "成人", false));
        ferrisWheel.addVisitorToHistory(new Visitor("朱二十四", 55, "440101196904041234", "老人", true));

        // 相对路径：文件生成在 yzhou59-A2 项目根目录（无需改路径）
        String filePath = "ferris_wheel_history.csv";
        ferrisWheel.exportRideHistory(filePath);
    }

    // Part 7：文件导入（与导出相对路径一致，读取项目根目录的 CSV 文件）
    public void partSeven() {
        // 创建游乐设施（用于存储导入的记录）
        Ride ferrisWheelImport = new Ride("摩天轮（导入）", "观景类", null, 6);

        // 与 Part 6 完全一致的相对路径（读取同一文件）
        String filePath = "ferris_wheel_history.csv";
        ferrisWheelImport.importRideHistory(filePath);

        // 打印导入后的记录总数
        ferrisWheelImport.numberOfVisitors();

        // 打印导入的所有游客信息
        System.out.println("\n导入的游客详细信息：");
        ferrisWheelImport.printRideHistory();
    }
}