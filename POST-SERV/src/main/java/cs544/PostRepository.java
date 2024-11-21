package cs544;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContaining(Pageable pageable, String infix);
    // Page<Post> findByCreatedAfter(Pageable pageable, ZonedDateTime createdDate);
}
