package backend.duka_kuu.service;

import backend.duka_kuu.domain.Inventory;
import backend.duka_kuu.model.InventoryDTO;
import backend.duka_kuu.repos.InventoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryDTO> findAll() {
        return inventoryRepository.findAll(Sort.by("id"))
                .stream()
                .map(inventory -> mapToDTO(inventory, new InventoryDTO()))
                .collect(Collectors.toList());
    }

    public InventoryDTO get(final Long id) {
        return inventoryRepository.findById(id)
                .map(inventory -> mapToDTO(inventory, new InventoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final InventoryDTO inventoryDTO) {
        final Inventory inventory = new Inventory();
        mapToEntity(inventoryDTO, inventory);
        return inventoryRepository.save(inventory).getId();
    }

    public void update(final Long id, final InventoryDTO inventoryDTO) {
        final Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(inventoryDTO, inventory);
        inventoryRepository.save(inventory);
    }

    public void delete(final Long id) {
        inventoryRepository.deleteById(id);
    }

    private InventoryDTO mapToDTO(final Inventory inventory, final InventoryDTO inventoryDTO) {
        inventoryDTO.setId(inventory.getId());
        inventoryDTO.setSkuCode(inventory.getSkuCode());
        inventoryDTO.setQuantity(inventory.getQuantity());
        inventoryDTO.setStorePrice(inventory.getStorePrice());
        inventoryDTO.setSalePrice(inventory.getSalePrice());
        inventoryDTO.setWeight(inventory.getWeight());
        return inventoryDTO;
    }

    private Inventory mapToEntity(final InventoryDTO inventoryDTO, final Inventory inventory) {
        inventory.setSkuCode(inventoryDTO.getSkuCode());
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setStorePrice(inventoryDTO.getStorePrice());
        inventory.setSalePrice(inventoryDTO.getSalePrice());
        inventory.setWeight(inventoryDTO.getWeight());
        return inventory;
    }

}
