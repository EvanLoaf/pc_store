package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.TargetDeterminer;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUserExtractor;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.OrderValidator;
import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
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

    private final PageProperties pageProperties;
    private final OrderService orderService;
    private final OrderValidator orderValidator;
    private final TargetDeterminer targetDeterminer;
    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public OrdersController(
            PageProperties pageProperties,
            OrderService orderService,
            OrderValidator orderValidator,
            TargetDeterminer targetDeterminer,
            UserService userService,
            ItemService itemService
    ) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.orderValidator = orderValidator;
        this.targetDeterminer = targetDeterminer;
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('view_orders_all', 'view_orders_self')")
    public String getOrders(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        int startPosition = (page - 1) * pageProperties.getPaginationMaxResults();
        String targetMethod = targetDeterminer.methodToFindOrders();
        if (targetMethod.equals("all")) {
            List<OrderDTO> orders = orderService.findAll(startPosition, pageProperties.getPaginationMaxResults());
            modelMap.addAttribute("statusEnum", OrderStatusEnum.values());
            modelMap.addAttribute("orders", orders);
        } else if (targetMethod.equals("self")) {
            Long userId = CurrentUserExtractor.getCurrentId();
            SimpleUserDTO user = new SimpleUserDTO();
            user.setId(userId);
            List<SimpleOrderDTO> orders = orderService.findByUserId(user, startPosition, pageProperties.getPaginationMaxResults());
            modelMap.addAttribute("orders", orders);
        }
        return pageProperties.getOrdersPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrder(
            @ModelAttribute("order") DataOrderDTO order,
            BindingResult result,
            ModelMap modelMap
    ) {
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setVendorCode(order.getItem().getVendorCode());
            ItemDTO item = itemService.findByVendorCode(itemDTO);
            modelMap.addAttribute("item", item);
            UserDTO userDTO = new UserDTO();
            Long userId = CurrentUserExtractor.getCurrentId();
            userDTO.setId(userId);
            UserDTO user = userService.findById(userDTO);
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("order", order);
            return pageProperties.getOrderCreatePagePath();
        } else {
            orderService.save(order);
            return "redirect:/web/orders";
        }
    }

    @GetMapping(value = "/item/{id}/create")
    @PreAuthorize("hasAuthority('create_order')")
    public String createOrderPage(
            @PathVariable("id") Long itemId,
            ModelMap modelMap
    ) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemId);
        ItemDTO item = itemService.findById(itemDTO);
        modelMap.addAttribute("item", item);
        UserDTO userDTO = new UserDTO();
        Long userId = CurrentUserExtractor.getCurrentId();
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
        SimpleOrderDTO order = new SimpleOrderDTO();
        order.setUuid(uuid);
        order.setStatus(status);
        orderService.update(order);
        return "redirect:/web/orders";
    }

    /*@GetMapping(value = "/{uuid}/update")
    @PreAuthorize("hasAuthority('update_order_status')")
    public String updateOrderPage(
            @PathVariable("uuid") String uuid,
            ModelMap modelMap
    ) {
        DataOrderDTO orderDTO = new DataOrderDTO();
        orderDTO.setUuid(uuid);
        SimpleOrderDTO order = orderService.findByUuid(orderDTO);
        modelMap.addAttribute("order", order);
        return pageProperties.g
    }*/

    @GetMapping(value = "/{uuid}/delete")
    @PreAuthorize("hasAuthority('delete_order_self')")
    public String deleteOrder(
            @PathVariable("uuid") String uuid
    ) {
        SimpleOrderDTO order = new SimpleOrderDTO();
        order.setUuid(uuid);
        orderService.deleteByUuid(order);
        return "redirect:/web/orders";
    }
}
