package hibernate.onetooneuni.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hibernate.onetooneuni.instructor.dto.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
    
    @Modifying
    @Query(
        value = "SET FOREIGN_KEY_CHECKS = 0",
        nativeQuery = true
    )
    public void setForeignKeyChecksToZero();

    @Modifying
    @Query(
        value = "TRUNCATE TABLE instructor",
        nativeQuery = true
    )
    public void truncateTable();

    @Modifying
    @Query(
        value = "SET FOREIGN_KEY_CHECKS = 1;",
        nativeQuery = true
    )
    public void setForeignKeyChecksToOne();
}
