package backend.duka_kuu.rest;

import backend.duka_kuu.model.SubcategoryDTO;
import backend.duka_kuu.service.SubcategoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubcategoryResource {

    private final SubcategoryService subcategoryService;

    public SubcategoryResource(final SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/subcategories")
    public ResponseEntity<List<SubcategoryDTO>> getAllSubcategorys() {
        return ResponseEntity.ok(subcategoryService.findAll());
    }

    @GetMapping("/subcategory/{id}")
    public ResponseEntity<SubcategoryDTO> getSubcategory(@PathVariable final Long id) {
        return ResponseEntity.ok(subcategoryService.get(id));
    }

    @PostMapping("subcategory")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSubcategory(
            @RequestBody @Valid final SubcategoryDTO subcategoryDTO) {
        return new ResponseEntity<>(subcategoryService.create(subcategoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/subcategory/{id}")
    public ResponseEntity<Void> updateSubcategory(@PathVariable final Long id,
            @RequestBody @Valid final SubcategoryDTO subcategoryDTO) {
        subcategoryService.update(id, subcategoryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subcategory/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable final Long id) {
        subcategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
