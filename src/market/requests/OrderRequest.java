package market.requests;

import market.order.Order;
import market.order.OrderBook;

import java.io.BufferedWriter;

import static market.requests.UtilityMethods.bestAsk;
import static market.requests.UtilityMethods.bestBid;

public class OrderRequest extends Request {

    String secondParameter;
    String thirdParameter;

    public OrderRequest() {
    }

    public OrderRequest(String secondParameter, String thirdParameter) {
        super();
        this.secondParameter = secondParameter;
        this.thirdParameter = thirdParameter;
    }

    @Override
    public void executeRequest(Request request, OrderBook orderBook, BufferedWriter writer) {
        OrderRequest orderRequest = (OrderRequest) request;

        String key = orderRequest.getSecondParameter();

        Integer transactionSize = Integer.parseInt(orderRequest.getThirdParameter());
        switch (key) {

            case "sell": // bid
                doTransaction(orderRequest, orderBook, "bid", transactionSize);
                break;

            case "buy": //ask
                doTransaction(orderRequest, orderBook, "ask", transactionSize);
                break;

        }
    }

    public static void doTransaction(OrderRequest orderRequest, OrderBook orderBook, String variable, Integer transactionSize) {

        while (transactionSize != 0) {

            Order bestOrder = null;
            if (variable.equals("ask")) {
                bestOrder = bestAsk(orderBook);
            } else if (variable.equals("bid")) {
                bestOrder = bestBid(orderBook);
            } else {
                return;
            }

            if (bestOrder != null) {

                int possition = orderBook.getOrders().indexOf(bestOrder);

                if (bestOrder.getSize() > transactionSize) {
                    bestOrder.setSize(bestOrder.getSize() - Integer.parseInt(orderRequest.getThirdParameter()));
                    orderBook.getOrders().set(possition, bestOrder);
                    transactionSize = 0;
                } else if (bestOrder.getSize() == transactionSize) {
                    orderBook.getOrders().remove(possition);
                    transactionSize = 0;
                } else if (bestOrder.getSize() < transactionSize) {
                    orderBook.getOrders().remove(possition);
                    transactionSize = transactionSize - bestOrder.getSize();
                }
            }
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

}
