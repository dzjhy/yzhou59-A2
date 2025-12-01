import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class AssignmentTwo {
    // Main method (strictly follows Java specifications to ensure IDEA recognizes it as the entry point)
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();

        // Run all modules in the order required by the assignment (Part 3-7)
        System.out.println("==================== Part 3 Demo: Waiting Queue ====================");
        demo.partThree();

        System.out.println("\n==================== Part 4A Demo: Ride History ====================");
        demo.partFourA();

        System.out.println("\n==================== Part 4B Demo: History Sorting ====================");
        demo.partFourB();

        System.out.println("\n==================== Part 5 Demo: Ride Operation Cycle ====================");
        demo.partFive();

        System.out.println("\n==================== Part 6 Demo: Export History to File ====================");
        demo.partSix();

        System.out.println("\n==================== Part 7 Demo: Import History from File ====================");
        demo.partSeven();
    }

    // Part 3: Waiting Queue (add, remove, print)
    public void partThree() {
        // Create an employee (as the ride operator)
        Employee operator = new Employee("Zhang San", 30, "440101199401011234", "EMP001", "Roller Coaster Operator");
        // Create a ride (Extreme Roller Coaster, capacity: 2 people)
        Ride rollerCoaster = new Ride("Extreme Roller Coaster", "Thrilling", operator, 2);

        // Add 5 visitors to the waiting queue
        rollerCoaster.addVisitorToQueue(new Visitor("Li Si", 25, "440101199901011234", "Adult", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Wang Wu", 18, "440101200601011234", "Adult", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Zhao Liu", 12, "440101201201011234", "Child", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Sun Qi", 35, "440101198901011234", "Adult", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Zhou Ba", 60, "440101196401011234", "Elderly", true));

        // Print the queue after adding visitors
        System.out.println("\nWaiting queue after adding 5 visitors:");
        rollerCoaster.printQueue();

        // Remove 1 visitor (from the front of the queue)
        rollerCoaster.removeVisitorFromQueue();

        // Print the queue after removal
        System.out.println("\nWaiting queue after removing the front visitor:");
        rollerCoaster.printQueue();
    }

    // Part 4A: Ride History (add, query, traverse and print)
    public void partFourA() {
        // Create a ride (Fantasy Carousel, capacity: 4 people)
        Ride carousel = new Ride("Fantasy Carousel", "Family-Friendly", null, 4);

        // Create 5 visitors and add them to the ride history
        Visitor v1 = new Visitor("Qian Jiu", 22, "440101200201011234", "Adult", false);
        Visitor v2 = new Visitor("Wu Shi", 8, "440101201601011234", "Child", true);
        Visitor v3 = new Visitor("Zheng Shi Yi", 28, "440101199601011234", "Adult", false);
        Visitor v4 = new Visitor("Wang Shi Er", 5, "440101201901011234", "Child", false);
        Visitor v5 = new Visitor("Feng Shi San", 32, "440101199201011234", "Adult", true);

        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        // Query if the specified visitor is in the history
        System.out.println("\nQuery if visitor [Wu Shi] is in the ride history:");
        carousel.checkVisitorFromHistory(v2);

        // Print the total number of visitors in history
        carousel.numberOfVisitors();

        // Traverse and print all ride history (using Iterator)
        System.out.println("\nAll ride history (Iterator traversal):");
        carousel.printRideHistory();
    }

    // Part 4B: Ride History Sorting (sorted by age ascending → visitor type)
    public void partFourB() {
        // Create a ride (Pirate Ship, capacity: 3 people)
        Ride pirateShip = new Ride("Pirate Ship", "Thrilling", null, 3);

        // Add 5 visitors (random ages and types)
        pirateShip.addVisitorToHistory(new Visitor("Chen Shi Si", 25, "440101199902021234", "Adult", false));
        pirateShip.addVisitorToHistory(new Visitor("Yang Shi Wu", 12, "440101201202021234", "Child", true));
        pirateShip.addVisitorToHistory(new Visitor("Huang Shi Liu", 25, "440101199903031234", "Elderly", false));
        pirateShip.addVisitorToHistory(new Visitor("Zhu Shi Qi", 30, "440101199402021234", "Adult", true));
        pirateShip.addVisitorToHistory(new Visitor("Qin Shi Ba", 18, "440101200602021234", "Adult", false));

        // Print before sorting
        System.out.println("\nBefore sorting (original order):");
        pirateShip.printRideHistory();

        // Sort using custom comparator (age ascending, then Child → Adult → Elderly for same ages)
        VisitorComparator comparator = new VisitorComparator();
        pirateShip.sortRideHistory(comparator);

        // Print after sorting
        System.out.println("\nAfter sorting (age ascending → visitor type):");
        pirateShip.printRideHistory();
    }

    // Part 5: Ride Operation Cycle (single cycle: take visitors from queue → add to history → print results)
    public void partFive() {
        // Create an employee (as the water ride operator)
        Employee operator = new Employee("Lin Shi Jiu", 28, "440101199603031234", "EMP002", "Water Ride Operator");
        // Create a ride (Log Flume, capacity: 3 people)
        Ride logFlume = new Ride("Log Flume", "Water-Based", operator, 3);

        // Batch add 10 visitors to the waiting queue
        for (int i = 1; i <= 10; i++) {
            String visitorType = i % 3 == 0 ? "Child" : (i % 3 == 1 ? "Adult" : "Elderly");
            Visitor visitor = new Visitor(
                    "Visitor " + i,
                    20 + i,
                    "440101" + (2000 - i) + "03031234",
                    visitorType,
                    i % 2 == 0  // Visitors with even indices have fast passes
            );
            logFlume.addVisitorToQueue(visitor);
        }

        // Print queue before operation
        System.out.println("\nWaiting queue before operation (total 10 people):");
        logFlume.printQueue();

        // Run one operation cycle (take visitors by capacity and add to ride history)
        logFlume.runOneCycle();

        // Print queue after operation (remaining visitors)
        System.out.println("\nWaiting queue after one cycle (remaining 7 people):");
        logFlume.printQueue();

        // Print ride history after operation (3 new additions)
        System.out.println("\nRide history after one cycle (3 new additions):");
        logFlume.printRideHistory();
    }

    // Part 6: Export History to File (relative path, generated in project root directory)
    public void partSix() {
        // Create a ride (Ferris Wheel, capacity: 6 people)
        Ride ferrisWheel = new Ride("Ferris Wheel", "Sightseeing", null, 6);

        // Add 5 visitors to ride history (for export)
        ferrisWheel.addVisitorToHistory(new Visitor("Jiang Er Shi", 22, "440101200204041234", "Adult", false));
        ferrisWheel.addVisitorToHistory(new Visitor("Shen Er Shi Yi", 25, "440101199904041234", "Adult", true));
        ferrisWheel.addVisitorToHistory(new Visitor("Han Er Shi Er", 15, "440101200904041234", "Child", false));
        ferrisWheel.addVisitorToHistory(new Visitor("Yang Er Shi San", 35, "440101198904041234", "Adult", false));
        ferrisWheel.addVisitorToHistory(new Visitor("Zhu Er Shi Si", 55, "440101196904041234", "Elderly", true));

        // Relative path: file generated in yzhou59-A2 project root directory (no need to modify path)
        String filePath = "ferris_wheel_history.csv";
        ferrisWheel.exportRideHistory(filePath);
    }

    // Part 7: Import History from File (same relative path as export, reads CSV from project root directory)
    public void partSeven() {
        // Create a ride (for storing imported history)
        Ride ferrisWheelImport = new Ride("Ferris Wheel (Imported)", "Sightseeing", null, 6);

        // Same relative path as Part 6 (reads the same file)
        String filePath = "ferris_wheel_history.csv";
        ferrisWheelImport.importRideHistory(filePath);

        // Print total number of imported visitors
        ferrisWheelImport.numberOfVisitors();

        // Print detailed information of imported visitors
        System.out.println("\nDetailed information of imported visitors:");
        ferrisWheelImport.printRideHistory();
    }
}