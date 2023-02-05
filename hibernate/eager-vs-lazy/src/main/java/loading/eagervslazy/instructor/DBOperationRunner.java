package loading.eagervslazy.instructor;

import loading.eagervslazy.instructor.dto.Course;
import loading.eagervslazy.instructor.dto.Instructor;
import loading.eagervslazy.instructor.dto.InstructorDetail;
import loading.eagervslazy.instructor.repository.CourseRepository;
import loading.eagervslazy.instructor.repository.InstructorDetailRepository;
import loading.eagervslazy.instructor.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

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

        // Populate the database
        System.out.println("\nPopulating the database....");
        Instructor windah = new Instructor("Windah", "Basudara", "windahbasudara@gmail.com");
        InstructorDetail windahDetail = new InstructorDetail("Windah Basudara", "Livestreaming");
        Course java = new Course("Java");
        Course golang = new Course("Golang");
        Course php = new Course("PHP");

        windah.setInstructorDetail(windahDetail);
        windah.addCourse(java, golang, php);

        instructorRepository.save(windah);

        // Get data from database
        System.out.println("\nRetrieve data from database...");
        Optional<Instructor> windahFromDB = instructorRepository.findById(1);
        if (windahFromDB.isPresent()){
            windahFromDB.get().getCourses().forEach(System.out::println);
        }

    }
}
