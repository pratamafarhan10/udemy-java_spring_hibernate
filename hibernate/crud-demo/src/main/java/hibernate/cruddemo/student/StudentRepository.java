package hibernate.cruddemo.student;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hibernate.cruddemo.student.dto.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    
    @Query("SELECT s FROM Student s WHERE s.lastName = :lastName")
    public List<Student> findStudentWithLastName(@Param("lastName") String lastName);

    @Modifying
    @Query(
        value = "TRUNCATE TABLE Student",
        nativeQuery = true
    )
    public void truncateTable();

    @Modifying
    @Query("UPDATE Student s set s.firstName = :#{#newStudent.firstName}, s.lastName = :#{#newStudent.lastName}, s.email = :#{#newStudent.email} WHERE s.id = :id")
    public void update(@Param("newStudent") Student newStudent, @Param("id") Integer id);
}
