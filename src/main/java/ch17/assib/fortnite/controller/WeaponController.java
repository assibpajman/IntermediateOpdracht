package ch17.assib.fortnite.controller;

import ch17.assib.fortnite.model.Weapon;
import ch17.assib.fortnite.repositories.WeaponRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Assib Pajman
 *
 */
@Controller
@RequestMapping("/weapon")
public class WeaponController {
    private final WeaponRepository weaponRepository;

    public WeaponController(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @GetMapping("/all")
    public String showWeaponOverview(Model datamodel) {
        datamodel.addAttribute("allWeapons", weaponRepository.findAll());
        datamodel.addAttribute("formWeapon", new Weapon());

        return "weaponOverview";
    }

    @PostMapping("/save")
    public String saveOrUpdateWeapon(@ModelAttribute("formWeapon") Weapon weapon, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/weapon/all";
        }

        weaponRepository.save(weapon);
        return "redirect:/weapon/all";
    }

    @GetMapping("/delete/{weaponId}")
    public String deleteWeapon(@PathVariable("weaponId") Long weaponId) {
        weaponRepository.deleteById(weaponId);
        return "redirect:/weapon/all";
    }
}
