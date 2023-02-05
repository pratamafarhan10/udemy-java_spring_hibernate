package advanced.mappings.manytomany.instructor.repository;

import advanced.mappings.manytomany.instructor.dto.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Modifying
    @Query(
            value = "TRUNCATE TABLE review",
            nativeQuery = true
    )
    void truncateTable();
}
