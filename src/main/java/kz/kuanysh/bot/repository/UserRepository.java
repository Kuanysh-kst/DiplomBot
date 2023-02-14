package kz.kuanysh.bot.repository;

import kz.kuanysh.bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     List<User> findByStatusAndCategory(String fieldValue,String category);


}
