package com.soshaw.wsdlfirstserversoap;
import cxf.com.soshaw.ws.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CustomerOrdersWsImpl  implements CustomerOrdersPortType {
    HashMap<Integer,List<Order>>customerOrder=new HashMap<Integer, List<Order>>();
    int currId;
    public CustomerOrdersWsImpl(){
        currId=0;
        init();

    }

    void init(){
        List<Order>orders=new ArrayList<>();
        Order order=new Order();
        order.setOrderId(123);
        Product prod=new Product();
        prod.setProductId(12);
        prod.setProductDescription("Product of id 12");
        prod.setProductQuantity(34);
        order.getProduct().add(prod);
        orders.add(order);
        customerOrder.put(Integer.valueOf(++currId),orders);
    }
    @Override
    public GetOrderResponse getOrders(GetOrderRequest request) {
        Integer customerId=request.getCustomerId();
        GetOrderResponse response=new GetOrderResponse();
        List<Order>order=customerOrder.get(customerId);
        response.getOrderList().addAll(order);
        return response;
    }

    @Override
    public CreateOrderResponse createOrders(CreateOrderRequest request) {
        Integer customerId=request.getCustomerId();
        Order order1=request.getOrderList();
        List<Order>orders=customerOrder.get(customerId);
        if(orders==null)orders=new ArrayList<>();
        orders.add(order1);
        CreateOrderResponse response=new CreateOrderResponse();
        response.setResult(true);
        return response;
    }
}
