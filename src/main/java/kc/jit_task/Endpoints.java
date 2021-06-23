package kc.jit_task;

import kc.jit_task.entity.Pokemon;
import kc.jit_task.entity.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Endpoints {

    @Autowired
    PokemonRepository pokemonRepository;

    @GetMapping(value="getById")
    public ResponseEntity<Pokemon> getById(@RequestParam Integer id){
        Pokemon pokemon;
        pokemon = pokemonRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .body(pokemon);
    }
    @GetMapping(value="getAll")
    public ResponseEntity<List<Pokemon>> getAll(){
        List<Pokemon> pokemonList;
        pokemonList = pokemonRepository.findAll();
        return ResponseEntity.ok()
                .body(pokemonList);
    }
    @GetMapping(value="getByName")
    public ResponseEntity<Pokemon> getByName(@RequestParam String name){
        Pokemon pokemon;
        pokemon = pokemonRepository.findByNameLikeIgnoreCase(name);
        return ResponseEntity.ok()
                .body(pokemon);
    }
    @PostMapping(value="saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Pokemon> pokemonList){
        pokemonRepository.saveAll(pokemonList);
        return ResponseEntity.ok()
                .body("ok");
    }
    @PostMapping(value="saveOne")
    public ResponseEntity<String> saveOne(@RequestBody Pokemon pokemon){
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok()
                .body("ok");
    }
    @PutMapping(value="update")
    public ResponseEntity<String> update(@RequestBody Pokemon pokemon){
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok()
                .body("ok");
    }
    @DeleteMapping(value="deleteById")
    public ResponseEntity<String> deleteById(@RequestParam Integer id){
        pokemonRepository.deleteById(id);
        return ResponseEntity.ok()
                .body("ok");
    }
    @DeleteMapping(value="delete")
    public ResponseEntity<String> delete(@RequestBody Pokemon pokemon){
        pokemonRepository.delete(pokemon);
        return ResponseEntity.ok()
                .body("ok");
    }
    @DeleteMapping(value="deleteAll")
    public ResponseEntity<String> deleteAll(){
        pokemonRepository.deleteAll();
        return ResponseEntity.ok()
                .body("ok");
    }
}
