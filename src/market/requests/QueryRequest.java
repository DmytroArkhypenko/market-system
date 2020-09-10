package market.requests;

import market.order.Order;
import market.order.OrderBook;

import java.io.BufferedWriter;
import java.util.List;
import java.util.stream.Collectors;

import static market.requests.UtilityMethods.*;

public class QueryRequest extends Request {

    String secondParameter;
    String thirdParameter;

    public QueryRequest() {
    }

    public QueryRequest(String secondParameter, String thirdParameter) {
        super();
        this.secondParameter = secondParameter;
        this.thirdParameter = thirdParameter;
    }

    public QueryRequest(String secondParameter) {
        super();
        this.secondParameter = secondParameter;
    }

    @Override
    public void executeRequest(Request request, OrderBook orderBook, BufferedWriter writer) {
        QueryRequest queryRequest = (QueryRequest) request;
        String key = queryRequest.getSecondParameter();
        switch (key) {
            case "best_bid":
                Order bestBidOrder = bestBid(orderBook);
                if (bestBidOrder != null) {
                    writeBest(writer, bestBidOrder);
                } else break;
                break;

            case "best_ask":
                Order bestAskOrder = bestAsk(orderBook);
                if (bestAskOrder != null) {
                    writeBest(writer, bestAskOrder);
                } else break;

                break;

            case "size":
                List<Order> filteredByPrice = orderBook.getOrders().stream()
                        .filter(order -> order.getPrice().equals(Integer.parseInt(queryRequest.getThirdParameter())))
                        .collect(Collectors.toList());
                if (filteredByPrice.size() == 0) {
                    Order order = new Order(Integer.parseInt(queryRequest.getThirdParameter()));
                    writeSize(writer, order);
                } else if (filteredByPrice.size() == 1) {
                    Order order = filteredByPrice.get(0);
                    writeSize(writer, order);
                } else break;
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
