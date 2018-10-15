package com.gmail.evanloafakahaitao.pcstore.controller.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.ItemValidator;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsAPIController {

    private static final Logger logger = LogManager.getLogger(ItemsAPIController.class);

    private final ItemService itemService;
    private final OrderService orderService;
    private final PageProperties pageProperties;
    private final ItemValidator itemValidator;
    private final PaginationUtil paginationUtil;

    @Autowired
    public ItemsAPIController(
            ItemService itemService,
            PageProperties pageProperties,
            OrderService orderService,
            ItemValidator itemValidator,
            PaginationUtil paginationUtil
    ) {
        this.itemService = itemService;
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.itemValidator = itemValidator;
        this.paginationUtil = paginationUtil;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_items_api')")
    public List<ItemDTO> getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        logger.debug("Executing Item API Controller method : getItems");
        return itemService.findAllNotDeleted(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_item_api')")
    public ResponseEntity<String> createItem(
            @RequestBody ItemDTO item,
            BindingResult result
    ) {
        logger.debug("Executing Item API Controller method : createItem");
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>("item " + item.getVendorCode(), HttpStatus.CONFLICT);
        } else {
            ItemDTO itemSaved = itemService.save(item);
            return new ResponseEntity<>("item id " + itemSaved.getId(), HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('view_items_api')")
    public ItemDTO getItem(@PathVariable(name = "id") Long id) {
        logger.debug("Executing Item API Controller method : getItem with id " + id);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);
        return itemService.findById(itemDTO);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('delete_item_api')")
    public ResponseEntity<String> deleteItem(@PathVariable(name = "id") Long id) {
        logger.debug("Executing Item API Controller method : deleteItem with id " + id);
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        Long countOfOrdersForItem = orderService.countByItemId(item);
        if (countOfOrdersForItem != null && countOfOrdersForItem.equals(0L)) {
            itemService.hardDelete(item);
            return ResponseEntity.status(204)
                    .header("Delete h")
                    .body("hard, item id " + id);
        } else {
            itemService.softDelete(item);
            return ResponseEntity.status(204)
                    .header("Delete s")
                    .body("soft, item id " + id);
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_item_api')")
    public @ResponseBody ResponseEntity<JSONPObject> updateItem(
            @RequestBody ItemDTO item,
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing Item API Controller method : updateItem with id " + id);
        item.setId(id);
        itemService.update(item);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
