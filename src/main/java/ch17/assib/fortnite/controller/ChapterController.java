package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Chapter;
import ch17.assib.fortnite.repositories.ChapterRepository;
import ch17.assib.fortnite.repositories.WeaponRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Assib Pajman
 *
 */

@Controller
public class ChapterController {
    private final WeaponRepository weaponRepository;
    private final ChapterRepository chapterRepository;

    public ChapterController(WeaponRepository weaponRepository, ChapterRepository chapterRepository) {
        this.weaponRepository = weaponRepository;
        this.chapterRepository = chapterRepository;
    }

    @GetMapping({"/chapter/all", "/"})
    private String showChapterOverview(Model datamodel) {

        datamodel.addAttribute("chapters", chapterRepository.findAll());

        return "chapterOverview";
    }

    @GetMapping("/chapter/add")
    public String showChapterForm(Model datamodel, Chapter chapter) {
        datamodel.addAttribute("formChapter", new Chapter());

        return "chapterForm";
    }

    @PostMapping("/chapter/save")
    public String saveOrUpdateChapter(@ModelAttribute("formChapter") Chapter chapterToBeSaved,
                                      BindingResult result,
                                      Model datamodel) {
        Optional<Chapter> chapterWithSameChapterNumber =
                chapterRepository.findByChapterNumber(chapterToBeSaved.getChapterNumber());

        if (chapterWithSameChapterNumber.isPresent() &&
                !chapterWithSameChapterNumber.get().getChapterId().equals(chapterToBeSaved.getChapterId())) {
            result.addError(new FieldError("chapter", "chapterNumber",
                    "this chapter is already present"));
        }

        if (result.hasErrors()) {
            return showChapterForm(datamodel, chapterToBeSaved);
        }

        chapterRepository.save(chapterToBeSaved);
        return "redirect:/chapter/all";
    }

    @GetMapping("/chapter/delete/{chapterId}")
    public String deleteChapter(@PathVariable("chapterId") Long chapterId) {
        chapterRepository.deleteById(chapterId);
        
        return "redirect:/chapter/all";
    }

    @GetMapping("/chapter/edit/{chapterNumber}")
    public String showEditChapterForm(@PathVariable("chapterNumber") Integer chapterNumber, Model datamodel) {
        Optional<Chapter> optionalChapter = chapterRepository.findByChapterNumber(chapterNumber);

        if (optionalChapter.isPresent()) {
            datamodel.addAttribute("formChapter", optionalChapter.get());
            return "chapterForm";
        }

        return "redirect:/chapter/all";
    }

    @GetMapping("/chapter/detail/{chapterNumber}")
    public String showChapterDetailpage(@PathVariable("chapterNumber") Integer chapterNumber, Model datamodel) {
        Optional<Chapter> chapterToShow = chapterRepository.findByChapterNumber(chapterNumber);

        if (chapterToShow.isEmpty()) {
            return "redirect:/chapter/all";
        }

        datamodel.addAttribute("chapter", chapterToShow.get());

        return "chapterDetails";
    }
}
