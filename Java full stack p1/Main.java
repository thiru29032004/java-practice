import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Details> studentList = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nEnter details for student " + i + ":");

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Roll No: ");
            int rno = sc.nextInt();
            sc.nextLine();

            System.out.print("Department: ");
            String dept = sc.nextLine();

            System.out.print("Transport Mode: ");
            String transport = sc.nextLine();

            System.out.print("Gender: ");
            String gender = sc.nextLine();

            System.out.print("Mobile Number: ");
            String mobile = sc.nextLine();

            System.out.print("Email ID: ");
            String email = sc.nextLine();

            Details student = new Details(name, rno, dept, transport, gender, mobile, email);
            studentList.add(student);
        }

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Display All Students");
            System.out.println("2. Display CSE Students");
            System.out.println("3. Count by Transport Mode");
            System.out.println("4. Display Students by Gender");
            System.out.println("5. Search Student by Roll Number");
            System.out.println("6. List Students by Department");
            System.out.println("7. Department-Wise Count");
            System.out.println("8. Display All Emails");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                for (Details student : studentList) {
                    System.out.println("\n" + student);
                }
            } else if (choice == 2) {
                for (Details student : studentList) {
                    if (student.dept.equalsIgnoreCase("CSE")) {
                        System.out.println(student.name + " - " + student.rno);
                    }
                }
            } else if (choice == 3) {
                HashMap<String, Integer> transportCount = new HashMap<>();
                for (Details student : studentList) {
                    transportCount.put(student.transport, transportCount.getOrDefault(student.transport, 0) + 1);
                }
                for (String transport : transportCount.keySet()) {
                    System.out.println(transport + " : " + transportCount.get(transport));
                }
            } else if (choice == 4) {
                System.out.println("Male:");
                for (Details student : studentList) {
                    if (student.gender.equalsIgnoreCase("Male")) {
                        System.out.println(student.name);
                    }
                }
                System.out.println("Female:");
                for (Details student : studentList) {
                    if (student.gender.equalsIgnoreCase("Female")) {
                        System.out.println(student.name);
                    }
                }
            } else if (choice == 5) {
                System.out.print("Enter Roll Number to search: ");
                int rollSearch = sc.nextInt();
                sc.nextLine();
                boolean found = false;
                for (Details student : studentList) {
                    if (student.rno == rollSearch) {
                        System.out.println(student);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Student not found.");
                }
            } else if (choice == 6) {
                System.out.print("Enter Department: ");
                String dname = sc.nextLine();
                for (Details student : studentList) {
                    if (student.dept.equalsIgnoreCase(dname)) {
                        System.out.println(student.name + " - " + student.rno);
                    }
                }
            } else if (choice == 7) {
                HashMap<String, Integer> deptCount = new HashMap<>();
                for (Details student : studentList) {
                    deptCount.put(student.dept, deptCount.getOrDefault(student.dept, 0) + 1);
                }
                for (String dept : deptCount.keySet()) {
                    System.out.println(dept + " : " + deptCount.get(dept));
                }
            } else if (choice == 8) {
                for (Details student : studentList) {
                    System.out.println(student.email);
                }
            } else if (choice == 9) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}

class Details {
    String name;
    int rno;
    String dept;
    String transport;
    String gender;
    String mobile;
    String email;

    Details(String name, int rno, String dept, String transport, String gender, String mobile, String email) {
        this.name = name;
        this.rno = rno;
        this.dept = dept;
        this.transport = transport;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
    }

    public String toString() {
        return "Name      : " + name +
               "\nRoll No   : " + rno +
               "\nDept      : " + dept +
               "\nTransport : " + transport +
               "\nGender    : " + gender +
               "\nMobile    : " + mobile +
               "\nEmail     : " + email;
    }
}
