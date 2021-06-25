package kc.jit_task;

import kc.jit_task.entity.Pokemon;
import kc.jit_task.entity.PokemonRepository;
import kc.jit_task.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Endpoints {

    @Autowired
    PokemonRepository pokemonRepository;


    @GetMapping(
            value="getById",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> getById(@RequestParam Integer id){
        Pokemon pokemon;
        pokemon = pokemonRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .body(pokemon);
    }

    @GetMapping(
            value="getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pokemon>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize){
        List<Pokemon> pokemonList;
        Pageable paging = PageRequest.of(pageNo, pageSize);
        pokemonList = pokemonRepository.findAll(paging).getContent();
        return ResponseEntity.ok()
               .body(pokemonList);
    }

    @GetMapping(
            value="getByName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pokemon>> getByName(@RequestParam String name){
        List<Pokemon> pokemonList;
        pokemonList = pokemonRepository.findByNameLikeIgnoreCase(name);
        return ResponseEntity.ok()
                .body(pokemonList);
    }

    @GetMapping(
            value="getByType",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pokemon>> getByType(
            @RequestParam String type,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize){
        List<Pokemon> pokemonList;
        Pageable paging = PageRequest.of(pageNo, pageSize);
        pokemonList = pokemonRepository.findByTypeLikeIgnoreCase(type,paging).getContent();
        return ResponseEntity.ok()
                .body(pokemonList);
    }

    @PostMapping(
            value="saveAll",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveAll(@RequestBody List<Pokemon> pokemonList){
        pokemonRepository.saveAll(pokemonList);
        return ResponseEntity.ok()
                .body("ok");
    }

    @PostMapping(
            value="saveOne",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOne(@RequestBody Pokemon pokemon){
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok()
                .body("ok");
    }

    @PutMapping(
            value="updateById",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateById(
            @RequestBody Pokemon pokemon,
            @RequestParam Integer id){


        if(pokemonRepository.existsById(id)){
            pokemon.setId(id);
            pokemonRepository.save(pokemon);
            return ResponseEntity.ok()
                    .body("ok");
        }

        return ResponseEntity.badRequest()
                .body("Id not found");
    }

    @PutMapping(
            value="updateByName",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateByName(
            @RequestBody Pokemon pokemon,
            @RequestParam String name){

        List<Pokemon> pokemonDb;
        pokemonDb = pokemonRepository.findByNameLikeIgnoreCase(name);

        if(pokemonDb.size() > 0){
           pokemon.setId(pokemonDb.get(0).getId());
            pokemonRepository.save(pokemon);
            return ResponseEntity.ok()
                    .body("ok");
        }

        return ResponseEntity.badRequest()
                .body("Name not found");
    }

    @DeleteMapping(value="deleteById")
    public ResponseEntity<String> deleteById(@RequestParam Integer id){
        pokemonRepository.deleteById(id);
        return ResponseEntity.ok()
                .body("ok");
    }

    @DeleteMapping(
            value="delete",
            consumes = MediaType.APPLICATION_JSON_VALUE)
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
