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

        // Bi-directional

        System.out.println("======== Get data from the weak instructor_detail");
        InstructorDetail thirdInstructorDetail = new InstructorDetail("windah basudara", "livestream");
        Instructor windah = new Instructor("windah", "basudara", "windahbasudara@gmail.com");
        
        thirdInstructorDetail.setInstructor(windah);
        // thirdInstructorDetail.getInstructor().setInstructorDetail(thirdInstructorDetail);
        thirdInstructorDetail = instructorDetailRepository.save(thirdInstructorDetail);

        System.out.println("Get data from instructor_detail");
        Optional<InstructorDetail> data3 = instructorDetailRepository.findById(3);
        System.out.println("\t" + (data3.isPresent() ? data3.get() : "No data found"));

        System.out.println("======== Delete data from the weak instructor_detail");
        thirdInstructorDetail.getInstructor().setInstructorDetail(null);
        instructorDetailRepository.deleteById(3);

        System.out.println("======== Set new instructor detail to windah basudara");
        InstructorDetail newInstructorDetail = new InstructorDetail("drake channel", "singing");
        windah.setInstructorDetail(newInstructorDetail);

    }
}

