package market.requests;

import market.order.Order;
import market.order.OrderBook;
import market.order.OrderType;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UtilityMethods {

    public static Order bestBid(OrderBook orderBook) {
        List<Order> requestList = orderBook.getOrders().stream()
                .filter(order -> order.getOrderType().equals(OrderType.bid))
                .collect(Collectors.toList());

        if (requestList.isEmpty() == false) {
            Order maxByPrice = requestList
                    .stream()
                    .max(Comparator.comparing(Order::getPrice))
                    .orElseThrow(NoSuchElementException::new);
            return maxByPrice;
        } else return null;
    }

    public static Order bestAsk(OrderBook orderBook) {
        List<Order> requestList = orderBook.getOrders().stream()
                .filter(order -> order.getOrderType().equals(OrderType.ask))
                .collect(Collectors.toList());

        if (requestList.isEmpty() == false) {
            Order minByPrice = requestList
                    .stream()
                    .min(Comparator.comparing(Order::getPrice))
                    .orElseThrow(NoSuchElementException::new);
            return minByPrice;
        } else return null;
    }

    public static void writeBest(BufferedWriter writer, Order order) {
        try {
            String s1 = Integer.toString(order.getPrice());
            String s2 = ",";
            String s3 = Integer.toString(order.getSize());
            String s4 = "\n";
            String content = s1 + s2 + s3 + s4;

            writer.write(content);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeSize(BufferedWriter writer, Order order) {
        try {
            String s1 = Integer.toString(order.getSize());
            String s2 = "\n";
            String content = s1 + s2;
            writer.write(content);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
