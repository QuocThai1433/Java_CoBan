package com.example.demo.repository;

import com.example.demo.entity.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IStudentRepository extends JpaRepository<Students, UUID> {
    @Query(value = "SELECT * FROM students ORDER BY id ASC limit :count",
        nativeQuery = true)
    List<Students> studentList(@Param("count") Integer count);

    @Query(value = "SELECT * FROM students limit :size offset :offset",
        nativeQuery = true)
    List<Students> studentPaging(@Param("size") Integer size, @Param("offset") Integer offset);

    @Query(value = "SELECT * FROM students WHERE  if(:findName is null, true, name LIKE CONCAT('%', :findName, '%'))  and if(:age is null, true,age = :age) ",
        nativeQuery = true)
    List<Students> studentFilter(@Param("findName") String name, @Param("age") Integer age);

    @Query(
        value = "select * " +
            "from students " +
            "where if(:name is null, true, name like concat('%', :name, '%')) " +
            "  and if(:age is null, true, age = :age) " +
            "  and if(:score is null, true, score = :score) " +
            "  and if(:address is null, true, address = :address) ",
        nativeQuery = true,
        countQuery = "select count(*) " +
            "from students " +
            "where if(:name is null, true, name like concat('%', :name, '%')) " +
            "  and if(:age is null, true, age = :age) " +
            "  and if(:score is null, true, score = :score) " +
            "  and if(:address is null, true, address = :address) "
    )
    Page<Students> query(
        String name,
        String address,
        Integer score,
        Integer age,
        Pageable pageable
    );

}
