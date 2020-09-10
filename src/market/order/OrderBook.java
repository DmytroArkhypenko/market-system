package market.order;

import java.util.List;

public class OrderBook {

    private List<Order> orders;

    public OrderBook() {
    }

    public OrderBook(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void add(Order order) {
        orders.add(order);

    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "orders=" + orders +
                '}';
    }
}
