package teachin.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teachin.server.entity.ScientificDegree;
import teachin.server.entity.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepo extends CommonRepo<Teacher> {
    Iterable<Teacher> findAllByScientificDegree(ScientificDegree degree);

    Iterable<Teacher> findAllByVacancyName(String position);

    @Query("""
            select t from Teacher t
            where t.name = ?1 or t.surname = ?1 or t.patronymic = ?1
            """)
    Optional<Teacher> findByFIO(String fio);

    @Query("select t from Teacher t where t.socialWork is null")
    Iterable<Teacher> findAllWithEmptySocialWork();

    Teacher findByVacancyName(String name);

    Iterable<Teacher> findAllBySocialWork(String work);

    @Query("select t from Teacher t where t.socialWork is not null")
    Iterable<Teacher> findTeachersWithNotEmptySocialWork();

    Iterable<Teacher> findAllByHoursLessThan(Integer hours);

    Iterable<Teacher> findAllByHoursGreaterThan(Integer hours);
}