package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { //repozytorium dla User (punkt styku z frameworkiem)
    User findByLogin(String login);
}

