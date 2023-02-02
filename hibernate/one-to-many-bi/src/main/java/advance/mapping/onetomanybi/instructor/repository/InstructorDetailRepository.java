package advance.mapping.onetomanybi.instructor.repository;

import advance.mapping.onetomanybi.instructor.dto.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer>{

    @Modifying
    @Query(
            value = "TRUNCATE TABLE instructor_detail",
            nativeQuery = true
    )
    public void truncateTable();
}
