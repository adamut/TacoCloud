package cosmin.tacocloud.service;

import cosmin.tacocloud.domain.Ingredient;
import cosmin.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;


    public Ingredient saveNewIngrd(Ingredient ingredient) {

        return repository.save(ingredient);
    }
}
