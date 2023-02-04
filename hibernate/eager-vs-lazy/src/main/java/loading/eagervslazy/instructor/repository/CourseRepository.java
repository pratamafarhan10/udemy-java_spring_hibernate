package loading.eagervslazy.instructor.repository;

import loading.eagervslazy.instructor.dto.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Modifying
    @Query(
            value = "TRUNCATE TABLE course",
            nativeQuery = true
    )
    void truncateTable();
}

