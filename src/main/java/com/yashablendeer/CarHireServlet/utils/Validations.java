package com.yashablendeer.CarHireServlet.utils;

import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.model.Order;
import com.yashablendeer.CarHireServlet.model.Status;
import com.yashablendeer.CarHireServlet.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Validations {
    private static OrderService orderService = new OrderService();

    /**
    * @return true if date is not available for current car
    *
    */
    public static boolean checkDateAvailability(Car car, LocalDateTime start, LocalDateTime end) {


        List<LocalDateTime> startDates = orderService.findAll().stream()
                .filter(order -> order.getCar().equals(car))
                .filter(order -> order.getStatus()
                        .equals(Status.ACCEPTED))
                .map(Order::getStartTime)
                .collect(Collectors.toList());
        if (startDates.size() == 0) {
            return false;
        }
        List<LocalDateTime> endDates = orderService.findAll().stream()
                .filter(order -> order.getCar().equals(car))
                .filter(order -> order.getStatus()
                        .equals(Status.ACCEPTED))
                .map(Order::getEndTime)
                .collect(Collectors.toList());
        Map<LocalDateTime, LocalDateTime> map = IntStream.range(0, startDates.size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> startDates.get(i), i -> endDates.get(i)));

        boolean notValidDate =
                map.entrySet().stream()
                        .anyMatch(entry -> entry.getKey().isBefore(end) && entry.getValue().isAfter(start));

        return notValidDate;
    }
}