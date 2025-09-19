# DIT400 — Course Management System (CMS)

## Assignment Overview
This project is the **Pre-OOP Assignment: Course Management System (Arrays + File I/O)** for **DIT400 — Object Oriented Programming (Semester 2, 2025)**.  
It is a **console-based application** that uses **arrays** and **file handling** to manage courses and users.  
The assignment reinforces programming fundamentals before introducing OOP concepts.


## Team Members
| Name               | Student ID   | Role                                      |
|--------------------|--------------|-------------------------------------------|
| Elizabeth Simasiku | 2410726      | Authentication & File I/O Lead            |
| Mike Mwansa        | 2410728     | Course CRUD & Array Management Lead        |
| Humphrey Nyirenda  | 2410425      | Search/Update & Java Lead                 |


## Project Structure

DIT400_CMS_GroupXX/
├── C++/
│   ├── main.cpp
│   ├── build.bat
│   └── cms_cpp.exe
├── Java/
│   ├── Main.java
│   ├── build.bat
│   └── cms_java.jar
├── docs/
│   ├── README.md
│   └── ai_prompts.md
└── audio/
    └── discussion.mp3


## Build & Run Instructions

**Windows (MinGW):**

g++ -std=c++11 -O2 -o cms_cpp.exe main.cpp
cms_cpp.exe
```

### Java Version
Compile and package into a runnable JAR:
```bash
javac Main.java
jar cfe cms_java.jar Main Main.class
java -jar cms_java.jar
```

## Test Credentials
Sample `users.txt`:
```
alice,alice123
bob,bob789
```

Sample `courses.txt`:
```
DIT101,Intro to IT,3
DIT205,Data Structures,4
```

---

## Known Limitations
- Passwords are stored in plaintext for simplicity.
- Maximum number of courses limited by array size (e.g., 1000).
- No advanced data structures allowed (no structs, classes, vectors, or maps in C++).


## Deliverables Checklist
- [x] `cms_cpp` / `cms_cpp.exe` + C++ source code  
- [x] `cms_java.jar` + Java source code  
- [x] `docs/README.md` with team details and build instructions  
- [x] `docs/ai_prompts.md` with documented AI usage  
- [x] `audio/discussion.mp3` (5-minute discussion by all members)  
- [x] GitHub repo named `DIT400_CMS_Group03`  
- [x] Submission via Google Form  

---

## Lessons Learned
- Using arrays for storage and persistence.  
- File handling for `users.txt` and `courses.txt`.  
- Translating C++ logic into Java.  
- Collaborating via GitHub with version control.  
- Documenting AI usage responsibly.  
