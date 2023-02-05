package advanced.mappings.manytomany.instructor.repository;

import advanced.mappings.manytomany.instructor.dto.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 0",
            nativeQuery = true
    )
    void setForeignKeyChecksToZero();

    @Modifying
    @Query(
            value = "TRUNCATE TABLE instructor",
            nativeQuery = true
    )
    void truncateTable();

    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 1;",
            nativeQuery = true
    )
    void setForeignKeyChecksToOne();
}
