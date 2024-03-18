package com.songareeit.tacocloud.web;

import com.songareeit.tacocloud.domain.Ingredient;
import com.songareeit.tacocloud.domain.Order;
import com.songareeit.tacocloud.domain.Taco;
import com.songareeit.tacocloud.data.IngredientRepository;
import com.songareeit.tacocloud.data.TacoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.songareeit.tacocloud.domain.Ingredient.*;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2024. 01. 10
 * @description
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2024. 01. 10     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepo;

    private TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        Type[] types = Type.values();
        Arrays.stream(types)
                .forEach(type ->
                        model.addAttribute(
                                type.toString().toLowerCase(),
                                filterByType(ingredients, type))
                );

        model.addAttribute("taco", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .toList();
    }
}
