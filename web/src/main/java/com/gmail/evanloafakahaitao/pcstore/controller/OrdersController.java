package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
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

import java.util.List;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders")
public class OrdersController {

    private static final Logger logger = LogManager.getLogger(OrdersController.class);

    private final PageProperties pageProperties;
    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;
    private final PaginationUtil paginationUtil;
    private final Validator orderValidator;

    @Autowired
    public OrdersController(
            PageProperties pageProperties,
            OrderService orderService,
            UserService userService,
            ItemService itemService,
            PaginationUtil paginationUtil,
            @Qualifier("orderValidator") Validator orderValidator
    ) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.userService = userService;
        this.itemService = itemService;
        this.paginationUtil = paginationUtil;
        this.orderValidator = orderValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_orders_self')")
    public String getOrdersFromUser(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : getOrdersFromUser page " + page);
        //TODO single method with /all - cin service check auth and then find for curr id or all
        List<SimpleOrderDTO> orders = orderService.findByCurrentUserId(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("orders", orders);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(userService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('view_orders_all')")
    public String getOrders(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : getOrders page " + page);
        List<OrderDTO> orders = orderService.findAll(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("statusEnum", OrderStatusEnum.values());
        modelMap.addAttribute("orders", orders);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(userService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getOrdersPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrder(
            @ModelAttribute("order") CreateOrderDTO order,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : createOrder");
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            ItemDTO item = itemService.findByVendorCode(order.getItemVendorCode());
            modelMap.addAttribute("item", item);
            UserDTO user = userService.findByCurrentId();
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("order", order);
            return pageProperties.getOrderCreatePagePath();
        } else {
            orderService.save(order);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders";
        }
    }

    @GetMapping(value = "/create/items/{id}")
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrderPage(
            @PathVariable("id") Long itemId,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : createOrderPage");
        ItemDTO item = itemService.findById(itemId);
        modelMap.addAttribute("item", item);
        UserDTO user = userService.findByCurrentId();
        modelMap.addAttribute("user", user);
        CreateOrderDTO order = new CreateOrderDTO();
        order.setUserEmail(user.getEmail());
        order.setItemVendorCode(item.getVendorCode());
        modelMap.addAttribute("order", order);
        return pageProperties.getOrderCreatePagePath();
    }

    @PostMapping(value = "/{uuid}")
    @PreAuthorize("hasAuthority('update_order_status')")
    public String updateOrder(
            @PathVariable("uuid") String uuid,
            @RequestParam("status") OrderStatusEnum status
    ) {
        logger.debug("Executing Orders Controller method : updateOrder to status " + status);
        SimpleOrderDTO order = new SimpleOrderDTO();
        order.setUuid(uuid);
        order.setStatus(status);
        orderService.update(order);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders/admin" + "?status=true";
    }

    @GetMapping(value = "/{uuid}/delete")
    @PreAuthorize("hasAuthority('delete_order_self')")
    public String deleteOrder(
            @PathVariable("uuid") String uuid
    ) {
        logger.debug("Executing Orders Controller method : deleteOrder with uuid " + uuid);
        orderService.deleteByUuid(uuid);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/orders";
    }
}
