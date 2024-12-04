import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class FoodOrderingSystem {
    JFrame frame;
    JTextArea orderSummary;
    JTextField phoneField;
    JComboBox<String> paymentOptions;
    Map<String, Integer> menu;
    Map<String, Integer> order;

    public FoodOrderingSystem() {
        menu = new HashMap<>();
        order = new HashMap<>();
        initializeMenu();
        createUI();
    }

    private void initializeMenu() {
        menu.put("Pizza", 12);
        menu.put("Burger", 8);
        menu.put("Pasta", 10);
        menu.put("Salad", 6);
        menu.put("Coffee", 3);
    }

    private void createUI() {
        frame = new JFrame("Food Ordering System");
        frame.setSize(500, 600);
        frame.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel);

        // Menu List
        for (String item : menu.keySet()) {
            JButton itemButton = new JButton(item + " - $" + menu.get(item));
            itemButton.addActionListener(e -> addItemToOrder(item));
            frame.add(itemButton);
        }

        // Order Summary
        orderSummary = new JTextArea(10, 40);
        orderSummary.setEditable(false);
        frame.add(new JScrollPane(orderSummary));

        // Phone Number Input
        frame.add(new JLabel("Enter Phone Number:"));
        phoneField = new JTextField(20);
        frame.add(phoneField);

        // Payment Options
        frame.add(new JLabel("Select Payment Method:"));
        String[] payments = {"Credit Card", "Debit Card", "PayPal", "Cash on Delivery"};
        paymentOptions = new JComboBox<>(payments);
        frame.add(paymentOptions);

        // Buttons
        JButton trackOrderButton = new JButton("Track Order");
        trackOrderButton.addActionListener(e -> trackOrder());
        frame.add(trackOrderButton);

        JButton payButton = new JButton("Pay Now");
        payButton.addActionListener(e -> processPayment());
        frame.add(payButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addItemToOrder(String item) {
        order.put(item, order.getOrDefault(item, 0) + 1);
        updateOrderSummary();
    }

    private void updateOrderSummary() {
        StringBuilder summary = new StringBuilder("Your Order:\n");
        int total = 0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            int price = menu.get(item) * quantity;
            total += price;
            summary.append(item).append(" x ").append(quantity).append(" = $").append(price).append("\n");
        }
        summary.append("Total: $").append(total).append("\n");
        orderSummary.setText(summary.toString());
    }

    private void trackOrder() {
        JOptionPane.showMessageDialog(frame, "Your order is being prepared. Estimated time: 30 minutes.");
    }

    private void processPayment() {
        String phoneNumber = phoneField.getText();
        String paymentMethod = (String) paymentOptions.getSelectedItem();

        if (phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter your phone number.");
            return;
        }

        JOptionPane.showMessageDialog(frame, "Payment successful via " + paymentMethod + "\nThank you for your order!");
    }

    public static void main(String[] args) {
        new FoodOrderingSystem();
    }
}