package advance.mapping.onetoonebi.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import advance.mapping.onetoonebi.instructor.dto.InstructorDetail;

public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer>{
    
    @Modifying
    @Query(
        value = "TRUNCATE TABLE instructor_detail",
        nativeQuery = true
    )
    public void truncateTable();
}