package com.songareeit.tacocloud.data;

import com.songareeit.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2024. 01. 24
 * @description
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2024. 01. 24     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {
}
