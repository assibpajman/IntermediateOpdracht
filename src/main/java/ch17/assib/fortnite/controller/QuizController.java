package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Chapter;
import ch17.assib.fortnite.model.Weapon;
import ch17.assib.fortnite.repositories.ChapterRepository;
import ch17.assib.fortnite.repositories.WeaponRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Assib Pajman
 *
 */
@Controller
public class QuizController {
    private static final int NUMBER_OF_PROBLEMS = 4;
    private final ChapterRepository chapterRepository;
    private final WeaponRepository weaponRepository;

    public QuizController(ChapterRepository chapterRepository, WeaponRepository weaponRepository) {
        this.chapterRepository = chapterRepository;
        this.weaponRepository = weaponRepository;
    }

    @GetMapping("/quiz")
    private String showQuiz(Model datamodel, Chapter chapter, Weapon weapon) {
        List<Weapon> weapons = weaponRepository.findAll();
        Collections.shuffle(weapons);


        if (weapons.size() > NUMBER_OF_PROBLEMS) {
            weapons = weapons.subList(0, NUMBER_OF_PROBLEMS);
        }

        datamodel.addAttribute("chapters", chapterRepository.findAll());
        datamodel.addAttribute("weapons", weapons);

        return "quiz";
    }

    @PostMapping("quiz/result")
    private String showResult(@RequestParam Map<String, String> selectedChapters, Model datamodel) {
        int correct = 0;

        for (Map.Entry<String, String> entry : selectedChapters.entrySet()) {
            Long weaponId = Long.parseLong(entry.getKey());
            Optional<Weapon> weapon = weaponRepository.findById(weaponId);

            String selectedChapterNumber = entry.getValue();

            if (weapon.isPresent()) {
                Chapter chapter = weapon.get().getChapters().iterator().next();
                if (chapter.getChapterNumber().toString().equals(selectedChapterNumber)) {
                    correct++;
                }
            }
        }

        datamodel.addAttribute("correct", correct);
        datamodel.addAttribute("total", NUMBER_OF_PROBLEMS);

        return "quizResult";
    }
}
