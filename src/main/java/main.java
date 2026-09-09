/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.Scanner;

public class main {
    private static HospitalManagement system = new HospitalManagement(); // Assuming a singleton pattern
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n==================================================");
                System.out.println("     MEDICARE HOSPITAL MANAGEMENT SYSTEM          ");
                System.out.println("==================================================");
                System.out.println("1. Register Patient");
                System.out.println("2. Search Patient");
                System.out.println("3. Update Patient");
                System.out.println("4. Delete Patient");
                System.out.println("5. Allocate Bed");
                System.out.println("6. Release Bed");
                System.out.println("7. Display Ward Layout");
                System.out.println("8. Generate Reports & Views");
                System.out.println("9. Exit");
                System.out.print("Select an option (1-9): ");

                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> register();
                    case 2 -> search();
                    case 3 -> update();
                    case 4 -> delete();
                    case 5 -> allocate();
                    case 6 -> release();
                    case 7 -> System.out.println("\nWARD LAYOUT:\n" + system.displayWardLayout());
                    case 8 -> reportsMenu();
                    case 9 -> {
                        System.out.println("Exiting System. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid selection. Choose 1-9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input error: Please enter a valid menu number.");
            }
        }
    }

    private static void register() {
        System.out.print("Patient ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("First Name: ");
        String fn = scanner.nextLine().trim();
        System.out.print("Last Name: ");
        String ln = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Gender: ");
        String gender = scanner.nextLine().trim();
        System.out.print("Medical Condition: ");
        String cond = scanner.nextLine().trim();
        System.out.println("Category: 1. Inpatient | 2. Outpatient | 3. Emergency");
        int cat = Integer.parseInt(scanner.nextLine().trim());

        Patient p = switch (cat) {
            case 1 -> new Inpatient(id, fn, ln, age, gender, cond, null, null);
            case 2 -> new Patient(id, fn, ln, age, gender, cond, PatientCategory.OUTPATIENT);
            case 3 -> new Patient(id, fn, ln, age, gender, cond, PatientCategory.EMERGENCY);
            default -> null;
        };

        if (p != null && system.registerPatient(p)) {
            System.out.println("--> Patient registered successfully.");
        } else {
            System.out.println("--> Error: Duplicate ID or invalid category selection.");
        }
    }

    private static void search() {
        System.out.print("Enter Patient ID to search: ");
        Patient p = system.searchPatient(scanner.nextLine().trim());
        System.out.println(p != null ? p.displayDetails() : "--> Patient not found.");
    }

    private static void update() {
        System.out.print("Enter Patient ID to update: ");
        String id = scanner.nextLine().trim();
        System.out.print("New First Name: ");
        String fn = scanner.nextLine().trim();
        System.out.print("New Last Name: ");
        String ln = scanner.nextLine().trim();
        System.out.print("New Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("New Gender: ");
        String gender = scanner.nextLine().trim();
        System.out.print("New Medical Condition: ");
        String cond = scanner.nextLine().trim();

        if (system.updatePatient(id, fn, ln, age, gender, cond)) {
            System.out.println("--> Patient details updated.");
        } else {
            System.out.println("--> Patient not found.");
        }
    }

    private static void delete() {
        System.out.print("Enter Patient ID to delete: ");
        if (system.deletePatient(scanner.nextLine().trim())) {
            System.out.println("--> Patient deleted.");
        } else {
            System.out.println("--> Patient not found.");
        }
    }

    private static void allocate() {
        System.out.print("Inpatient ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Bed Code (e.g., B01): ");
        String bed = scanner.nextLine().trim();

        if (system.allocateBed(id, bed)) {
            System.out.println("--> Bed allocated successfully.");
        } else {
            System.out.println("--> Allocation failed: Invalid bed, bed occupied, or patient not an inpatient.");
        }
    }

    private static void release() {
        System.out.print("Enter Bed Code to release (e.g., B01): ");
        if (system.releaseBed(scanner.nextLine().trim())) {
            System.out.println("--> Bed released.");
        } else {
            System.out.println("--> Release failed: Bed is not occupied or invalid.");
        }
    }

    private static void reportsMenu() {
        System.out.println("\n--- REPORTS & VIEWS ---");
        System.out.println("1. View Patient List (Sorted by ID)");
        System.out.println("2. View Patient List (Sorted by Last Name)");
        System.out.println("3. Ward Occupancy Summary");
        System.out.print("Selection: ");
        int sel = Integer.parseInt(scanner.nextLine().trim());

        if (sel == 1) {
            system.sortPatientsById();
            System.out.println("\nPATIENTS (SORTED BY ID):");
            system.getPatients().forEach(p -> System.out.println(p.displayDetails()));
        } else if (sel == 2) {
            system.sortPatientsByName(); // Fixed method name
            System.out.println("\nPATIENTS (SORTED BY LAST NAME):");
            system.getPatients().forEach(p -> System.out.println(p.displayDetails()));
        } else if (sel == 3) {
            System.out.println("\nWARD OCCUPANCY REPORT:");
            System.out.println("Total Registered Patients : " + system.getTotalRegisteredPatients());
            System.out.println("Available Beds            : " + system.getAvailableBedsCount());
            System.out.println("Occupied Beds             : " + system.getOccupiedBedsCount());
            System.out.printf("Ward Occupancy Rate       : %.2f%%\n", system.getOccupancyPercentage());
        }
    }
}