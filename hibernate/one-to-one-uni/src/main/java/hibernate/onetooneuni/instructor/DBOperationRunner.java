package hibernate.onetooneuni.instructor;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hibernate.onetooneuni.instructor.dto.Instructor;
import hibernate.onetooneuni.instructor.dto.InstructorDetail;

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
        instructorRepository.deleteById(1);
    }
}
