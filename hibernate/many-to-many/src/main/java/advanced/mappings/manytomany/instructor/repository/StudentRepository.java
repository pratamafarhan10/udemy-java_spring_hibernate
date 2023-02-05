package advanced.mappings.manytomany.instructor.repository;

import advanced.mappings.manytomany.instructor.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Query(
            value = "TRUNCATE TABLE student",
            nativeQuery = true
    )
    void truncateTable();

    @Modifying
    @Query(
            value = "TRUNCATE TABLE course_student",
            nativeQuery = true
    )
    void truncateTableCourseStudent();
}
