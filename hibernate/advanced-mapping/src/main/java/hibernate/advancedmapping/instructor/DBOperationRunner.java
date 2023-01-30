package hibernate.advancedmapping.instructor;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hibernate.advancedmapping.instructor.dto.Instructor;
import hibernate.advancedmapping.instructor.dto.InstructorDetail;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorDetailRepository instructorDetailRepository;
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        instructorRepository.setForeignKeyChecksToZero();
        instructorRepository.truncateTable();
        instructorDetailRepository.truncateTable();
        instructorRepository.setForeignKeyChecksToOne();

        System.out.println("Saving new data into table...");
        Instructor instructor1 = new Instructor("john", "doe", "johndoe@gmail.com");
        instructor1.setInstructorDetail(new InstructorDetail("luv2code", "making java videos"));

        // Note: this will ALSO save the instructor details object
        // because of CascadeType.ALL
        instructorRepository.save(instructor1);
    }
}
