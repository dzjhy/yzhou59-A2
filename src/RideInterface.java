import java.util.Queue;

public interface RideInterface {
    // Part 3：等待队列操作
    void addVisitorToQueue(Visitor visitor); // 添加游客到队列
    void removeVisitorFromQueue(); // 移除队列头部游客（FIFO）
    void printQueue(); // 打印队列所有游客

    // Part 4：乘坐记录操作
    void addVisitorToHistory(Visitor visitor); // 添加游客到乘坐记录
    boolean checkVisitorFromHistory(Visitor visitor); // 检查游客是否在记录中
    int numberOfVisitors(); // 返回记录中游客数量
    void printRideHistory(); // 打印乘坐记录（需用Iterator）

    // Part 5：运行设施循环
    void runOneCycle(); // 运行一个设施循环
}