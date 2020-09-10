package market;

import market.order.Order;
import market.order.OrderBook;
import market.requests.OrderRequest;
import market.requests.QueryRequest;
import market.requests.Request;
import market.requests.UpdateRequest;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Request> requestList = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        OrderBook orderBook = new OrderBook(orders);

        File inputFile = new File("Input.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            for (String forline : lines) {
                String[] splitted = forline.split(",");

                Character firstParameter = splitted[0].charAt(0);

                switch (firstParameter) {
                    case 'u':
                        if (splitted.length == 4) {
                            Request updateRequest = new UpdateRequest(splitted[1], splitted[2], splitted[3]);
                            requestList.add(updateRequest);
                        } else break;
                        break;
                    case 'q':
                        if (splitted.length == 2) {
                            Request queryRequest = new QueryRequest(splitted[1]);
                            requestList.add(queryRequest);
                        } else if (splitted.length == 3) {
                            Request queryRequest = new QueryRequest(splitted[1], splitted[2]);
                            requestList.add(queryRequest);
                        } else break;
                        break;
                    case 'o':
                        if (splitted.length == 3) {
                            Request orderRequest = new OrderRequest(splitted[1], splitted[2]);
                            requestList.add(orderRequest);
                        } else break;
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        File outputFile = new File("Output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        for (Request request : requestList) {
            request.executeRequest(request, orderBook, writer);
        }

        writer.close();
    }
}