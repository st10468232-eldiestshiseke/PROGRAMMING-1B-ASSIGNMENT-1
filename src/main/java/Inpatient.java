/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Author: Bro code
 * Title: Java Full Course for free
 * Available at: https://www.youtube.com/watch?v=xTtL8E4LzTQ
 * Date accessed: 09/09/2026
 */ 
public class Inpatient extends Patient {
    private String wardNumber;
    private String bedNumber;
    
    public Inpatient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition, String wardNumber, String bedNumber){
        super(patientId, firstName, lastName, age, gender, medicalCondition, PatientCategory.INPATIENT);
        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
    }
    public String getWardNumber() { return wardNumber;}
    public void setWardNumber(String wardNumber) {this.wardNumber = wardNumber;}
    public String getBedNumber() {return bedNumber;}
    public void setBedNumber(String bedNumber) {this.bedNumber = bedNumber;}
    
    @Override
    public String displayDetails() {
        String bedInfo = (bedNumber != null) ? bedNumber : "Unassigned";
        String wardInfo = (wardNumber != null) ? wardNumber : "Unassigned";
        return super.displayDetails() + String.format(" | Ward: %-6s | Bed: %-5s", wardInfo, bedInfo);
    }
    
}
