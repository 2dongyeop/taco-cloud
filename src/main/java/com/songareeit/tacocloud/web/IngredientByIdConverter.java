package com.songareeit.tacocloud.web;

import com.songareeit.tacocloud.Ingredient;
import com.songareeit.tacocloud.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2024. 01. 25
 * @description
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2024. 01. 25     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
@Component
public class IngredientByIdConverter implements Converter<String, Optional<Ingredient>> {
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Optional<Ingredient> convert(String id) {
        return ingredientRepo.findById(id);
    }
}
