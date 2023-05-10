package repositories;

import entities.UserOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserOptionsRepository extends JpaRepository<UserOptions, UUID> {
}