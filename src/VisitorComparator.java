import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    /**
     * Compares two visitors: first by age in ascending order;
     * if ages are equal, by visitor type (Adult > Child > Elderly)
     * @param v1 The first visitor
     * @param v2 The second visitor
     * @return Comparison result (negative = v1 comes first, positive = v2 comes first, 0 = equal)
     */
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. First compare by age (ascending order)
        if (v1.getAge() != v2.getAge()) {
            return v1.getAge() - v2.getAge();
        }

        // 2. If ages are equal, compare by visitor type (custom priority: Adult > Child > Elderly)
        String type1 = v1.getVisitorType();
        String type2 = v2.getVisitorType();

        // Define type priority: Adult = 3, Child = 2, Elderly = 1, Others = 0
        int priority1 = getTypePriority(type1);
        int priority2 = getTypePriority(type2);

        return priority2 - priority1; // Higher priority comes first (descending order)
    }

    /**
     * Helper method: Gets the priority of the visitor type
     * @param type Visitor type (Adult/Child/Elderly/Other)
     * @return Priority value
     */
    private int getTypePriority(String type) {
        switch (type) {
            case "Adult":
                return 3;
            case "Child":
                return 2;
            case "Elderly":
                return 1;
            default:
                return 0;
        }
    }
}