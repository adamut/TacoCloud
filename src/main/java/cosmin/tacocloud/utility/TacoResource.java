package cosmin.tacocloud.utility;

import cosmin.tacocloud.domain.Ingredient;
import cosmin.tacocloud.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(value="taco", collectionRelation="tacos")
public class TacoResource extends ResourceSupport {


    private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

    @Getter
    private final String name;

    @Getter
    private final Date createdAt;


    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
    }
}
