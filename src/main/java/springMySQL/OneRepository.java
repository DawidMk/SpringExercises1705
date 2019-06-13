package springMySQL;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import springMySQL.entities.BaseEntity;
import springMySQL.entities.AppUser;

public interface OneRepository<T extends BaseEntity, Integer> extends CrudRepository<T, Integer> {

    @Query(value = "select * from users u where u.login = :login", nativeQuery = true)
    Iterable<AppUser> findByLogin(@Param("login") String login);

//    @Query(value = "select * from users u,  where u.email = :email", nativeQuery = true)
//    Iterable<AppUser> findByEmail(@Param("email") String email);

    //    @Query(value = "select email, last_name from app_users a, persons p where app_users.id = persons.app_user_id and app_users.email = :email", nativeQuery = true)
//    @Query(value = "select email, last_name from app_users a join persons p on a.id = p.app_user_id where email = :email", nativeQuery = true)
    @Query("select u from AppUser u where u.email = ?1")
    Iterable<T> findByEmail(@Param("email") String email);

    //    @Query(value = "select id from app_users where email = :email", nativeQuery = true)
//    Iterable<AppUser> findByEmail(@Param("email") String email);
    @Query("select u from AppUser u")
    Iterable<T> findAllUsers();

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM AppUser u WHERE u.email = ?1")
    Boolean existsByEmail();
}
