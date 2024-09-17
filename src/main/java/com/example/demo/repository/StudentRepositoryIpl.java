package com.example.demo.repository;

import com.example.demo.entity.Students;
import com.example.demo.enumerate.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StudentRepositoryIpl implements IStudentRepositoryCustorm {

    public final EntityManager entityManager;
    @Override
    @SuppressWarnings("unchecked")
    public List<Students> getByFilter(String nameSort,
                                      String nameType,
                                      String fullName,
                                      Integer age,
                                      Float score,
                                      String address) {
        String query = "select * from students where " +
            "if(:name is null, true, name = :name) " +
            "and if(:age is null, true, age = :age) " +
            "and if(:score is null, true, score = :score) " +
            "and if(:address is null, true, address = :address)";

        if (nameSort.equals("name ")) {
            query = query + "name";
        } else {
            query = query + "age ";
        }

        if (nameType.equals("asc")) {
            query = query + "asc";
        } else {
            query = query + "desc";
        }

        Query typeQuery = entityManager.createNativeQuery(query, Students.class);
        typeQuery.setParameter("name", fullName);
        typeQuery.setParameter("age", age);
        typeQuery.setParameter("score", score);
        typeQuery.setParameter("address", address);

        return typeQuery.getResultList();
    }
}
