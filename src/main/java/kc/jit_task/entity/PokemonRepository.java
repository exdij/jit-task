package kc.jit_task.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    List<Pokemon> findAll();
    List<Pokemon> findByNameLikeIgnoreCase(String name);
    Page<Pokemon> findByTypeLikeIgnoreCase(String type, Pageable paging);
}
