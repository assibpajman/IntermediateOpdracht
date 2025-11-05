package ch17.assib.fortnite.repositories;

import ch17.assib.fortnite.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Optional<Chapter> findByChapterNumber(Integer chapterNumber);
}
