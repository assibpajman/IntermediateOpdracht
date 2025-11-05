package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Chapter;
import ch17.assib.fortnite.model.Location;
import ch17.assib.fortnite.repositories.ChapterRepository;
import ch17.assib.fortnite.repositories.LocationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Assib Pajman
 *
 */
@Controller
@RequestMapping("/location")
public class LocationController {
    private final ChapterRepository chapterRepository;
    private final LocationRepository locationRepository;

    public LocationController(ChapterRepository chapterRepository, LocationRepository locationRepository) {
        this.chapterRepository = chapterRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/new/{chapterId}")
    private String createNewLocation(@PathVariable("chapterId") Long chapterId) {
        Optional<Chapter> optionalChapter = chapterRepository.findById(chapterId);

        if (optionalChapter.isPresent()) {
            Location location = new Location(optionalChapter.get());
            locationRepository.save(location);
        }

        return "redirect:/chapter/all";
    }
}
