package com.example.restaurantservice.exception.not_found;

public class OrderNotFoundException extends NotFoundException{
    public OrderNotFoundException(String message) {
        super(message);
    }
    public OrderNotFoundException(Long orderId){
        super("Order with id " + orderId + " not found");
    }
}
