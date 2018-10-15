package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.model.ItemDiscountData;
import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.FileConverter;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.ItemDiscountDataValidator;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.ItemValidator;
import com.gmail.evanloafakahaitao.pcstore.service.DiscountService;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.XMLService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
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
    private final PaginationUtil paginationUtil;
    private final DiscountService discountService;
    private final ItemDiscountDataValidator itemDiscountDataValidator;

    @Autowired
    public ItemsController(
            PageProperties pageProperties,
            ItemService itemService,
            ItemValidator itemValidator,
            FileConverter fileConverter,
            XMLItemConverter xmlItemConverter,
            XMLService xmlService,
            PaginationUtil paginationUtil,
            DiscountService discountService,
            ItemDiscountDataValidator itemDiscountDataValidator
    ) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.itemValidator = itemValidator;
        this.fileConverter = fileConverter;
        this.xmlItemConverter = xmlItemConverter;
        this.xmlService = xmlService;
        this.paginationUtil = paginationUtil;
        this.discountService = discountService;
        this.itemDiscountDataValidator = itemDiscountDataValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_items')")
    public String getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        List<ItemDTO> items = itemService.findAllNotDeleted(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("items", items);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(itemService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getStartPosition(page) + 1);
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getItemsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_item')")
    public String createItem(
            @ModelAttribute("item") ItemDTO item,
            BindingResult result,
            ModelMap modelMap
    ) {
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return pageProperties.getItemCreatePagePath();
        } else {
            itemService.save(item);
            return "redirect:/web/items";
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
            /*BindingResult result,*/
            ModelMap modelMap
    ) throws IOException {
        File itemsFile = fileConverter.convert(multipartItems);
        List<ItemXMLDTO> xmlItems = xmlService.getXmlItems(itemsFile, pageProperties.getSchemaFilePath());
        List<ItemDTO> items = xmlItemConverter.toItems(xmlItems);
        for (ItemDTO item : items) {
            /*BindingResult itemResult = result;
            itemValidator.validate(item, itemResult);
            if (!itemResult.hasErrors()) {
                itemService.save(item);
            }*/
            itemService.save(item);
        }
        return "redirect:/web/items";
    }

    /*@GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('remove_item')")
    public String deleteItem(
            @PathVariable("id") Long id
    ) {
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        itemService.softDelete(item);
        return pageProperties.getItemsPagePath();
    }*/

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('remove_item')")
    public String deleteItem(
            @RequestParam("ids") Long[] ids
    ) {
        for (Long id : ids) {
            SimpleItemDTO item = new SimpleItemDTO();
            item.setId(id);
            itemService.softDelete(item);
        }
        return "redirect:/web/items";
    }

    @GetMapping(value = "/{id}/copy")
    @PreAuthorize("hasAuthority('copy_item')")
    public String copyItem(
            @PathVariable("id") Long id
    ) {
        SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);
        itemService.copy(item);
        return "redirect:/web/items";
    }

    @PostMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_item')")
    public String updateItemsDiscounts(
            @ModelAttribute("discountData") ItemDiscountData discountData,
            BindingResult result,
            ModelMap modelMap
    ) {
        itemDiscountDataValidator.validate(discountData, result);
        if (result.hasErrors()) {
            List<DiscountDTO> discounts = discountService.findAll();
            modelMap.addAttribute("discounts", discounts);
            modelMap.addAttribute("discountData", discountData);
            return pageProperties.getItemsSetDiscountPagePath();
        } else {
            itemService.updateDiscountAll(
                    discountData.getDiscountId(),
                    discountData.getMinPriceRange(),
                    discountData.getMaxPriceRange()
            );
            return "redirect:/web/items" + "?itemdiscounts=true";
        }

    }

    @GetMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_item')")
    public String updateItemsDiscountsPage(
            ModelMap modelMap
    ) {
        List<DiscountDTO> discounts = discountService.findAll();
        modelMap.addAttribute("discounts", discounts);
        modelMap.addAttribute("discountData", new ItemDiscountData());
        return pageProperties.getItemsSetDiscountPagePath();
    }
}