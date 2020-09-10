package market.requests;

import market.order.OrderBook;

import java.io.BufferedWriter;

public class Request {

    public Request() {
    }

    public void executeRequest(Request request, OrderBook orderBook, BufferedWriter writer) {
        // need to override
    }

}
