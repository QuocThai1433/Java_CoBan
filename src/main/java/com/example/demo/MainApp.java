package com.example.demo;

import com.example.demo.entity.Classes;
import com.example.demo.dao.ClassesDao;
import com.example.demo.entity.Student;
import com.example.demo.dao.StudentDao;
import com.example.demo.utils.InputUtils;
import com.example.demo.utils.PrintUtils;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ENTER_ID = "Enter ID: ";
    private static final String STUDENT_NOT_FOUND_BY_ID = "Student not found by id = ";

    public static void main(String[] args) {
        while (true) {
            mainMenu();
            System.out.print("Enter your choose: ");
            int choose = InputUtils.inputNumber();
            switch (choose) {
                case 1:
                    listAllStudents();
                    break;
                case 2:
                    findStudent();
                    break;
                case 3:
                    listStudentLimit();
                    break;
                case 4:
                    addStudent();
                    break;
                case 5:
                    updateStudent();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    listAllClasses();
                    break;
                case 0:
                    System.out.println("Have a good day !!!");
                    return;
                default:
                    break;
            }
        }
    }

    private static void listAllClasses() {

    }

    public static void mainMenu() {
        System.out.println("\nWelcome to your school");
        System.out.println("1. List all students");
        System.out.println("2. Find student by id");
        System.out.println("3. List students by limit");
        System.out.println("4. Add student");
        System.out.println("5. Edit student");
        System.out.println("6. Delete Student");
        System.out.println("7. List all classes");
        System.out.println("Type \"0\" to exit");
    }

    private static void deleteStudent() {
        System.out.print(ENTER_ID);
        int id = InputUtils.inputNumber();
        Student student = StudentDao.getInstance().getById(id);
        if (student == null) {
            PrintUtils.errorMessage(STUDENT_NOT_FOUND_BY_ID + id);
            return;
        }
        if (StudentDao.getInstance().delete(id) == 0) {
            PrintUtils.errorMessage("Delete fail");
            return;
        }
        PrintUtils.infoMessage("Student \"" + student.getName() + "\" is deleted");
    }

    private static void updateStudent() {
        System.out.print(ENTER_ID);
        int id = InputUtils.inputNumber();
        Student student = StudentDao.getInstance().getById(id);
        if (student == null) {
            PrintUtils.infoMessage(STUDENT_NOT_FOUND_BY_ID + id);
            return;
        }
        PrintUtils.infoMessage("Student: " + student);
        String message = "Change %s to: ";
        boolean isExited = false;
        while (!isExited) {
            updateStudentMenu();
            System.out.print("Enter your choose: ");
            int choose = InputUtils.inputNumber();
            switch (choose) {
                case 1:
                    System.out.printf(message, student.getName());
                    student.setName(scanner.nextLine());
                    break;
                case 2:
                    System.out.printf(message, student.getAge().toString());
                    student.setAge(InputUtils.inputNumber());
                    break;
                case 3:
                    System.out.printf(message, student.getAddress());
                    student.setAddress(scanner.nextLine());
                    break;
                case 0:
                    PrintUtils.infoMessage("Cancel successfully");
                    isExited = true;
                    break;
                case 9:
                    int result = StudentDao.getInstance().update(id, student);
                    if (result == 0) {
                        PrintUtils.errorMessage("Update fail");
                    } else {
                        PrintUtils.infoMessage("Update successfully");
                    }
                    isExited = true;
                    break;
                default:
                    break;
            }
        }
    }

    private static void updateStudentMenu() {
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Address");
        System.out.println("Type \"9\" to save change and exit");
        System.out.println("Type \"0\" to cancel");
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = InputUtils.inputNumber();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        Student student = new Student(name, age, address);
        StudentDao.getInstance().create(student);
    }

    private static void listStudentLimit() {
        List<Student> students = StudentDao.getInstance().getListByLimit(20);
        students.forEach(item -> System.err.println(item.toString()));
    }

    private static void findStudent() {
        System.out.print(ENTER_ID);
        int id = InputUtils.inputNumber();
        Student student = StudentDao.getInstance().getById(id);
        if (student == null) {
            PrintUtils.errorMessage(STUDENT_NOT_FOUND_BY_ID + id);
            return;
        }
        PrintUtils.errorMessage(student.toString());
    }

    private static void listAllStudents() {
        List<Student> students = StudentDao.getInstance().getList();
        students.forEach(student -> PrintUtils.infoMessage(student.toString()));
    }

    /**
     * Classes management
     */
    private static Classes inputNewClasses() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        return new Classes(name);
    }

    private static void addClasses() {
        Classes classes = inputNewClasses();
        if (ClassesDao.getInstance().create(classes) == 0) {
            PrintUtils.errorMessage("Create class fail");
            return;
        }
        PrintUtils.infoMessage("Add Classes Successful");
    }

    private static void updateClasses() {
        System.out.print("Enter classId: ");
        int id = scanner.nextInt();
        Classes classes = ClassesDao.getInstance().getById(id);
        if (classes == null) {
            PrintUtils.errorMessage("Class not found");
            return;
        }
        boolean isCanceled = false;
        while (!isCanceled) {
            updateClassMenu();
            int choose = InputUtils.inputNumber();
            switch (choose) {
                case 1:
                    System.out.print("Enter name: ");
                    classes.setName(scanner.nextLine());
                    break;
                case 0:
                    isCanceled = true;
                    break;
                case 9:
                    if (ClassesDao.getInstance().update(classes) == 0) {
                        PrintUtils.errorMessage("Update fail");
                    } else {
                        PrintUtils.infoMessage("Update Successful");
                    }
                    isCanceled = true;
                    break;
                default:
                    break;
            }
        }
    }

    private static void updateClassMenu() {
        System.out.println("1. Name");
        System.out.println("Type \"9\" to save change and exit");
        System.out.println("Type \"0\" to cancel");
    }

    public static void deleteClassById() {
        System.out.print("Enter classId: ");
        int id = scanner.nextInt();
        Classes classes = ClassesDao.getInstance().getById(id);
        if (classes == null) {
            PrintUtils.errorMessage("Class not found");
            return;
        }
        if (ClassesDao.getInstance().delete(id) == 0) {
            PrintUtils.errorMessage("Class " + classes.getName() + " is deleted");
            return;
        }
        System.out.println("Delete Classes Successful");
    }

    public static void getAllCLasses() {
        List<Classes> classes = ClassesDao.getInstance().getList();
        System.out.println(classes.toString());
    }
}
