package advance.mapping.onetomanybi.instructor;

import advance.mapping.onetomanybi.instructor.dto.Course;
import advance.mapping.onetomanybi.instructor.dto.Instructor;
import advance.mapping.onetomanybi.instructor.dto.InstructorDetail;
import advance.mapping.onetomanybi.instructor.repository.CourseRepository;
import advance.mapping.onetomanybi.instructor.repository.InstructorDetailRepository;
import advance.mapping.onetomanybi.instructor.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        instructorRepository.setForeignKeyChecksToZero();
        instructorRepository.truncateTable();
        instructorDetailRepository.truncateTable();
        courseRepository.truncateTable();
        instructorRepository.setForeignKeyChecksToOne();

        System.out.println("\n======== Saving data into database.....");
        Instructor windah = new Instructor("windah", "basudara", "windahbasudara@gmail.com");
        InstructorDetail windahDetail = new InstructorDetail("windah basudara", "livestreaming");
        windahDetail.setInstructor(windah);
        windah.setInstructorDetail(windahDetail);
        Course javaCourse = new Course("Java course");
        Course golangCourse = new Course("Golang course");

        windah.addCourse(javaCourse);
        windah.addCourse(golangCourse);

        instructorRepository.save(windah);

        System.out.println("\n======== Get data from database.....");
        Instructor windahFromDB = instructorRepository.getReferenceById(1);

        System.out.println(windahFromDB);
        System.out.println(windahFromDB.getInstructorDetail());
        windahFromDB.getCourses().stream().forEach(course -> System.out.println(course));

        System.out.println("\n======== Delete data from database.....");
        windah.deleteCourse(golangCourse);
        golangCourse.setInstructor(null);
        courseRepository.deleteById(2);
//        courseRepository.deleteById(1);
//        windah.getCourses().stream().forEach(course -> System.out.println(course));
    }
}
