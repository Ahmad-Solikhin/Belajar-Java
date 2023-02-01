package spring.starter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.starter.domain.Publisher;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    public Optional<Publisher> findBySecureIdAndDeletedFalse(String secureId);

    public Page<Publisher> findByNameLikeIgnoreCase(String publisherName, Pageable pageable);

}
