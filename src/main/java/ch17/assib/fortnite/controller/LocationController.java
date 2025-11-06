package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Chapter;
import ch17.assib.fortnite.model.Location;
import ch17.assib.fortnite.model.Weapon;
import ch17.assib.fortnite.repositories.ChapterRepository;
import ch17.assib.fortnite.repositories.LocationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public String showLocationOverview(Model datamodel) {
        datamodel.addAttribute("allLocations", locationRepository.findAll());
        datamodel.addAttribute("formLocation", new Location());

        return "locationOverview";
    }

    @PostMapping("/save")
    public String saveOrUpdateLocation(@ModelAttribute("formLocation") Location location, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/location/all";
        }

        locationRepository.save(location);
        return "redirect:/location/all";
    }

    @GetMapping("/delete/{locationId}")
    public String deleteLocation(@PathVariable("locationId") Long locationId) {
        locationRepository.deleteById(locationId);
        return "redirect:/location/all";
    }
}
