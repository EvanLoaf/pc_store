package com.gmail.evanloafakahaitao.pcstore.controller.api;

import com.gmail.evanloafakahaitao.pcstore.controller.model.APIResponseEntity;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.ResponseProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.api.ItemAPIValidator;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(WebProperties.REST_API_ENTRY_POINT_PREFIX + "/items")
public class ItemsAPIController {

    private static final Logger logger = LogManager.getLogger(ItemsAPIController.class);

    private final ItemService itemService;
    private final PageProperties pageProperties;
    private final PaginationUtil paginationUtil;
    private final ItemAPIValidator itemAPIValidator;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public ItemsAPIController(
            ItemService itemService,
            PageProperties pageProperties,
            PaginationUtil paginationUtil,
            ItemAPIValidator itemAPIValidator,
            FieldTrimmer fieldTrimmer
    ) {
        this.itemService = itemService;
        this.pageProperties = pageProperties;
        this.paginationUtil = paginationUtil;
        this.itemAPIValidator = itemAPIValidator;
        this.fieldTrimmer = fieldTrimmer;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_items_api')")
    public List<ItemDTO> getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        logger.debug("Executing Item API Controller method : getItems");
        return itemService.findAllNotDeleted(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_item_api')")
    public ResponseEntity<APIResponseEntity> createItem(
            @RequestBody ItemDTO item
    ) {
        logger.debug("Executing Item API Controller method : createItem");
        item = fieldTrimmer.trim(item);
        Set<String> errors = itemAPIValidator.validate(item);
        APIResponseEntity response = new APIResponseEntity();
        if (!errors.isEmpty()) {
            response.setMessage(ResponseProperties.ITEM_NOT_CREATED);
            response.setErrors(errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            itemService.save(item);
            response.setMessage(ResponseProperties.ITEM_CREATED);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('view_items_api')")
    public ItemDTO getItem(
            @PathVariable(name = "id") Long id
    ) {
        logger.debug("Executing Item API Controller method : getItem with id " + id);
        return itemService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('delete_item_api')")
    public ResponseEntity<APIResponseEntity> deleteItem(
            @PathVariable(name = "id") Long id
    ) {
        logger.debug("Executing Item API Controller method : deleteItem with id " + id);
        itemService.deleteByOrdersCount(id);
        APIResponseEntity response = new APIResponseEntity();
        response.setMessage(ResponseProperties.ITEM_DELETED);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_item_api')")
    public ResponseEntity<APIResponseEntity> updateItem(
            @RequestBody ItemDTO item,
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing Item API Controller method : updateItem with id " + id);
        item = fieldTrimmer.trim(item);
        item.setId(id);
        Set<String> errors = itemAPIValidator.validate(item);
        APIResponseEntity response = new APIResponseEntity();
        if (!errors.isEmpty()) {
            response.setMessage(ResponseProperties.ITEM_NOT_UPDATED);
            response.setErrors(errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            itemService.update(item);
            response.setMessage(ResponseProperties.ITEM_UPDATED);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }
}
