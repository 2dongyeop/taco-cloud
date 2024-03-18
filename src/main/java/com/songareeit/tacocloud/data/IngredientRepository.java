package com.songareeit.tacocloud.data;

import com.songareeit.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2024. 01. 23
 * @description
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2024. 01. 23     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}