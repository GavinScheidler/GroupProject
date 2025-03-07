/*
FIO53-J Example: Use the serialization methods writeUnshared() and readUnshared() with care
Author: Dane Iwema
*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Person implements Serializable {
    private String name;

    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Student extends Person {
    private Professor tutor;

    Student(String name, Professor tutor) {
        super(name);
        this.tutor = tutor;
    }

    public Professor getTutor() {
        return tutor;
    }
}

class Professor extends Person {
    private List<Student> tutees = new ArrayList<>();

    Professor(String name) {
        super(name);
    }

    public List<Student> getTutees() {
        return tutees;
    }

    public boolean checkTutees() {
        boolean result = true;
        for (Student stu : tutees) {
            if (stu.getTutor() != this) {
                result = false;
                break;
            }
        }
        return result;
    }
}

public class FIO53 {
    public static void main(String[] args) {
        // Creating the professor and students
        Professor jane = new Professor("Jane");
        Student able = new Student("Able", jane);
        Student baker = new Student("Baker", jane);
        Student charlie = new Student("Charlie", jane);
        jane.getTutees().add(able);
        jane.getTutees().add(baker);
        jane.getTutees().add(charlie);

        // Print the result before serialization
        System.out.println("Before Serialization:");
        System.out.println("checkTutees returns: " + jane.checkTutees());

        // Serialize the objects
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("professor_students.ser"))) {
            oos.writeObject(jane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the objects
        Professor jane2 = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("professor_students.ser"))) {
            jane2 = (Professor) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Print the result after deserialization
        if (jane2 != null) {
            System.out.println("\nAfter Deserialization:");
            System.out.println("checkTutees returns: " + jane2.checkTutees());
        }
    }
}