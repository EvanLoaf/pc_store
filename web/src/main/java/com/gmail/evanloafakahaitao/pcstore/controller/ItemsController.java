package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.FileConverter;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.ItemValidator;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.XMLService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.XMLItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/web/items")
public class ItemsController {

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final ItemValidator itemValidator;
    private final FileConverter fileConverter;
    private final XMLItemConverter xmlItemConverter;
    private final XMLService xmlService;

    @Autowired
    public ItemsController(PageProperties pageProperties, ItemService itemService, ItemValidator itemValidator, FileConverter fileConverter, XMLItemConverter xmlItemConverter, XMLService xmlService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.itemValidator = itemValidator;
        this.fileConverter = fileConverter;
        this.xmlItemConverter = xmlItemConverter;
        this.xmlService = xmlService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_items')")
    public String getItems(
            @RequestParam(value = "page", required = false) Integer page,
            ModelMap modelMap
    ) {
        if (page == null) {
            page = 1;
        }
        int startPosition = (page - 1) * pageProperties.getPaginationMaxResults();
        List<ItemDTO> items = itemService.findAll(startPosition, pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_item')")
    public String createItem(
            @ModelAttribute("order") ItemDTO item,
            BindingResult result,
            ModelMap modelMap
    ) {
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return pageProperties.getItemCreatePagePath();
        } else {
            itemService.save(item);
            return pageProperties.getItemsPagePath();
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_item')")
    public String createItemPage(ModelMap modelMap) {
        modelMap.addAttribute("item", new ItemDTO());
        return pageProperties.getItemCreatePagePath();
    }

    @GetMapping(value = "/upload")
    @PreAuthorize("hasAuthority('upload_items')")
    public String uploadItemsPage() {
        return pageProperties.getItemsUploadPagePath();
    }

    @PostMapping(value = "/upload")
    @PreAuthorize("hasAuthority('upload_items')")
    public String uploadItems(
            @RequestParam("file") MultipartFile multipartItems,
            BindingResult result,
            ModelMap modelMap
    ) throws IOException {
        File itemsFile = fileConverter.convert(multipartItems);
        List<ItemXMLDTO> xmlItems = xmlService.getXmlItems(itemsFile, pageProperties.getSchemaFilePath());
        List<ItemDTO> items = xmlItemConverter.toItems(xmlItems);
        for (ItemDTO item : items) {
            BindingResult itemResult = result;
            itemValidator.validate(item, itemResult);
            if (!itemResult.hasErrors()) {
                itemService.save(item);
            }
        }
        return pageProperties.getItemsPagePath();
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('remove_item')")
    public String deleteItem(
            @PathVariable("id") Long id
    ) {
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        itemService.softDelete(item);
        return pageProperties.getItemsPagePath();
    }

    @GetMapping(value = "/{id}/copy")
    @PreAuthorize("hasAuthority('copy_item')")
    public String copyItem(
            @PathVariable("id") Long id
    ) {
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        itemService.copy(item);
        return pageProperties.getItemsPagePath();
    }
}
