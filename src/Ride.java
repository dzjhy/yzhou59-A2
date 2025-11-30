import java.io.*;
import java.util.*;

public class Ride implements RideInterface {
    // 3个基础实例变量（含1个Employee类型，Part 1要求）
    private String rideName;
    private String rideType;
    private Employee operator; // 负责该设施的员工（Part 1要求）

    // Part 3：等待队列（Queue<Visitor>，FIFO）
    private Queue<Visitor> waitingLine;

    // Part 4A：乘坐记录（LinkedList<Visitor>）
    private LinkedList<Visitor> rideHistory;

    // Part 5：运行属性（最大载客量、运行次数）
    private int maxRider; // 一个循环最大载客量
    private int numOfCycles; // 已运行循环次数（默认0）

    // 默认构造方法（Part 1要求）
    public Ride() {
        this.waitingLine = new LinkedList<>(); // 用LinkedList实现Queue
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0; // 默认运行次数为0
    }

    // 带参构造方法（Part 1要求）
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.maxRider = maxRider;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // 所有实例变量的getter和setter（Part 1要求）
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = maxRider;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    // ------------------------------ Part 3：等待队列方法实现 ------------------------------
    /**
     * 添加游客到等待队列
     * @param visitor 待添加的游客
     */
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.offer(visitor); // Queue的添加方法（FIFO）
            System.out.println("成功添加游客【" + visitor.getName() + "】到【" + rideName + "】的等待队列");
        } else {
            System.out.println("添加失败：游客信息不能为空");
        }
    }

    /**
     * 从等待队列移除头部游客（FIFO）
     */
    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("移除失败：【" + rideName + "】的等待队列为空");
            return;
        }
        Visitor removedVisitor = waitingLine.poll(); // Queue的移除方法（返回头部元素）
        System.out.println("成功从【" + rideName + "】的等待队列移除游客：" + removedVisitor.getName());
    }

    /**
     * 打印等待队列所有游客
     */
    @Override
    public void printQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("【" + rideName + "】的等待队列为空");
            return;
        }
        System.out.println("【" + rideName + "】的等待队列（共" + waitingLine.size() + "人）：");
        int index = 1;
        for (Visitor visitor : waitingLine) { // 按添加顺序遍历
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ Part 4A：乘坐记录方法实现 ------------------------------
    /**
     * 添加游客到乘坐记录
     * @param visitor 待添加的游客
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("成功添加游客【" + visitor.getName() + "】到【" + rideName + "】的乘坐记录");
        } else {
            System.out.println("添加失败：游客信息不能为空");
        }
    }

    /**
     * 检查游客是否在乘坐记录中
     * @param visitor 待检查的游客
     * @return true=存在，false=不存在
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("检查失败：游客信息不能为空");
            return false;
        }
        // 遍历乘坐记录（按身份证号匹配，避免同名问题）
        for (Visitor v : rideHistory) {
            if (v.getIdCard().equals(visitor.getIdCard())) {
                System.out.println("检查结果：游客【" + visitor.getName() + "】在【" + rideName + "】的乘坐记录中");
                return true;
            }
        }
        System.out.println("检查结果：游客【" + visitor.getName() + "】不在【" + rideName + "】的乘坐记录中");
        return false;
    }

    /**
     * 返回乘坐记录中的游客数量
     * @return 游客数量
     */
    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("【" + rideName + "】的乘坐记录中共" + count + "名游客");
        return count;
    }

    /**
     * 打印乘坐记录所有游客（必须用Iterator，Part 4A要求）
     */
    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("【" + rideName + "】的乘坐记录为空");
            return;
        }
        System.out.println("【" + rideName + "】的乘坐记录（共" + rideHistory.size() + "人）：");
        int index = 1;
        Iterator<Visitor> iterator = rideHistory.iterator(); // 使用Iterator遍历
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ Part 4B：乘坐记录排序方法 ------------------------------
    /**
     * 按自定义比较器排序乘坐记录
     * @param comparator 游客比较器（实现Comparator）
     */
    public void sortRideHistory(VisitorComparator comparator) {
        if (rideHistory.isEmpty()) {
            System.out.println("排序失败：【" + rideName + "】的乘坐记录为空");
            return;
        }
        Collections.sort(rideHistory, comparator); // 使用Collections.sort+Comparator
        System.out.println("成功对【" + rideName + "】的乘坐记录进行排序");
    }

    // ------------------------------ Part 5：运行设施循环方法实现 ------------------------------
    /**
     * 运行一个设施循环：从队列取maxRider名游客，添加到乘坐记录
     */
    @Override
    public void runOneCycle() {
        // 检查1：是否有负责员工
        if (operator == null) {
            System.out.println("运行失败：【" + rideName + "】未分配负责员工，无法启动");
            return;
        }
        // 检查2：等待队列是否有游客
        if (waitingLine.isEmpty()) {
            System.out.println("运行失败：【" + rideName + "】的等待队列为空，无法启动");
            return;
        }

        // 计算本次循环可载客数量（取队列大小和maxRider的最小值）
        int takeCount = Math.min(waitingLine.size(), maxRider);
        System.out.println("【" + rideName + "】开始运行第" + (numOfCycles + 1) + "个循环，本次将搭载" + takeCount + "名游客");

        // 从队列取游客，添加到乘坐记录
        for (int i = 0; i < takeCount; i++) {
            Visitor visitor = waitingLine.poll(); // 移除队列头部游客
            rideHistory.add(visitor); // 添加到乘坐记录
            System.out.println("游客【" + visitor.getName() + "】已乘坐【" + rideName + "】");
        }

        // 运行次数+1
        numOfCycles++;
        System.out.println("【" + rideName + "】第" + numOfCycles + "个循环运行完成");
    }

    // ------------------------------ Part 6：导出乘坐记录到文件（CSV格式） ------------------------------
    /**
     * 导出乘坐记录到CSV文件（每行一条游客信息，逗号分隔）
     * @param filePath 文件路径（如：src/ride_history.csv）
     */
    public void exportRideHistory(String filePath) {
        if (rideHistory.isEmpty()) {
            System.out.println("导出失败：【" + rideName + "】的乘坐记录为空");
            return;
        }

        // 处理文件写入异常（Part 6要求：异常处理）
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入文件头部（列名）
            writer.write("姓名,年龄,身份证号,游客类型,是否有快速通行证");
            writer.newLine(); // 换行

            // 写入每条游客信息（CSV格式）
            for (Visitor visitor : rideHistory) {
                String line = String.format("%s,%d,%s,%s,%s",
                        visitor.getName(),
                        visitor.getAge(),
                        visitor.getIdCard(),
                        visitor.getVisitorType(),
                        visitor.isHasFastPass() ? "是" : "否");
                writer.write(line);
                writer.newLine();
            }

            System.out.println("成功导出【" + rideName + "】的乘坐记录到文件：" + filePath);
        } catch (IOException e) {
            System.out.println("导出失败：文件写入错误，原因：" + e.getMessage());
        }
    }

    // ------------------------------ Part 7：从文件导入乘坐记录（CSV格式） ------------------------------
    /**
     * 从CSV文件导入乘坐记录，添加到rideHistory
     * @param filePath 文件路径（如：src/ride_history.csv）
     */
    public void importRideHistory(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("导入失败：文件不存在，路径：" + filePath);
            return;
        }

        // 处理文件读取异常（Part 7要求：异常处理）
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (lineNum == 1) {
                    continue; // 跳过文件头部（列名）
                }

                // 拆分CSV行（按逗号分隔）
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("跳过无效行（第" + lineNum + "行）：列数不足");
                    continue;
                }

                // 解析游客信息（处理格式异常）
                try {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    String idCard = parts[2].trim();
                    String visitorType = parts[3].trim();
                    boolean hasFastPass = parts[4].trim().equals("是");

                    // 创建游客对象，添加到乘坐记录
                    Visitor visitor = new Visitor(name, age, idCard, visitorType, hasFastPass);
                    rideHistory.add(visitor);
                    System.out.println("成功导入游客：" + visitor.getName());
                } catch (NumberFormatException e) {
                    System.out.println("跳过无效行（第" + lineNum + "行）：年龄格式错误");
                }
            }

            System.out.println("导入完成：共从文件【" + filePath + "】导入" + (rideHistory.size()) + "名游客到【" + rideName + "】的乘坐记录");
        } catch (IOException e) {
            System.out.println("导入失败：文件读取错误，原因：" + e.getMessage());
        }
    }
}