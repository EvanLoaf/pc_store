package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUser;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.OrderValidator;
import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/orders")
public class OrdersController {

    private static final Logger logger = LogManager.getLogger(OrdersController.class);

    private final PageProperties pageProperties;
    private final OrderService orderService;
    private final OrderValidator orderValidator;
    private final UserService userService;
    private final ItemService itemService;
    private final PaginationUtil paginationUtil;

    @Autowired
    public OrdersController(
            PageProperties pageProperties,
            OrderService orderService,
            OrderValidator orderValidator,
            UserService userService,
            ItemService itemService,
            PaginationUtil paginationUtil
    ) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.orderValidator = orderValidator;
        this.userService = userService;
        this.itemService = itemService;
        this.paginationUtil = paginationUtil;
    }

    @GetMapping(value = "/self")
    @PreAuthorize("hasAuthority('view_orders_self')")
    public String getOrdersFromUser(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : getOrdersFromUser page " + page);
        Long userId = CurrentUser.getCurrentId();
        SimpleUserDTO user = new SimpleUserDTO();
        user.setId(userId);
        List<SimpleOrderDTO> orders = orderService.findByUserId(user, paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('view_orders_all')")
    public String getOrders(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : getOrders page " + page);
        List<OrderDTO> orders = orderService.findAll(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("statusEnum", OrderStatusEnum.values());
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrder(
            @ModelAttribute("order") DataOrderDTO order,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : createOrder");
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setVendorCode(order.getItem().getVendorCode());
            ItemDTO item = itemService.findByVendorCode(itemDTO);
            modelMap.addAttribute("item", item);
            UserDTO userDTO = new UserDTO();
            Long userId = CurrentUser.getCurrentId();
            userDTO.setId(userId);
            UserDTO user = userService.findById(userDTO);
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("order", order);
            return pageProperties.getOrderCreatePagePath();
        } else {
            orderService.save(order);
            return "redirect:/web/orders/self";
        }
    }

    @GetMapping(value = "/item/{id}/create")
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrderPage(
            @PathVariable("id") Long itemId,
            ModelMap modelMap
    ) {
        logger.debug("Executing Orders Controller method : createOrderPage");
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemId);
        ItemDTO item = itemService.findById(itemDTO);
        modelMap.addAttribute("item", item);
        UserDTO userDTO = new UserDTO();
        Long userId = CurrentUser.getCurrentId();
        userDTO.setId(userId);
        UserDTO user = userService.findById(userDTO);
        modelMap.addAttribute("user", user);
        DataOrderDTO order = new DataOrderDTO();
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
        simpleUserDTO.setEmail(user.getEmail());
        order.setUser(simpleUserDTO);
        SimpleItemDTO simpleItemDTO = new SimpleItemDTO();
        simpleItemDTO.setVendorCode(item.getVendorCode());
        order.setItem(simpleItemDTO);
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
        return "redirect:/web/orders/all" + "?status=true";
    }

    @GetMapping(value = "/{uuid}/delete")
    @PreAuthorize("hasAuthority('delete_order_self')")
    public String deleteOrder(
            @PathVariable("uuid") String uuid
    ) {
        logger.debug("Executing Orders Controller method : deleteOrder with uuid " + uuid);
        SimpleOrderDTO order = new SimpleOrderDTO();
        order.setUuid(uuid);
        orderService.deleteByUuid(order);
        return "redirect:/web/orders/self";
    }
}
