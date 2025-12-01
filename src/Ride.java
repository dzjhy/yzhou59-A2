import java.io.*;
import java.util.*;

public class Ride implements RideInterface {
    // 3 basic instance variables (including 1 Employee type, required by Part 1)
    private String rideName;
    private String rideType;
    private Employee operator; // Employee responsible for this ride (required by Part 1)

    // Part 3: Waiting Queue (Queue<Visitor>, FIFO)
    private Queue<Visitor> waitingLine;

    // Part 4A: Ride History (LinkedList<Visitor>)
    private LinkedList<Visitor> rideHistory;

    // Part 5: Operation Properties (maximum capacity, number of cycles operated)
    private int maxRider; // Maximum number of riders per cycle
    private int numOfCycles; // Number of cycles already operated (default: 0)

    // Default constructor (required by Part 1)
    public Ride() {
        this.waitingLine = new LinkedList<>(); // Implement Queue with LinkedList
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0; // Default number of cycles is 0
    }

    // Parameterized constructor (required by Part 1)
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.maxRider = maxRider;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Getters and setters for all instance variables (required by Part 1)
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

    // ------------------------------ Part 3: Waiting Queue Method Implementations ------------------------------
    /**
     * Adds a visitor to the waiting queue
     * @param visitor The visitor to be added
     */
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.offer(visitor); // Queue's add method (FIFO)
            System.out.println("Successfully added visitor [" + visitor.getName() + "] to the waiting queue of [" + rideName + "]");
        } else {
            System.out.println("Add failed: Visitor information cannot be empty");
        }
    }

    /**
     * Removes the visitor at the front of the waiting queue (FIFO)
     */
    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("Removal failed: The waiting queue of [" + rideName + "] is empty");
            return;
        }
        Visitor removedVisitor = waitingLine.poll(); // Queue's remove method (returns front element)
        System.out.println("Successfully removed visitor from the waiting queue of [" + rideName + "]: " + removedVisitor.getName());
    }

    /**
     * Prints all visitors in the waiting queue
     */
    @Override
    public void printQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("The waiting queue of [" + rideName + "] is empty");
            return;
        }
        System.out.println("Waiting queue of [" + rideName + "] (total " + waitingLine.size() + " people):");
        int index = 1;
        for (Visitor visitor : waitingLine) { // Traverse in the order of addition
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ Part 4A: Ride History Method Implementations ------------------------------
    /**
     * Adds a visitor to the ride history
     * @param visitor The visitor to be added
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("Successfully added visitor [" + visitor.getName() + "] to the ride history of [" + rideName + "]");
        } else {
            System.out.println("Add failed: Visitor information cannot be empty");
        }
    }

    /**
     * Checks if a visitor is in the ride history
     * @param visitor The visitor to be checked
     * @return true = exists, false = does not exist
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Check failed: Visitor information cannot be empty");
            return false;
        }
        // Traverse ride history (match by ID card number to avoid duplicate name issues)
        for (Visitor v : rideHistory) {
            if (v.getIdCard().equals(visitor.getIdCard())) {
                System.out.println("Check result: Visitor [" + visitor.getName() + "] is in the ride history of [" + rideName + "]");
                return true;
            }
        }
        System.out.println("Check result: Visitor [" + visitor.getName() + "] is not in the ride history of [" + rideName + "]");
        return false;
    }

    /**
     * Returns the number of visitors in the ride history
     * @return Number of visitors
     */
    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("The ride history of [" + rideName + "] contains " + count + " visitors");
        return count;
    }

    /**
     * Prints all visitors in the ride history (must use Iterator, required by Part 4A)
     */
    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("The ride history of [" + rideName + "] is empty");
            return;
        }
        System.out.println("Ride history of [" + rideName + "] (total " + rideHistory.size() + " people):");
        int index = 1;
        Iterator<Visitor> iterator = rideHistory.iterator(); // Traverse using Iterator
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ Part 4B: Ride History Sorting Method ------------------------------
    /**
     * Sorts the ride history using a custom comparator
     * @param comparator Visitor comparator (implements Comparator)
     */
    public void sortRideHistory(VisitorComparator comparator) {
        if (rideHistory.isEmpty()) {
            System.out.println("Sort failed: The ride history of [" + rideName + "] is empty");
            return;
        }
        Collections.sort(rideHistory, comparator); // Use Collections.sort + Comparator
        System.out.println("Successfully sorted the ride history of [" + rideName + "]");
    }

    // ------------------------------ Part 5: Ride Operation Cycle Method Implementation ------------------------------
    /**
     * Runs one ride cycle: takes maxRider visitors from the queue and adds them to the ride history
     */
    @Override
    public void runOneCycle() {
        // Check 1: Whether there is a responsible employee
        if (operator == null) {
            System.out.println("Operation failed: No responsible employee assigned to [" + rideName + "], cannot start");
            return;
        }
        // Check 2: Whether there are visitors in the waiting queue
        if (waitingLine.isEmpty()) {
            System.out.println("Operation failed: The waiting queue of [" + rideName + "] is empty, cannot start");
            return;
        }

        // Calculate the number of riders for this cycle (take the minimum of queue size and maxRider)
        int takeCount = Math.min(waitingLine.size(), maxRider);
        System.out.println("[" + rideName + "] starts the " + (numOfCycles + 1) + "th cycle, will carry " + takeCount + " visitors this time");

        // Take visitors from the queue and add them to the ride history
        for (int i = 0; i < takeCount; i++) {
            Visitor visitor = waitingLine.poll(); // Remove the front visitor from the queue
            rideHistory.add(visitor); // Add to ride history
            System.out.println("Visitor [" + visitor.getName() + "] has ridden [" + rideName + "]");
        }

        // Increment the number of cycles by 1
        numOfCycles++;
        System.out.println("[" + rideName + "] completed the " + numOfCycles + "th cycle");
    }

    // ------------------------------ Part 6: Export Ride History to File (CSV Format) ------------------------------
    /**
     * Exports ride history to a CSV file (one visitor record per line, separated by commas)
     * @param filePath File path (e.g.: src/ride_history.csv)
     */
    public void exportRideHistory(String filePath) {
        if (rideHistory.isEmpty()) {
            System.out.println("Export failed: The ride history of [" + rideName + "] is empty");
            return;
        }

        // Handle file writing exceptions (required by Part 6: Exception handling)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write file header (column names)
            writer.write("Name,Age,ID Card Number,Visitor Type,Has Fast Pass");
            writer.newLine(); // Line break

            // Write each visitor's information (CSV format)
            for (Visitor visitor : rideHistory) {
                String line = String.format("%s,%d,%s,%s,%s",
                        visitor.getName(),
                        visitor.getAge(),
                        visitor.getIdCard(),
                        visitor.getVisitorType(),
                        visitor.isHasFastPass() ? "Yes" : "No");
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Successfully exported the ride history of [" + rideName + "] to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Export failed: File writing error, reason: " + e.getMessage());
        }
    }

    // ------------------------------ Part 7: Import Ride History from File (CSV Format) ------------------------------
    /**
     * Imports ride history from a CSV file and adds it to rideHistory
     * @param filePath File path (e.g.: src/ride_history.csv)
     */
    public void importRideHistory(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Import failed: File does not exist, path: " + filePath);
            return;
        }

        // Handle file reading exceptions (required by Part 7: Exception handling)
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (lineNum == 1) {
                    continue; // Skip file header (column names)
                }

                // Split CSV line (separated by commas)
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("Skipping invalid line (line " + lineNum + "): Insufficient columns");
                    continue;
                }

                // Parse visitor information (handle format exceptions)
                try {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    String idCard = parts[2].trim();
                    String visitorType = parts[3].trim();
                    boolean hasFastPass = parts[4].trim().equals("Yes");

                    // Create visitor object and add to ride history
                    Visitor visitor = new Visitor(name, age, idCard, visitorType, hasFastPass);
                    rideHistory.add(visitor);
                    System.out.println("Successfully imported visitor: " + visitor.getName());
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line (line " + lineNum + "): Invalid age format");
                }
            }

            System.out.println("Import completed: A total of " + (rideHistory.size()) + " visitors imported from file [" + filePath + "] to the ride history of [" + rideName + "]");
        } catch (IOException e) {
            System.out.println("Import failed: File reading error, reason: " + e.getMessage());
        }
    }
}