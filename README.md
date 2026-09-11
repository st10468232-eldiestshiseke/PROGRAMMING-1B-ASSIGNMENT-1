# **Medicare Hospital Patient Admission System**


## **PROG6112 Practical Assignment 1 — Comprehensive Documentation & Setup Guide**
---
### **1. System Overview**
---
MediCare Hospital is transitioning from a traditional paper-based admission process to an automated, console-based Hospital Patient Admission System for its medical ward. This Java-based software solution replaces manual tracking of patient details, bed allocations, and ward management to minimize misplaced records and streamline ward administration.
### **2. Core Features & Capabilities**
---
•	**Feature 1** - Patient Management: Full CRUD (Create, Read, Update, Delete) capability. Allows staff to register patients, search by unique ID, update existing record details, and remove records safely.
•	**Feature 2** - Ward Bed Management: Manages a 20-bed hospital ward modeled using a 2D array (4 rows × 5 columns). Supports allocating available beds (B01–B20) to Inpatients and releasing occupied beds upon discharge.
•	**Feature 3** - Reports & Data Views: Generates real-time ward occupancy reports (Total Registered, Available Beds, Occupied Beds, Occupancy Rate %). Provides multi-criteria patient listing sorted by Patient ID or Last Name.
•	**Feature 4** - Object-Oriented Architecture: Implements core OOP principles including encapsulation, inheritance (Inpatient subclass extending base Patient class), method overriding (displayDetails), and enum categorization.
•	**Feature 5** - Automated Unit Testing: Comprehensive JUnit 5 test suite covering CRUD operations, bed allocations, boundary conditions, and full-ward constraints.

### **3. System Architecture & Class Design**
---
The application follows a modular, object-oriented design structure:
•	**PatientCategory (Enum):** Defines allowable patient categories: INPATIENT, OUTPATIENT, and EMERGENCY.
•	**Patient (Base Class):** Encapsulates general patient metadata (Patient ID, First Name, Last Name, Age, Gender, Medical Condition, Category) with getters, setters, and comparison capability.
•	**Inpatient (Subclass):** Extends Patient class with additional hospital ward attributes (Ward Number, Bed Number) and overrides displayDetails().
•	**HospitalManagement (Logic Controller):** Manages internal data collections (ArrayList<Patient> and 2D bed layout matrix), encapsulating business logic, allocation validation, and sorting functions.
•	**Main (Console Interface):** Provides a robust, menu-driven CLI with input validation and exception handling for user interactions.
•	**HospitalManagementTest (Unit Tests):** Contains JUnit 5 test cases to systematically verify all system constraints.

### **4. Hospital Ward Layout Matrix**
---
The ward consists of exactly 20 beds structured in a 4 x 5 grid:

Row 1: [ B01 ] [ B02 ] [ B03 ] [ B04 ] [ B05 ]
---
Row 2: [ B06 ] [ B07 ] [ B08 ] [ B09 ] [ B10 ]
---
Row 3: [ B11 ] [ B12 ] [ B13 ] [ B14 ] [ B15 ]
---
Row 4: [ B16 ] [ B17 ] [ B18 ] [ B19 ] [ B20 ]
---

### **5. YOUTUBE LINK**
https://youtu.be/WfKKKjyOPi0

### **6. REFERENCE**

**Author:** _Bro code_

**Title:** _Java Full Course for free_

**Available at:** https://www.youtube.com/watch?v=xTtL8E4LzTQ

**Date accessed:** _09/09/2026_
 
