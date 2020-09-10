package market.requests;

import market.order.Order;
import market.order.OrderBook;
import market.order.OrderType;

import java.io.BufferedWriter;

public class UpdateRequest extends Request {

    String secondParameter;
    String thirdParameter;
    String fourthParamater;

    public UpdateRequest() {
    }

    public UpdateRequest(String secondParameter, String thirdParameter, String fourthParameter) {
        super();
        this.secondParameter = secondParameter;
        this.thirdParameter = thirdParameter;
        this.fourthParamater = fourthParameter;
    }

    @Override
    public void executeRequest(Request request, OrderBook orderBook, BufferedWriter writer) {
        UpdateRequest updateRequest = (UpdateRequest) request;
        Integer price = Integer.parseInt(updateRequest.getSecondParameter());
        Integer size = Integer.parseInt(updateRequest.getThirdParameter());
        OrderType orderType = OrderType.valueOf(updateRequest.getFourthParamater());

        Order result =
                orderBook.getOrders().stream().filter(order -> price.equals(order.getPrice()) &&
                        orderType.equals(order.getOrderType()))
                        .findFirst()
                        .orElse(null);

        if (result == null) {
            Order newlyCreatedOrder = new Order(price, size, orderType);

            orderBook.getOrders().add(newlyCreatedOrder);
        } else {
            int possition = orderBook.getOrders().indexOf(result);
            result.setSize(result.getSize() + size);
            orderBook.getOrders().set(possition, result);
        }
    }

    public String getSecondParameter() {
        return secondParameter;
    }

    public void setSecondParameter(String secondParameter) {
        this.secondParameter = secondParameter;
    }

    public String getThirdParameter() {
        return thirdParameter;
    }

    public void setThirdParameter(String thirdParameter) {
        this.thirdParameter = thirdParameter;
    }

    public String getFourthParamater() {
        return fourthParamater;
    }

    public void setFourthParamater(String fourthParamater) {
        this.fourthParamater = fourthParamater;
    }
}
