import java.util.Random;

// Base class representing a general Vehicle
class Vehicle {
    String vehicleId;
    double fuelLevel;

    // Constructor
    public Vehicle(String vehicleId, double fuelLevel) {
        this.vehicleId = vehicleId;
        this.fuelLevel = fuelLevel;
    }

    // Method to simulate vehicle movement (can be overridden)
    public void move() {
        // Default move behavior (empty for simplicity)
    }
}

// Subclass that extends Vehicle and adds tracking functionality with threading
class VehicleTracker extends Vehicle implements Runnable {
    // Constructor
    public VehicleTracker(String vehicleId, double fuelLevel) {
        super(vehicleId, fuelLevel);
    }

    // Override the move method to simulate movement
    @Override
    public void move() {
        Random rand = new Random();
        double distance = 1.0 + rand.nextDouble(); // km
        fuelLevel -= distance * 0.5; // fuel used per km

        // Ensure fuel level doesn't go below 0
        if (fuelLevel < 0) fuelLevel = 0;

        // Print the vehicle status
        System.out.printf("Distance covered: %.2f km\n", distance);
        System.out.printf("Fuel level: %.2f%%\n", fuelLevel);
    }

    // Implement the run method for threading
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            move(); // Simulate vehicle movement
            try {
                Thread.sleep(1000); // Simulate time delay (1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Tracking ended.");
    }
}

// Main class to execute the code
public class Main {
    public static void main(String[] args) {
        String vehicleId = "MH12AB1234";
        double fuelLevel = 100.0; // In percentage

        // Create VehicleTracker object
        VehicleTracker vehicleTracker = new VehicleTracker(vehicleId, fuelLevel);

        // Create and start a new thread for vehicle tracking
        Thread trackingThread = new Thread(vehicleTracker);
        System.out.println("Starting Vehicle Tracking...");
        trackingThread.start(); // Begin the vehicle tracking in a separate thread
    }
}