package market.order;


public class Order {

    private Integer price;
    private Integer size;
    private OrderType orderType;

    public Order() {
    }

    public Order(Integer price) {
        this.price = price;
        size = 0;
        orderType = OrderType.spread;
    }

    public Order(Integer price, Integer size, OrderType orderType) {
        this.price = price;
        this.size = size;
        this.orderType = orderType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
