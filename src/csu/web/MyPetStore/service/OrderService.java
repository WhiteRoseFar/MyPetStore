package csu.web.MyPetStore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csu.web.MyPetStore.domain.Item;
import csu.web.MyPetStore.domain.LineItem;
import csu.web.MyPetStore.domain.Order;
import csu.web.MyPetStore.domain.Sequence;
import csu.web.MyPetStore.persistence.ItemDao;
import csu.web.MyPetStore.persistence.LineItemDao;
import csu.web.MyPetStore.persistence.OrderDao;
import csu.web.MyPetStore.persistence.impl.ItemDaoImpl;
import csu.web.MyPetStore.persistence.impl.LineItemDaoImpl;
import csu.web.MyPetStore.persistence.impl.OrderDaoImpl;
import csu.web.MyPetStore.persistence.impl.SequenceDaoImpl;


public class OrderService {

    private ItemDao itemDao;
    private OrderDao orderDao;
    private csu.web.MyPetStore.persistence.SequenceDao sequenceDao;
    private LineItemDao lineItemDao;

    public OrderService(){
        itemDao = new ItemDaoImpl();
        orderDao = new OrderDaoImpl();
        sequenceDao= new SequenceDaoImpl();
        lineItemDao = new LineItemDaoImpl();
    }

    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for(int i=0;i<order.getLineItems().size();i++)
        {
            LineItem lineItem=order.getLineItems().get(i);
            String itemId=lineItem.getItemId();
            Integer increment=lineItem.getQuantity();
            Map<String,Object>param=new HashMap<>(2);
            param.put("itemId",itemId);
            param.put("increment",increment);
            itemDao.updateInventoryQuantity(param);
        }
        orderDao.insertOrder(order);

        for(int i=0;i<order.getLineItems().size();i++)
        {
            LineItem lineItem=order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDao.insertLineItem(lineItem);
        }
    }

    public Order getOrder(int orderId) {
        Order order = orderDao.getOrder(orderId);
        order.setLineItems(lineItemDao.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemDao.getItem(lineItem.getItemId());
            item.setQuantity(itemDao.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDao.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = sequenceDao.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDao.updateSequence(parameterObject);
        return sequence.getNextId();
    }

}