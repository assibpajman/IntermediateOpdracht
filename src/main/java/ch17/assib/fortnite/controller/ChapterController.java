package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Chapter;
import ch17.assib.fortnite.repositories.ChapterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Assib Pajman
 *
 */

@Controller
public class ChapterController {
    private final ChapterRepository chapterRepository;

    public ChapterController(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @GetMapping({"/chapter/all", "/"})
    private String showChapterOverview(Model datamodel) {

        datamodel.addAttribute("chapters", chapterRepository.findAll());

        return "chapterOverview";
    }

    @GetMapping("/chapter/add")
    public String showChapterForm(Model datamodel) {
        datamodel.addAttribute("formChapter", new Chapter());

        return "chapterForm";
    }

    @PostMapping("/chapter/save")
    public String saveOrUpdateChapter(@ModelAttribute("formChapter") Chapter chapter, BindingResult result) {
        if (!result.hasErrors()) {
            chapterRepository.save(chapter);
        }

        return "redirect:/chapter/all";
    }

    @GetMapping("/chapter/delete/{chapterId}")
    public String deleteChapter(@PathVariable("chapterId") Long chapterId) {
        chapterRepository.deleteById(chapterId);
        
        return "redirect:/chapter/all";
    }

    @GetMapping("/chapter/edit/{chapterId}")
    public String showEditChapterForm(@PathVariable("chapterId") Long chapterId, Model datamodel) {
        Optional<Chapter> optionalChapter = chapterRepository.findById(chapterId);

        if (optionalChapter.isPresent()) {
            datamodel.addAttribute("formChapter", optionalChapter.get());
            return "chapterForm";
        }

        return "redirect:/chapter/all";
    }
}
