package advanced.mappings.onetomanyuni.instructor.repository;

import advanced.mappings.onetomanyuni.instructor.dto.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer> {

    @Modifying
    @Query(
            value = "TRUNCATE TABLE instructor_detail",
            nativeQuery = true
    )
    void truncateTable();
}
