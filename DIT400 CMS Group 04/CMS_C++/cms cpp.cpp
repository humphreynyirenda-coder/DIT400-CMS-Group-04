#include <iostream>
#include <fstream>
#include <string>
using namespace std;
const int Course = 1000;
const int Users = 100;
string courseIds[Course], titles[Course];
int creditHours[Course], courseCount = 0;
string usernames[Users], passwords[Users];
int userCount = 0;

// Loads user data from users.txt and populates usernames and passwords arrays
void loadUsers() {
    ifstream file("users.txt"); string line;
    for (int i = 0; i < Users && getline(file, line); i++) {
        size_t pos = line.find(',');
        if (pos != string::npos) {
            usernames[userCount] = line.substr(0, pos);
            passwords[userCount] = line.substr(pos + 1);
            userCount++;
        }
    }
}
// Loads course data from courses.txt and populates course arrays
void loadCourses() {
    ifstream file("courses.txt"); string line;
    for (int i = 0; i < Course && getline(file, line); i++) {
        size_t pos1 = line.find(','); size_t pos2 = line.find(',', pos1 + 1);
        if (pos1 != string::npos && pos2 != string::npos) {
            courseIds[courseCount] = line.substr(0, pos1);
            titles[courseCount] = line.substr(pos1 + 1, pos2 - pos1 - 1);
            creditHours[courseCount] = stoi(line.substr(pos2 + 1));
            courseCount++;
        }
    }
}
// Saves all course data to courses.txt
void saveCourses() {
    ofstream file("courses.txt");
    for (int i = 0; i < courseCount; i++) {
        file << courseIds[i] << "," << titles[i] << "," << creditHours[i] << endl;
    }
}
// Saves all user data to users.txt
void saveUsers() {
    ofstream file("users.txt");
    for (int i = 0; i < userCount; i++) {
        file << usernames[i] << "," << passwords[i] << endl;
    }
}
// Checks if the provided username and password match any user
bool login(string user, string pass) {
    for (int i = 0; i < userCount; i++) {
        if (usernames[i] == user && passwords[i] == pass) return true;
    }
    return false;
}
// Registers a new user and saves to file
void registerUser(string user, string pass) {
    usernames[userCount] = user;
    passwords[userCount] = pass;
    userCount++;
    saveUsers();
}
// Finds the index of a course by its ID, returns -1 if not found
int findCourse(string id) {
    for (int i = 0; i < courseCount; i++) {
        if (courseIds[i] == id) return i;
    }
    return -1;
}
// Adds a new course to the arrays and saves to file
void addCourse() {
    string id, title;
    int credits;
    cout << "Course ID: "; cin.ignore(); getline(cin, id);
    if (findCourse(id) != -1) { cout << "ID exists!\n"; return; }
    cout << "Title: "; getline(cin, title);
    cout << "Credits (1-6): "; cin >> credits;
    if (credits < 1 || credits > 6) { cout << "Invalid credits!\n"; return; }
    
    courseIds[courseCount] = id;
    titles[courseCount] = title;
    creditHours[courseCount] = credits;
    courseCount++;
    saveCourses();
    cout << "Course added!\n";
}
// Deletes a course by its ID and saves changes to file
void deleteCourse() {
    string id;
    cout << "Course ID to delete: "; cin.ignore(); getline(cin, id);
    int idx = findCourse(id);
    if (idx == -1) { cout << "Not found!\n"; return; }
    for (int i = idx; i < courseCount - 1; i++) {
        courseIds[i] = courseIds[i + 1];
        titles[i] = titles[i + 1];
        creditHours[i] = creditHours[i + 1];
    }
    courseCount--;
    saveCourses();
    cout << "Course deleted!\n";
}
// Searches for a course by ID or title and prints results
void searchCourse() {
    string term;
    cout << "Search by ID or title: "; cin.ignore(); getline(cin, term);
    bool found = false;
    for (int i = 0; i < courseCount; i++) {
        if (courseIds[i] == term || titles[i].find(term) != string::npos) {
            cout << courseIds[i] << " - " << titles[i] << " (" << creditHours[i] << ")\n";
            found = true;
        }
    } if (!found) cout << "Not found!\n";
}
// Updates the title and credits of a course by its ID
void updateCourse() {
    string id, newTitle;
    int newCredits;
    cout << "Course ID: "; cin.ignore(); getline(cin, id);
    int idx = findCourse(id);
    if (idx == -1) { cout << "Not found!\n"; return; }  
    cout << "New title: "; getline(cin, newTitle);
    cout << "New credits (1-6): "; cin >> newCredits;
    if (newCredits < 1 || newCredits > 6) { cout << "Invalid credits!\n"; return; }  
    titles[idx] = newTitle;
    creditHours[idx] = newCredits;
    saveCourses();
    cout << "Course updated!\n";
}
// Lists all courses with their details
void listCourses() {
    for (int i = 0; i < courseCount; i++) {
        cout << courseIds[i] << " - " << titles[i] << " (" << creditHours[i] << ")\n";
    }
}
// Main function: handles user login/register and menu operations
int main() {
    loadUsers();
    loadCourses();
    
    string user, password;
    int choice;
    cout << "1.Login 2.Register: "; cin >> choice;
    cout << "Username: "; cin >> user;
    cout << "Password: "; cin >> password;    
    if (choice == 1) {
        if (!login(user, password)) { cout << "Invalid!\n"; return 1; }
    } else {
        registerUser(user, password);
    }
    
    while (true) {
        cout << "\n1.Add 2.Delete 3.Search 4.Update 5.List 6.Exit: ";
        cin >> choice;
        
        switch (choice) {
            case 1: addCourse(); break;
            case 2: deleteCourse(); break;
            case 3: searchCourse(); break;
            case 4: updateCourse(); break;
            case 5: listCourses(); break;
            case 6: return 0;
            default: cout << "Invalid!\n";
        }
    }
}