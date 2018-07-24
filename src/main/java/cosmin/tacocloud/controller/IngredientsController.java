package cosmin.tacocloud.controller;

import cosmin.tacocloud.domain.Ingredient;
import cosmin.tacocloud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientsController {

    @Autowired
    private IngredientService service;



    @PostMapping("/new")
    public Ingredient saveINg(@RequestBody Ingredient ingredient) {

        return service.saveNewIngrd(ingredient);
    }

    @GetMapping("/ping")
    public String checkHealth() {
        return "is working";
    }
}
