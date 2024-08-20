import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Double> marketData = new HashMap<>();
    private static final Map<String, Integer> portfolio = new HashMap<>();

    public static void main(String[] args) {
        initializeMarketData();

        while (true) {
            System.out.println("\nStock Trading Platform");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewMarketData();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeMarketData() {
        // Sample market data
        marketData.put("AAPL", 150.0);
        marketData.put("GOOGL", 2800.0);
        marketData.put("MSFT", 300.0);
    }

    private static void viewMarketData() {
        System.out.println("Market Data:");
        for (Map.Entry<String, Double> entry : marketData.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    private static void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (!marketData.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        double price = marketData.get(symbol);
        portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + symbol + " at $" + price + " each.");
    }

    private static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (!portfolio.containsKey(symbol)) {
            System.out.println("You don't own any shares of " + symbol + ".");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int currentQuantity = portfolio.get(symbol);
        if (quantity > currentQuantity) {
            System.out.println("Not enough shares to sell.");
            return;
        }

        double price = marketData.get(symbol);
        if (quantity == currentQuantity) {
            portfolio.remove(symbol);
        } else {
            portfolio.put(symbol, currentQuantity - quantity);
        }
        System.out.println("Sold " + quantity + " shares of " + symbol + " at $" + price + " each.");
    }

    private static void viewPortfolio() {
        System.out.println("Your Portfolio:");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks in portfolio.");
            return;
        }

        double totalValue = 0;
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            double price = marketData.get(symbol);
            double value = price * quantity;
            totalValue += value;
            System.out.println(symbol + ": " + quantity + " shares, $" + value + " total");
        }
        System.out.println("Total Portfolio Value: $" + totalValue);
    }
}
           
    