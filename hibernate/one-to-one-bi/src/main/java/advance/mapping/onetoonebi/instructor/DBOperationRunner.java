package advance.mapping.onetoonebi.instructor;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import advance.mapping.onetoonebi.instructor.dto.Instructor;
import advance.mapping.onetoonebi.instructor.dto.InstructorDetail;

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

        Instructor instructor2= new Instructor("paul", "walker", "paulwalker@gmail.com");
        instructor2.setInstructorDetail(new InstructorDetail("luv2code", "drive"));

        // Note: this will ALSO save the instructor details object
        // because of CascadeType.ALL
        Instructor save1 = instructorRepository.save(instructor1);
        Instructor save2 = instructorRepository.save(instructor2);

        System.out.println("Data saved");
        System.out.println("\t" + save1);
        System.out.println("\t" + save2);


        System.out.println("Delete record in table to simulate cascade remove...");
        System.out.println("Delete instructor from instructor table");
        instructorRepository.deleteById(1);

        System.out.println("Get data from instructor_detail");
        Optional<InstructorDetail> data2 = instructorDetailRepository.findById(2);
        System.out.println("\t" + (data2.isPresent() ? data2.get() : "No data found"));
    }
}

