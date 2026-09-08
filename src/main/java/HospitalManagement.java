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
        for(int c = 0; c < 5; c++) {
            wardBeds[r][c] = String.format("B%02d", bedCount++);
        }
    }
    
}
