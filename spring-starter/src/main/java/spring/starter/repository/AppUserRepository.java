package spring.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.starter.domain.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public Optional<AppUser> findByUsername(String username);
}
