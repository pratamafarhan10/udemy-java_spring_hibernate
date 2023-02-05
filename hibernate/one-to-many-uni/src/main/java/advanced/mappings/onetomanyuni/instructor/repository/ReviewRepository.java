package advanced.mappings.onetomanyuni.instructor.repository;

import advanced.mappings.onetomanyuni.instructor.dto.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Modifying
    @Query(
            value = "TRUNCATE TABLE review",
            nativeQuery = true
    )
    void truncateTable();
}
