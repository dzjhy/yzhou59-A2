import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    /**
     * 比较两个游客：先按年龄升序，年龄相同则按游客类型（成人>儿童>老人）
     * @param v1 第一个游客
     * @param v2 第二个游客
     * @return 比较结果（负=v1在前，正=v2在前，0=相等）
     */
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. 先比较年龄（升序）
        if (v1.getAge() != v2.getAge()) {
            return v1.getAge() - v2.getAge();
        }

        // 2. 年龄相同，比较游客类型（自定义优先级：成人>儿童>老人）
        String type1 = v1.getVisitorType();
        String type2 = v2.getVisitorType();

        // 定义类型优先级：成人=3，儿童=2，老人=1，其他=0
        int priority1 = getTypePriority(type1);
        int priority2 = getTypePriority(type2);

        return priority2 - priority1; // 优先级高的在前（降序）
    }

    /**
     * 辅助方法：获取游客类型的优先级
     * @param type 游客类型（成人/儿童/老人/其他）
     * @return 优先级数值
     */
    private int getTypePriority(String type) {
        switch (type) {
            case "成人":
                return 3;
            case "儿童":
                return 2;
            case "老人":
                return 1;
            default:
                return 0;
        }
    }
}