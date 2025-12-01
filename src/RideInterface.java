import java.util.Queue;

public interface RideInterface {
    // Part 3: Waiting Queue Operations
    void addVisitorToQueue(Visitor visitor); // Add a visitor to the queue
    void removeVisitorFromQueue(); // Remove the front visitor from the queue (FIFO)
    void printQueue(); // Print all visitors in the queue

    // Part 4: Ride History Operations
    void addVisitorToHistory(Visitor visitor); // Add a visitor to the ride history
    boolean checkVisitorFromHistory(Visitor visitor); // Check if a visitor is in the history
    int numberOfVisitors(); // Return the number of visitors in the history
    void printRideHistory(); // Print the ride history (must use Iterator)

    // Part 5: Ride Operation Cycle
    void runOneCycle(); // Run one ride operation cycle
}