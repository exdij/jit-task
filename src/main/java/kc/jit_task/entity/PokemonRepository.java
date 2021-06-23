package kc.jit_task.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    List<Pokemon> findAll();
    Pokemon findByNameLikeIgnoreCase(String name);
}
