package ch17.assib.fortnite.repositories;

import ch17.assib.fortnite.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
