/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

/**
 * Author: Bro code
 * Title: Java Full Course for free
 * Available at: https://www.youtube.com/watch?v=xTtL8E4LzTQ
 * Date accessed: 09/09/2026
 */ 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalManagementTest {
    private HospitalManagement system;

    @BeforeEach
    public void setUp() {
        system = new HospitalManagement();
    }

    // --- CRUD Tests ---
    @Test
    public void testRegisterPatient() {
        Patient p = new Patient("P001", "John", "Doe", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        assertTrue(system.registerPatient(p));
        assertEquals(1, system.getTotalRegisteredPatients());
    }

    @Test
    public void testRegisterDuplicatePatient() {
        Patient p1 = new Patient("P001", "John", "Doe", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        Patient p2 = new Patient("P001", "Jane", "Doe", 25, "Female", "Fever", PatientCategory.EMERGENCY);
        system.registerPatient(p1);
        assertFalse(system.registerPatient(p2));
    }

    @Test
    public void testSearchPatient() {
        Patient p = new Patient("P002", "Alice", "Smith", 45, "Female", "Asthma", PatientCategory.OUTPATIENT);
        system.registerPatient(p);
        assertNotNull(system.searchPatient("P002"));
        assertNull(system.searchPatient("P999"));
    }

    @Test
    public void testUpdatePatient() {
        Patient p = new Patient("P003", "Bob", "Marley", 40, "Male", "Cough", PatientCategory.OUTPATIENT);
        system.registerPatient(p);
        assertTrue(system.updatePatient("P003", "Robert", "Marley", 41, "Male", "Recovered"));
        Patient updatedPatient = system.searchPatient("P003");
        assertEquals("Robert", updatedPatient.getFirstName());
        assertEquals(41, updatedPatient.getAge());
    }

    @Test
    public void testDeletePatient() {
        Patient p = new Patient("P004", "Charlie", "Brown", 50, "Male", "Fracture", PatientCategory.OUTPATIENT);
        system.registerPatient(p);
        assertTrue(system.deletePatient("P004"));
        assertNull(system.searchPatient("P004"));
    }

    // --- Bed Management Tests ---
    @Test
    public void testAllocateBed() {
        Inpatient inp = new Inpatient("P005", "David", "Miller", 35, "Male", "Surgery", null, null);
        system.registerPatient(inp);
        assertTrue(system.allocateBed("P005", "B01"));
        assertEquals(19, system.getAvailableBedsCount());
    }

    @Test
    public void testAllocateOccupiedBed() {
        Inpatient inp1 = new Inpatient("P006", "Emma", "Watson", 28, "Female", "Observation", null, null);
        Inpatient inp2 = new Inpatient("P007", "Frank", "Wright", 60, "Male", "Cardiac", null, null);
        system.registerPatient(inp1);
        system.registerPatient(inp2);
        system.allocateBed("P006", "B01");
        assertFalse(system.allocateBed("P007", "B01"));
    }

    @Test
    public void testReleaseBed() {
        Inpatient inp = new Inpatient("P006", "Grace", "Hopper", 70, "Female", "Pneumonia", null, null);
        system.registerPatient(inp);
        assertTrue(system.allocateBed("P006", "B02")); // Allocate bed first
        assertTrue(system.releaseBed("B02")); // Now release it
        assertEquals(20, system.getAvailableBedsCount());
    }
    @Test
    public void testReleaseUnoccupiedBed() {
        assertFalse(system.releaseBed("B99")); // Non-existent bed
    }

    // --- Ward Layout Tests ---
    @Test
    public void testDisplayWardLayout() {
        String layout = system.displayWardLayout();
        assertNotNull(layout);
        assertTrue(layout.contains("B01"));
    }

    // --- Sorting Tests ---
    @Test
    public void testSortPatientsById() {
        Patient p1 = new Patient("P003", "Zack", "Zulu", 20, "Male", "Cold", PatientCategory.OUTPATIENT);
        Patient p2 = new Patient("P001", "Adam", "Apple", 22, "Male", "Cold", PatientCategory.OUTPATIENT);
        system.registerPatient(p1);
        system.registerPatient(p2);
        system.sortPatientsById();
        assertEquals("P001", system.getPatients().get(0).getPatientId());
    }

    @Test
    public void testSortPatientsByName() {
        Patient p1 = new Patient("P003", "Zack", "Zulu", 20, "Male", "Cold", PatientCategory.OUTPATIENT);
        Patient p2 = new Patient("P001", "Adam", "Apple", 22, "Male", "Cold", PatientCategory.OUTPATIENT);
        system.registerPatient(p1);
        system.registerPatient(p2);
        system.sortPatientsByName();
        assertEquals("Apple", system.getPatients().get(0).getLastName());
    }

    // --- Occupancy Tests ---
    @Test
    public void testGetOccupiedBedsCount() {
        Inpatient inp = new Inpatient("P009", "Hank", "Hill", 55, "Male", "Checkup", null, null);
        system.registerPatient(inp);
        system.allocateBed("P009", "B03");
        assertEquals(1, system.getOccupiedBedsCount());
    }

    @Test
    public void testGetAvailableBedsCount() {
        assertEquals(20, system.getAvailableBedsCount());
    }

    @Test
    public void testGetOccupancyPercentage() {
        Inpatient inp = new Inpatient("P010", "Ivy", "League", 40, "Female", "Checkup", null, null);
        system.registerPatient(inp);
        system.allocateBed("P010", "B04");
        assertEquals(5.0, system.getOccupancyPercentage(), 0.01);
    }

    // --- Edge Cases ---
    @Test
    public void testFullWard() {
        for (int i = 1; i <= 20; i++) {
            String id = String.format("P%03d", i);
            String bed = String.format("B%02d", i);
            Inpatient inp = new Inpatient(id, "Test", "Patient", 30, "Other", "Stable", null, null);
            system.registerPatient(inp);
            system.allocateBed(id, bed);
        }
        assertEquals(0, system.getAvailableBedsCount());
        Inpatient extra = new Inpatient("P021", "Extra", "Patient", 30, "Other", "Stable", null, null);
        system.registerPatient(extra);
        assertFalse(system.allocateBed("P021", "B01"));
    }
}