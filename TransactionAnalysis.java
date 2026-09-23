import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class Transaction {
    private String transactionId;
    private String clientId;
    private String transactionType;
    private double amount;
    private String timestamp;

    public Transaction(String transactionId, String clientId,
                       String transactionType, double amount,
                       String timestamp) {
        this.transactionId = transactionId;
        this.clientId = clientId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

public class TransactionAnalysis {

    public static void main(String[] args) {

        String filePath = "D:\\transactions_v1.csv";

        Map<String, Double> totalBuyAmountByClient = new HashMap<>();
        Map<String, Double> totalSellAmountByClient = new HashMap<>();

        List<Transaction> transactionList = new ArrayList<>();

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {

                String[] values = line.split(",");

                String transactionId = values[0].trim();
                String clientId = values[1].trim();
                String transactionType = values[2].trim();
                double amount = Double.parseDouble(values[3].trim());
                String timestamp = values[4].trim();

                Transaction transaction = new Transaction(
                        transactionId,
                        clientId,
                        transactionType,
                        amount,
                        timestamp
                );

                transactionList.add(transaction);

                if (transactionType.equalsIgnoreCase("BUY")) {

                    totalBuyAmountByClient.put(
                            clientId,
                            totalBuyAmountByClient.getOrDefault(clientId, 0.0) + amount
                    );

                } else if (transactionType.equalsIgnoreCase("SELL")) {

                    totalSellAmountByClient.put(
                            clientId,
                            totalSellAmountByClient.getOrDefault(clientId, 0.0) + amount
                    );
                }
            }

            System.out.println("Client Summary:");

            Set<String> clientIds = new TreeSet<>();
            clientIds.addAll(totalBuyAmountByClient.keySet());
            clientIds.addAll(totalSellAmountByClient.keySet());

            for (String clientId : clientIds) {

                double totalBuyAmount =
                        totalBuyAmountByClient.getOrDefault(clientId, 0.0);

                double totalSellAmount =
                        totalSellAmountByClient.getOrDefault(clientId, 0.0);

                double netAmount =
                        totalBuyAmount - totalSellAmount;

                System.out.println(
                        "Client: " + clientId +
                        " -> Total Buy Amount: " +  totalBuyAmount +
                        ", Total Sell Amount: " + totalSellAmount +
                        ", Net Amount: " +  netAmount
                );
            }

            transactionList.sort(
                    Comparator.comparing(Transaction::getTimestamp)
            );

            System.out.println("\nTransactions Sorted By Timestamp (Ascending):");

            for (Transaction transaction : transactionList) {

                System.out.println(
                        transaction.getTimestamp() + " | " +
                        transaction.getTransactionId() + " | " +
                        transaction.getClientId() + " | " +
                        transaction.getTransactionType() + " | " +
                        (int) transaction.getAmount()
                );
            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}