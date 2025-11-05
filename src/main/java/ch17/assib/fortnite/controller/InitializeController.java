package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Chapter;
import ch17.assib.fortnite.repositories.ChapterRepository;
import ch17.assib.fortnite.repositories.LocationRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * @author Assib Pajman
 *
 */
@Controller
public class InitializeController {
    private final ChapterRepository chapterRepository;
    private final LocationRepository locationRepository;

    public InitializeController(ChapterRepository chapterRepository, LocationRepository locationRepository) {
        this.chapterRepository = chapterRepository;
        this.locationRepository = locationRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if(chapterRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        Chapter chapter1 = makeChapter(1);
        Chapter chapter2 = makeChapter(2);
        Chapter chapter3 = makeChapter(3);
        Chapter chapter4 = makeChapter(4);
        Chapter chapter5 = makeChapter(5);
        Chapter chapter6 = makeChapter(6);
    }

    private Chapter makeChapter(Integer chapterNumber) {
        Chapter chapter = new Chapter();

        chapter.setChapterNumber(chapterNumber);
        chapterRepository.save(chapter);

        return chapter;
    }
}
