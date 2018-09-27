package com.gmail.evanloafakahaitao.pcstore.web;

import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.pcstore.service.*;
import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
import com.gmail.evanloafakahaitao.pcstore.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TestMain {

    public static void main(String[] args) {

        Random random = new Random();
        Logger logger = LogManager.getLogger(TestMain.class);

        ItemService itemService = new ItemServiceImpl();
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        DiscountService discountService = new DiscountServiceImpl();

        /**
         * Task 1 - created tables, edited entities, converters, methods etc
         */

        /**
         * Task 2
         */
        for (int i = 0; i < 30; i++) {
            ItemDTO itemDTO = ItemDTO.newBuilder()
                    .withDescription("Item" + (i + 1))
                    .withName("Name" + (i + 1))
                    .withPrice(BigDecimal.valueOf(random.nextInt(401) + 100))
                    .withVendorCode("vendor code " + (i + 1))
                    .build();
            ItemDTO itemDTOsaved = itemService.save(itemDTO);
            logger.info("saved item id: " + itemDTOsaved.getId());
        }

        /**
         * Task 3
         */

        DiscountDTO discountDTOTenPercent = DiscountDTO.newBuilder()
                .withName("ten")
                .withPercent(10)
                .withFinishDate(LocalDateTime.now())
                .build();

        DiscountDTO discountDTOTwentyPercent = DiscountDTO.newBuilder()
                .withName("twenty")
                .withPercent(20)
                .withFinishDate(LocalDateTime.now())
                .build();

        DiscountDTO discountDTOThirtyPercent = DiscountDTO.newBuilder()
                .withName("thirty")
                .withPercent(30)
                .withFinishDate(LocalDateTime.now())
                .build();

        DiscountDTO disc1 = discountService.save(discountDTOTenPercent);
        logger.info("disc1 id: " + disc1.getId());

        DiscountDTO disc2 = discountService.save(discountDTOTwentyPercent);
        logger.info("disc2 id: " + disc2.getId());

        DiscountDTO disc3 = discountService.save(discountDTOThirtyPercent);
        logger.info("disc3 id: " + disc3.getId());

        /**
         * Task 4
         */

        List<ItemDTO> itemDTOSCheap = itemService.findInPriceRange(BigDecimal.valueOf(200), BigDecimal.valueOf(299), null, null);
        for (ItemDTO itemDTO : itemDTOSCheap) {
            logger.info("prices betw 200-299: " + itemDTO.getPrice());
        }
        DiscountDTO discPercentTen = DiscountDTO.newBuilder()
                .withPercent(10)
                .build();
        for (ItemDTO dto : itemDTOSCheap) {
            dto.getDiscounts().clear();
            dto.getDiscounts().add(discPercentTen);
            ItemDTO itemDTOsaved = itemService.updateDiscount(dto);
            logger.info("saved item :: price - " + itemDTOsaved.getPrice() + " disc - " + itemDTOsaved.getDiscounts().iterator().next().getPercent());
        }

        List<ItemDTO> itemDTOSMedium = itemService.findInPriceRange(BigDecimal.valueOf(300), BigDecimal.valueOf(399), null, null);
        for (ItemDTO itemDTO : itemDTOSMedium) {
            logger.info("prices betw 300-399: " + itemDTO.getPrice());
        }
        DiscountDTO discPercentTwenty = DiscountDTO.newBuilder()
                .withPercent(20)
                .build();
        for (ItemDTO dto : itemDTOSMedium) {
            dto.getDiscounts().clear();
            dto.getDiscounts().add(discPercentTwenty);
            ItemDTO itemDTOsaved = itemService.updateDiscount(dto);
            logger.info("saved item :: price - " + itemDTOsaved.getPrice() + " disc - " + itemDTOsaved.getDiscounts().iterator().next().getPercent());
        }

        List<ItemDTO> itemDTOSExpensive = itemService.findInPriceRange(BigDecimal.valueOf(400), BigDecimal.valueOf(499), null, null);
        for (ItemDTO itemDTO : itemDTOSExpensive) {
            logger.info("prices betw 400-499: " + itemDTO.getPrice());
        }
        DiscountDTO discPercentThirty = DiscountDTO.newBuilder()
                .withPercent(30)
                .build();
        DiscountDTO discountDTOThirty = discountService.findByPercent(discPercentThirty);
        for (ItemDTO dto : itemDTOSExpensive) {
            dto.getDiscounts().clear();
            dto.getDiscounts().add(discPercentThirty);
            ItemDTO itemDTOsaved = itemService.updateDiscount(dto);
            logger.info("saved item :: price - " + itemDTOsaved.getPrice() + " disc - " + itemDTOsaved.getDiscounts().iterator().next().getPercent());
        }

        /**
         * Task 5 - created method findByDiscount in ItemDaoImpl, you can specify the discount size through
         * the method argument. It will return List of items with the specified size of discount.
         * If the input is 0 or null, the method will return List of items for which there are no discounts at all.
         */

        DiscountDTO discountPercentNull = DiscountDTO.newBuilder()
                .withPercent(null)
                .build();
        List<ItemDTO> itemDTOSwithNullDiscount = itemService.findByDiscount(discountPercentNull);
        logger.info("Amount of items with " + discountPercentNull.getPercent() + " discount - " + itemDTOSwithNullDiscount.size());
        for (ItemDTO itemDTO : itemDTOSwithNullDiscount) {
            logger.info("item with " + discountPercentNull.getPercent() + " discount :: price - " + itemDTO.getPrice());
        }

        DiscountDTO discountPercentThirty = DiscountDTO.newBuilder()
                .withPercent(30)
                .build();
        List<ItemDTO> itemDTOSwithThirtyDiscount = itemService.findByDiscount(discountPercentThirty);
        logger.info("Amount of items with " + discountPercentThirty.getPercent() + " discount - " + itemDTOSwithThirtyDiscount.size());
        for (ItemDTO itemDTO : itemDTOSwithThirtyDiscount) {
            logger.info("item with " + discountPercentThirty.getPercent() + " discount :: price - " + itemDTO.getPrice());
        }

        /**
         * Task 6
         */

        UserDTO userDTO = UserDTO.newBuilder()
                .withEmail("my@email.com")
                .withFirstName("first")
                .withLastName("last")
                .withPassword("12345")
                .withProfile(
                        ProfileDTO.newBuilder()
                                .withAddress("address")
                                .withPhoneNumber("phone")
                                .build()
                )
                .withDiscount(
                        new DiscountDTO()
                )
                .build();

        UserDTO userDTOsaved = userService.save(userDTO);
        logger.info("saved user id: " + userDTOsaved.getId() + " discount : " + userDTOsaved.getDiscount().getPercent() + "%");

        /**
         * Task 7
         */

        UserDTO userDTOupdateData = UserDTO.newBuilder()
                .withEmail(userDTOsaved.getEmail())
                .withDiscount(
                        DiscountDTO.newBuilder()
                                .withPercent(20)
                                .build()
                )
                .build();
        UserDTO userDTOupdated = userService.update(userDTOupdateData);
        logger.info("updated user and gave him " + userDTOupdated.getDiscount().getPercent() + "% discount");


        /**
         * Task 8 - I could first search for items in price range and then with Java's help pick random ones,
         * but I decided it would be better to count items in given price range first and then just pick 4 of them
         * starting from random position.
         *
         * Now I think by "Произвольный" you didn't mean "Random", so I made this method a little too complicated...
         * Anyways, just in case, I'll send it like this. I can alter it to more simple version at any time.
         *
         * Also to make it pure random I could use 'order by rand()', but it's not very good in some cases and would
         * require a separate method since I want to order items by their price
         *
         * P.S. if we take a random Item every time to create Order, we might hit the same item twice. This would cause
         * an Error since our implementation of Order with @EmbeddedId restricts users from ordering the same item twice
         */

        Long countOfItems = itemService.findCountInPriceRange(BigDecimal.valueOf(250), BigDecimal.valueOf(450));
        logger.info("Items in 250-450 price range" + countOfItems);

        /*
               +1 since random can return 0 and -3 since I wanna retrieve 4 items, so, for example, if I got 20 items
               and need 4 of them, I can set StartPosition from 1 to 17 to actually get 4. If it becomes 18, theres only
               Item 18,19,20 left.
         */
        List<ItemDTO> items = itemService.findInPriceRange(BigDecimal.valueOf(250), BigDecimal.valueOf(450), random.nextInt(countOfItems.intValue() - 3) + 1, 4);
        logger.info("items in price range taken : " + items.size());

        /*
              Less than 4 orders will be created if theres less than 4 items in 250-450 price range
              Cant create orders with similar items for single users with our implementation
         */

        for (int i = 0; i < items.size(); i++) {
            OrderDTO orderDTO = OrderDTO.newBuilder()
                    .withCreated(LocalDateTime.now())
                    .withStatus(OrderStatusEnum.NEW)
                    .withUuid(UUID.randomUUID().toString())
                    .withQuantity(countOfItems.intValue())
                    .withUser(OrderUserDTO.newBuilder()
                            .withEmail("my@email.com")
                            .withProfile(
                                    ProfileDTO.newBuilder()
                                            .withAddress(userDTOsaved.getProfile().getAddress())
                                            .withPhoneNumber(userDTOsaved.getProfile().getPhoneNumber())
                                            .build()
                            )
                            .build())
                    .withItem(items.get(i))
                    .build();

            SimpleOrderDTO simpleOrderDTO = orderService.save(orderDTO);

            logger.info("saved order: " + simpleOrderDTO.getUuid());
        }

        /**
         * Task 9 - Not 100% sure about final price formula, I guess it doesn't matter now
         */

        List<OrderDTO> orders = orderService.findAll();
        logger.info("Order info :: User_name -- Item_name -- Quantity -- Normal price -- Final price (Price with item discount + user discount)");
        for (OrderDTO order : orders) {
            logger.info(
                    String.format(
                            "%s -- %s -- %d -- %.2f -- %.2f",
                            order.getUser().getName(),
                            order.getItem().getName(),
                            order.getQuantity(),
                            order.getItem().getPrice(),
                            order.getItem().getPrice().multiply(BigDecimal.valueOf(100 - order.getItem().getDiscounts().iterator().next().getPercent()).divide(BigDecimal.valueOf(100L))).multiply(BigDecimal.valueOf(100 - order.getUser().getDiscount().getPercent()).divide(BigDecimal.valueOf(100L)))
                    )
            );
        }

    }
}
