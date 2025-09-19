import java.io.*;
import java.util.*;
class CourseManagement {
    static final int COURSE = 1000;
    static final int USERS = 100;

    static String[] courseIds = new String[COURSE];
    static String[] titles = new String[COURSE];
    static int[] creditHours = new int[COURSE];
    static int courseCount = 0;

    static String[] usernames = new String[USERS];
    static String[] passwords = new String[USERS];
    static int userCount = 0;

    // Loads user data from users.txt
    static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null && userCount < USERS) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    usernames[userCount] = parts[0];
                    passwords[userCount] = parts[1];
                    userCount++;
                } }
        } catch (IOException e) {
            // If file not found, start empty
        }
    }

    // Loads course data from courses.txt
    static void loadCourses() {
        try (BufferedReader br = new BufferedReader(new FileReader("courses.txt"))) {
            String line;
            while ((line = br.readLine()) != null && courseCount < COURSE) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    courseIds[courseCount] = parts[0];
                    titles[courseCount] = parts[1];
                    creditHours[courseCount] = Integer.parseInt(parts[2]);
                    courseCount++;
                }
            }
        } catch (IOException e) {
            // If file not found, start empty
        }
    }

    // Saves all user data to users.txt
    static void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt"))) {
            for (int i = 0; i < userCount; i++) {
                pw.println(usernames[i] + "," + passwords[i]);
            }
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    // Saves all course data to courses.txt
    static void saveCourses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("courses.txt"))) {
            for (int i = 0; i < courseCount; i++) {
                pw.println(courseIds[i] + "," + titles[i] + "," + creditHours[i]);
            }
        } catch (IOException e) {
            System.out.println("Error saving courses.");
        }
    }

    // Checks if username/password match
    static boolean login(String user, String pass) {
        for (int i = 0; i < userCount; i++) {
            if (usernames[i].equals(user) && passwords[i].equals(pass)) {
                return true;
            }
        }
        return false;
    }

    // Registers new user
    static void registerUser(String user, String pass) {
        usernames[userCount] = user;
        passwords[userCount] = pass;
        userCount++;
        saveUsers();
    }

    // Find course index
    static int findCourse(String id) {
        for (int i = 0; i < courseCount; i++) {
            if (courseIds[i].equals(id)) return i;
        }
        return -1;
    }
    // Add new course
    static void addCourse(Scanner sc) {
        System.out.print("Course ID: ");
        sc.nextLine(); // clear buffer
        String id = sc.nextLine();
        if (findCourse(id) != -1) {
            System.out.println("ID exists!");
            return;
        }
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Credits (1-6): ");
        int credits = sc.nextInt();
        if (credits < 1 || credits > 6) {
            System.out.println("Invalid credits!");
            return;
        }
        courseIds[courseCount] = id;
        titles[courseCount] = title;
        creditHours[courseCount] = credits;
        courseCount++;
        saveCourses();
        System.out.println("Course added!");
    }

    // Delete course
    static void deleteCourse(Scanner sc) {
        System.out.print("Course ID to delete: ");
        sc.nextLine();
        String id = sc.nextLine();
        int idx = findCourse(id);
        if (idx == -1) {
            System.out.println("Not found!");
            return;
        }
        for (int i = idx; i < courseCount - 1; i++) {
            courseIds[i] = courseIds[i + 1];
            titles[i] = titles[i + 1];
            creditHours[i] = creditHours[i + 1];
        }
        courseCount--;
        saveCourses();
        System.out.println("Course deleted!");
    }

    // Search course
    static void searchCourse(Scanner sc) {
        System.out.print("Search by ID or title: ");
        sc.nextLine();
        String term = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < courseCount; i++) {
            if (courseIds[i].equals(term) || titles[i].contains(term)) {
                System.out.println(courseIds[i] + " - " + titles[i] + " (" + creditHours[i] + ")");
                found = true;
            }
        }
        if (!found) System.out.println("Not found!");
    }

    // Update course
    static void updateCourse(Scanner sc) {
        System.out.print("Course ID: ");
        sc.nextLine();
        String id = sc.nextLine();
        int idx = findCourse(id);
        if (idx == -1) {
            System.out.println("Not found!");
            return;
        }
        System.out.print("New title: ");
        String newTitle = sc.nextLine();
        System.out.print("New credits (1-6): ");
        int newCredits = sc.nextInt();
        if (newCredits < 1 || newCredits > 6) {
            System.out.println("Invalid credits!");
            return;
        }
        titles[idx] = newTitle;
        creditHours[idx] = newCredits;
        saveCourses();
        System.out.println("Course updated!");
    }

    // List courses
    static void listCourses() {
        for (int i = 0; i < courseCount; i++) {
            System.out.println(courseIds[i] + " - " + titles[i] + " (" + creditHours[i] + ")");
        }
    }

    // Main
    public static void main(String[] args) {
        loadUsers();
        loadCourses();
        Scanner sc = new Scanner(System.in);

        System.out.print("1.Login 2.Register: ");
        int choice = sc.nextInt();
        System.out.print("Username: ");
        String user = sc.next();
        System.out.print("Password: ");
        String password = sc.next();

        if (choice == 1) {
            if (!login(user, password)) {
                System.out.println("Invalid!");
                return;
            }
        } else {
            registerUser(user, password);
        }

        while (true) {
            System.out.print("\n1.Add 2.Delete 3.Search 4.Update 5.List 6.Exit: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: addCourse(sc); break;
                case 2: deleteCourse(sc); break;
                case 3: searchCourse(sc); break;
                case 4: updateCourse(sc); break;
                case 5: listCourses(); break;
                case 6: return;
                default: System.out.println("Invalid!");
            }
        }
    }
}
