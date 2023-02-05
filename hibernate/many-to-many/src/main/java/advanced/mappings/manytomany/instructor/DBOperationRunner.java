package advanced.mappings.manytomany.instructor;

import advanced.mappings.manytomany.instructor.dto.Course;
import advanced.mappings.manytomany.instructor.dto.Student;
import advanced.mappings.manytomany.instructor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        instructorRepository.setForeignKeyChecksToZero();
        instructorRepository.truncateTable();
        instructorDetailRepository.truncateTable();
        courseRepository.truncateTable();
        studentRepository.truncateTable();
        reviewRepository.truncateTable();
        studentRepository.truncateTableCourseStudent();
        instructorRepository.setForeignKeyChecksToOne();

        System.out.println("\nSaving data into the database...");
        // Create new course
        Course javaCourse = new Course("Java complete bootcamp");

        // Create new students
        Student windah = new Student("Windah", "Basudara", "windahbasudara@gmail.com");
        Student john = new Student("john", "doe", "johndoe@gmail.com");

        // Add the students to course
        javaCourse.addStudents(windah, john);
//        windah.addCourses(javaCourse);
//        john.addCourses(javaCourse);

        // Save the data to the database
        courseRepository.save(javaCourse);
//        studentRepository.save(windah);
//        studentRepository.save(john);

        System.out.println("\nRetrieving data from the database...");
        Optional<Student> windahFromDB = studentRepository.findById(1);
        Optional<Course> javaFromDB = courseRepository.findById(1);

        if (javaFromDB.isPresent()){
            Course javaGet = javaFromDB.get();
            System.out.println(javaGet.getTitle());
            System.out.println(javaGet.getStudents());
        }

        if (windahFromDB.isPresent()){
            Student windahGet = windahFromDB.get();
            System.out.println(windahGet.getEmail());
            System.out.println(windahGet.getCourses());
        }

        // Create more course
        Course golang = new Course("Web development with Golang");

        // Save new students to new course
        // windah.addCourses(golang);
        // john.addCourses(golang);
        golang.addStudents(windah, john);

        // Save new course to the database
        // studentRepository.saveAll(List.of(windah, john));
        courseRepository.save(golang);

        System.out.println("\nDelete data from the database...");
        // courseRepository.delete(javaCourse);
        courseRepository.deleteById(1);

        // Can only be deleted if we don't have any truncate operations above
        studentRepository.delete(windah);

    }
}
