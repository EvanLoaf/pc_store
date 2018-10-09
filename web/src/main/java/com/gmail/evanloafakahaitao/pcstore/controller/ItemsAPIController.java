package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsAPIController {

    private final ItemService itemService;
    private final PageProperties pageProperties;

    @Autowired
    public ItemsAPIController(ItemService itemService, PageProperties pageProperties) {
        this.itemService = itemService;
        this.pageProperties = pageProperties;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('api_admin_basic_permission')")
    public List<ItemDTO> getItems() {
        List<ItemDTO> items = itemService.findAll(0, pageProperties.getPaginationMaxResults());
        return items;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('api_admin_basic_permission')")
    public ItemDTO saveItem(@RequestBody ItemDTO item) {
        ItemDTO itemSaved = itemService.save(item);
        return itemSaved;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('api_admin_basic_permission')")
    public ItemDTO getItem(@PathVariable(name = "id") Long id) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);
        ItemDTO item = itemService.findById(itemDTO);
        return item;
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('api_admin_basic_permission')")
    public SimpleItemDTO deleteItem(@PathVariable(name = "id") Long id) {
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        SimpleItemDTO itemDeleted = itemService.softDelete(item);
        return itemDeleted;
    }
}
