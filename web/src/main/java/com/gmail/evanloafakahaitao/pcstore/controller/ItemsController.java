package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.model.ItemDiscountData;
import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.service.util.XMLItemSaverUtil;
import com.gmail.evanloafakahaitao.pcstore.service.xml.util.FileConverter;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items")
public class ItemsController {

    private static final Logger logger = LogManager.getLogger(ItemsController.class);

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final XMLService xmlService;
    private final PaginationUtil paginationUtil;
    private final DiscountService discountService;
    private final XMLItemSaverUtil xmlItemSaverUtil;
    private final Validator itemValidator;
    private final Validator itemDiscountDataValidator;

    @Autowired
    public ItemsController(
            PageProperties pageProperties,
            ItemService itemService,
            XMLService xmlService,
            PaginationUtil paginationUtil,
            DiscountService discountService,
            XMLItemSaverUtil xmlItemSaverUtil,
            @Qualifier("itemValidator") Validator itemValidator,
            @Qualifier("itemDiscountDataValidator") Validator itemDiscountDataValidator
    ) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.xmlService = xmlService;
        this.paginationUtil = paginationUtil;
        this.discountService = discountService;
        this.xmlItemSaverUtil = xmlItemSaverUtil;
        this.itemValidator = itemValidator;
        this.itemDiscountDataValidator = itemDiscountDataValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_items')")
    public String getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method : getItems");
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
        logger.debug("Executing Item Controller method : createItem");
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return pageProperties.getItemCreatePagePath();
        } else {
            itemService.save(item);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_item')")
    public String createItemPage(ModelMap modelMap) {
        logger.debug("Executing Item Controller method : createItemPage");
        modelMap.addAttribute("item", new ItemDTO());
        return pageProperties.getItemCreatePagePath();
    }

    @GetMapping(value = "/upload")
    @PreAuthorize("hasAuthority('upload_items')")
    public String uploadItemsPage() {
        logger.debug("Executing Item Controller method : uploadItemsPage");
        return pageProperties.getItemsUploadPagePath();
    }

    @PostMapping(value = "/upload")
    @PreAuthorize("hasAuthority('upload_items')")
    public String uploadItems(
            @RequestParam("file") MultipartFile multipartItems,
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method : uploadItems");
        List<ItemDTO> items = xmlService.getUploadedXmlItems(multipartItems);
        List<String> vendorCodeDuplicates = xmlItemSaverUtil.saveUploadedItems(items);
        if (!vendorCodeDuplicates.isEmpty()) {
            modelMap.addAttribute("duplicates", vendorCodeDuplicates);
            return pageProperties.getItemsUploadPagePath();
        } else {
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items";
        }
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('remove_item')")
    public String deleteItems(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing Item Controller method : deleteItems with id's " + Arrays.toString(ids));
        for (Long id : ids) {
            /*SimpleItemDTO item = new SimpleItemDTO();
            item.setId(id);*/
            itemService.softDelete(id);
        }
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items";
    }

    @GetMapping(value = "/{id}/copy")
    @PreAuthorize("hasAuthority('copy_item')")
    public String copyItem(
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing Item Controller method : copyItem with id " + id);
        /*SimpleItemDTO item = new SimpleItemDTO();
        item.setId(id);*/
        itemService.copy(id);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items";
    }

    @PostMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_item')")
    public String updateItemsDiscount(
            @ModelAttribute("discountData") ItemDiscountData discountData,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method : updateItemsDiscount with discount id : " + discountData.getDiscountId());
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
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/items" + "?itemdiscounts=true";
        }

    }

    @GetMapping(value = "/discounts/update")
    @PreAuthorize("hasAuthority('update_discount_item')")
    public String updateItemsDiscountPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing Item Controller method : updateItemsDiscountPage");
        List<DiscountDTO> discounts = discountService.findAll();
        modelMap.addAttribute("discounts", discounts);
        modelMap.addAttribute("discountData", new ItemDiscountData());
        return pageProperties.getItemsSetDiscountPagePath();
    }
}
