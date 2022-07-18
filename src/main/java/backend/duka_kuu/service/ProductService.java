package backend.duka_kuu.service;

import backend.duka_kuu.domain.Category;
//import backend.duka_kuu.domain.Inventory;
import backend.duka_kuu.domain.Product;
import backend.duka_kuu.domain.Subcategory;
import backend.duka_kuu.model.ProductDTO;
import backend.duka_kuu.repos.CategoryRepository;
import backend.duka_kuu.repos.InventoryRepository;
import backend.duka_kuu.repos.ProductRepository;
import backend.duka_kuu.repos.SubcategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final InventoryRepository inventoryRepository;

    public ProductService(final ProductRepository productRepository,
            final CategoryRepository categoryRepository,
            final SubcategoryRepository subcategoryRepository,
            final InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll(Sort.by("id"))
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductImage(product.getProductImage());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductQuantity(product.getProductQuantity());
        productDTO.setCategory(product.getCategory() == null ? null : product.getCategory().getId());
        productDTO.setSubcategory(product.getSubcategory() == null ? null : product.getSubcategory().getId());
        //productDTO.setInventory(product.getInventory() == null ? null : product.getInventory().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductImage(productDTO.getProductImage());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductQuantity(productDTO.getProductQuantity());
        final Category category = productDTO.getCategory() == null ? null : categoryRepository.findById(productDTO.getCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));
        product.setCategory(category);
        final Subcategory subcategory = productDTO.getSubcategory() == null ? null : subcategoryRepository.findById(productDTO.getSubcategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "subcategory not found"));
        product.setSubcategory(subcategory);
//        final Inventory inventory = productDTO.getInventory() == null ? null : inventoryRepository.findById(productDTO.getInventory())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "inventory not found"));
//        product.setInventory(inventory);
        return product;
    }

}
