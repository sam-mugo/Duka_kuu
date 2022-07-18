package backend.duka_kuu.service;

import backend.duka_kuu.domain.Category;
import backend.duka_kuu.domain.Subcategory;
import backend.duka_kuu.model.SubcategoryDTO;
import backend.duka_kuu.repos.CategoryRepository;
import backend.duka_kuu.repos.SubcategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubcategoryService(final SubcategoryRepository subcategoryRepository,
            final CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<SubcategoryDTO> findAll() {
        return subcategoryRepository.findAll(Sort.by("id"))
                .stream()
                .map(subcategory -> mapToDTO(subcategory, new SubcategoryDTO()))
                .collect(Collectors.toList());
    }

    public SubcategoryDTO get(final Long id) {
        return subcategoryRepository.findById(id)
                .map(subcategory -> mapToDTO(subcategory, new SubcategoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SubcategoryDTO subcategoryDTO) {
        final Subcategory subcategory = new Subcategory();
        mapToEntity(subcategoryDTO, subcategory);
        return subcategoryRepository.save(subcategory).getId();
    }

    public void update(final Long id, final SubcategoryDTO subcategoryDTO) {
        final Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(subcategoryDTO, subcategory);
        subcategoryRepository.save(subcategory);
    }

    public void delete(final Long id) {
        subcategoryRepository.deleteById(id);
    }

    private SubcategoryDTO mapToDTO(final Subcategory subcategory,
            final SubcategoryDTO subcategoryDTO) {
        subcategoryDTO.setId(subcategory.getId());
        subcategoryDTO.setSubcategoryName(subcategory.getSubcategoryName());
        subcategoryDTO.setSubcategoryDescription(subcategory.getSubcategoryDescription());
        subcategoryDTO.setSubcategoryImage(subcategory.getSubcategoryImage());
        subcategoryDTO.setCategory(subcategory.getCategory() == null ? null : subcategory.getCategory().getId());
        return subcategoryDTO;
    }

    private Subcategory mapToEntity(final SubcategoryDTO subcategoryDTO,
            final Subcategory subcategory) {
        subcategory.setSubcategoryName(subcategoryDTO.getSubcategoryName());
        subcategory.setSubcategoryDescription(subcategoryDTO.getSubcategoryDescription());
        subcategory.setSubcategoryImage(subcategoryDTO.getSubcategoryImage());
        final Category category = subcategoryDTO.getCategory() == null ? null : categoryRepository.findById(subcategoryDTO.getCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));
        subcategory.setCategory(category);
        return subcategory;
    }

}
