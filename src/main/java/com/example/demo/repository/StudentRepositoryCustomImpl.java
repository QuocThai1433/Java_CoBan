package com.example.demo.repository;

import com.example.demo.dto.QueryStudentRequest;
import com.example.demo.dto.QueryStudentRequestV2;
import com.example.demo.entity.Students;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentRepositoryCustomImpl implements IStudentRepositoryCustom {
    private static final List<String> sortList = List.of(
        "name",
        "age",
        "address",
        "score",
        "dateMonth"
    );

    private final EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Students> getByFilter(Sort.Direction nameSortType) {
        String query = "SELECT st FROM students st ORDER BY st.name";
        if (Sort.Direction.ASC.equals(nameSortType)) {
            query = query + " " + Sort.Direction.ASC.name();
        } else {
            query = query + " " + Sort.Direction.DESC.name();
        }
        Query typedQuery = entityManager.createNativeQuery(query, Students.class);
        return typedQuery.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Students> query(QueryStudentRequest request) {
        String query = "select * " +
            "from students " +
            "where if(:name is null, true, name like concat('%', :name, '%')) " +
            "  and if(:age is null, true, age = :age) " +
            "  and if(:score is null, true, score = :score) " +
            "  and if(:address is null, true, address = :address) ";

        query = this.buildSort(query, request.getSorts());

        Query typeQuery = entityManager.createNativeQuery(query, Students.class)
            .setParameter("name", request.getFullName())
            .setParameter("age", request.getAge())
            .setParameter("score", request.getScore())
            .setParameter("address", request.getAddress());

        return typeQuery.getResultList();
    }

    private String buildSort(String query, List<String> sorts) {
        if (sorts.isEmpty()) {
            return query;
        }
        return query + "order by " +
            String.join(", ", sorts);
    }

}
