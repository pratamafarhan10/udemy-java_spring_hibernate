package advanced.mappings.onetomanyuni.instructor;

import advanced.mappings.onetomanyuni.instructor.dto.Course;
import advanced.mappings.onetomanyuni.instructor.dto.Review;
import advanced.mappings.onetomanyuni.instructor.repository.CourseRepository;
import advanced.mappings.onetomanyuni.instructor.repository.InstructorDetailRepository;
import advanced.mappings.onetomanyuni.instructor.repository.InstructorRepository;
import advanced.mappings.onetomanyuni.instructor.repository.ReviewRepository;
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

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        instructorRepository.setForeignKeyChecksToZero();
        instructorRepository.truncateTable();
        instructorDetailRepository.truncateTable();
        courseRepository.truncateTable();
        reviewRepository.truncateTable();
        instructorRepository.setForeignKeyChecksToOne();

        System.out.println("\nSaving data into the database...");

        // Create course
        Course java = new Course("Java development bootcamp");

        // Create reviews
        java.addReview(new Review("What a good course!"));
        java.addReview(new Review("I love it!"));
        java.addReview(new Review("Not worth it!"));

        // Save the course
        courseRepository.save(java);

        System.out.println("\nRetrieve data from the database...");
        Optional<Course> javaFromDB = courseRepository.findById(1);

        if (javaFromDB.isPresent()){
            javaFromDB.get().getReviews().forEach(review -> System.out.println(review.getComment()));
        }

        System.out.println("\nDelete data from the database...");
        courseRepository.deleteById(1);
    }
}
