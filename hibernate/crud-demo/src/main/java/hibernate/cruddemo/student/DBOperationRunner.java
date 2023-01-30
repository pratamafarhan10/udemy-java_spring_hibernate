package hibernate.cruddemo.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hibernate.cruddemo.student.dto.DateUtils;
import hibernate.cruddemo.student.dto.Student;

@Component
public class DBOperationRunner implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        studentRepository.truncateTable();
        System.out.println("Saving data into database...");

        Student tempStudent = new Student("chris", "evans", "chrisevans@gmail.com", DateUtils.parseDate("31/12/2000"));

        System.out.println(tempStudent.getId());

        studentRepository.saveAll(Arrays.asList(
            new Student("paull", "wall", "paul@gmail.com", DateUtils.parseDate("20/05/2000")),
            new Student("john", "doe", "johndoe@gmail.com", DateUtils.parseDate("12/01/2000")),
            new Student("mark", "robert", "markrobert@gmail.com", DateUtils.parseDate("13/11/2000")),
            new Student("levi", "ark", "leviark@gmail.com", DateUtils.parseDate("05/03/2000")),
            new Student("windah", "basudara", "windahbasudara@gmail.com", DateUtils.parseDate("09/08/2000"))
        ));

        System.out.println("Done!");

        System.out.println("\nGet all data...");

        List<Student> students = new ArrayList<>();

        students = studentRepository.findAll();

        students.forEach(student -> System.out.println(student.getFirstName() + " - " + student.getLastName() + " - " + student.getEmail()));

        System.out.println("\nGet data with last name doe...");

        List<Student> doeStudents = new ArrayList<>();

        doeStudents = studentRepository.findStudentWithLastName("doe");

        doeStudents.forEach(student -> System.out.println(student.getId() + " - " + student.getFirstName() + " - " + student.getLastName() + " - " + student.getEmail()));

        System.out.println("\nUpdate student data based on id...");
        
        studentRepository.update(new Student("scooby", "wall", "scooby@gmail.com", DateUtils.parseDate("20/05/2000")), 1);

        System.out.println("\nDelete student data based on id...");
        studentRepository.deleteById(2);
    }
}
