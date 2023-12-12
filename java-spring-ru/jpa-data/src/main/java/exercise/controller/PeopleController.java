package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

//import java.lang.foreign.Linker;
import java.util.List;
import java.util.Optional;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> index(@RequestParam(defaultValue = "10") Integer limit) {
        return personRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create (@Validated @RequestBody Person p) {
        var res = personRepository.save(p);
        return res;
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        var user = personRepository.findById(id).get();
        personRepository.delete(user);
    }
    // END
}