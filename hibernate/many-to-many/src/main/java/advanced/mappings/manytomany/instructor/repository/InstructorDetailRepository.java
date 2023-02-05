package advanced.mappings.manytomany.instructor.repository;

import advanced.mappings.manytomany.instructor.dto.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer> {

    @Modifying
    @Query(
            value = "TRUNCATE TABLE instructor_detail",
            nativeQuery = true
    )
    void truncateTable();
}
