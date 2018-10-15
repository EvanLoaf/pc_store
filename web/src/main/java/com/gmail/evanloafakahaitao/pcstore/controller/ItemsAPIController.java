package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.ItemValidator;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsAPIController {

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
        return itemService.findAllNotDeleted(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_item_api')")
    public ItemDTO createItem(
            @RequestBody ItemDTO item,
            BindingResult result
    ) {
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            return item;
        } else {
            return itemService.save(item);
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('view_items_api')")
    public ItemDTO getItem(@PathVariable(name = "id") Long id) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);
        return itemService.findById(itemDTO);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('delete_item_api')")
    public SimpleItemDTO deleteItem(@PathVariable(name = "id") Long id) {
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        Long countOfOrdersForItem = orderService.countByItemId(item);
        if (countOfOrdersForItem != null && countOfOrdersForItem.equals(0L)) {
            return itemService.hardDelete(item);
        } else {
            return itemService.softDelete(item);
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_item_api')")
    public ItemDTO updateItem(
            @RequestBody ItemDTO item,
            @PathVariable("id") Long id
    ) {
        item.setId(id);
        return itemService.update(item);
    }
}
