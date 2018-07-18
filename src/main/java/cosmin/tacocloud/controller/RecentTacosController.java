package cosmin.tacocloud.controller;


import cosmin.tacocloud.domain.Taco;
import cosmin.tacocloud.repository.TacoRepository;
import cosmin.tacocloud.utility.TacoResource;
import cosmin.tacocloud.utility.TacoResourceAssembler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosController {

    private TacoRepository tacoRepository;

    public RecentTacosController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<Resources<TacoResource>> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();
        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
        Resources<TacoResource> resourceResources = new Resources<TacoResource>(tacoResources);
        resourceResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
        return new ResponseEntity<>(resourceResources, HttpStatus.OK);
    }
}
