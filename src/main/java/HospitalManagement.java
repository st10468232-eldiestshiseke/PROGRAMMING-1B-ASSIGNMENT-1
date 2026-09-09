/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HospitalManagement {
    private ArrayList<Patient> patients = new ArrayList<>();
    private String[][] wardBeds = new String[4][5]; // 4 rows x 5 colums =20 beds
    
    private HospitalManagement() {
        initializeWard();
    }
    private void initializeWard(){
        int bedCount = 1;
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 5; c++){
            wardBeds[r][c] = String.format("B%02d", bedCount++);
            }
        }
        
    }
    public boolean registerPatient(Patient patient) {
        if (patient == null || patient.getPatientId() == null || patient.getPatientId().trim().isEmpty()){
        return false;
        }
        for(Patient p : patients) {
           if (p.getPatientId().equalsIgnoreCase(patient.getPatientId())){
            return false;
           }
        }
        patients.add(patient);
        return true;
    }
    public Patient searchPatient(String patientId){
        for (Patient p : patients) {
            if (p.getPatientId().equalsIgnoreCase(patientId)){
                return p;
            }
        }
        return null;
    }
    public boolean updatePatient(String patientId,String firstName, String lastName, int age, String gender, String condition){
        Patient p = searchPatient(patientId);
        if (p != null) {
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setAge(age);
            p.setGender(gender);
            p.setMedicalCondition(condition);
            return true;
        }
        return false;
    }
    public boolean deletePatient(String patientId) {
        Patient p = searchPatient(patientId);
        if (p != null) {
            if (p instanceof Inpatient inp && inp.getBedNumber() != null) {
            releaseBed(inp.getBedNumber());
            }
            patients.remove(p);
            return true;
        }
        return false;
    }
    public boolean allocatedBed(String patientId, String bedCode) {
        if (getAvailableBedCount() == 0) return false;
        Patient p = searchPatient(patientId);
        if (p == null || !(p instanceof Inpatient inpatient)) return false;
        if (inpatient.getBedNumber() != null) return false;
        
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 5; c++) {
                if (wardBeds[r][c].equalsIgnoreCase(bedCode)) {
                    wardBeds[r][c] = "OCCUPIED";
                    inpatient.setBedNumber(bedCode.toUpperCase());
                    inpatient.setWardNumber("ward 1");
                    return true;
                }
            }
        }
        return false; // Bed unavailable or non-existant
    
    }
    public boolean releaseBed (String bedCode) {
        boolean bedFound = false;
        int bedNum = 1;
        
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c <5; c++) {
                String targetBed = String.format("B%02d", bedNum++);
                if (targetBed.equalsIgnoreCase(bedCode) && wardBeds[r][c].equals("OCCUPIED")) {
                    wardBeds[r][c] =targetBed;
                    bedFound = true;
                    break;
                }
            }
        }
        
    }
}
