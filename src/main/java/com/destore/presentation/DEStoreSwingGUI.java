package com.destore.presentation;

import com.destore.application.PriceControlController;
import com.destore.business.PriceControlService;
import com.destore.data.ProductDAO;
import com.destore.data.ManagerDAO;
import com.destore.model.Customer;
import com.destore.model.ShoppingCart;
import com.destore.model.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DEStoreSwingGUI {
    private PriceControlController priceControlController;
    private JFrame frame;
    private JPanel panel;

    public DEStoreSwingGUI(PriceControlController priceControlController) {
        this.priceControlController = priceControlController;

        // Initialize and set up your main JFrame
        frame = new JFrame("DE-Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create a panel for the main content
        panel = new JPanel();
        frame.getContentPane().add(panel);

        // Display the login page
        showLoginPage();

        frame.setVisible(true);
    }

    private void showLoginPage() {
        // Clear the main panel and set layout to null for custom component placement
        panel.removeAll();
        panel.setLayout(null);

        // Create login components
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 20, 165, 25);
        panel.add(emailText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 120, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check login credentials
                String email = emailText.getText();
                char[] passwordChars = passwordText.getPassword();
                String password = new String(passwordChars);

                if (isValidLogin(email, password)) {
                    // Successful login, show main content
                    showMainContent();
                } else {
                    // Failed login, show error message
                    JOptionPane.showMessageDialog(panel, "Invalid email or password. Try again.");
                }
            }
        });
        panel.add(loginButton);
    }

    private void showMainContent() {
        // Clear the main panel and set layout to null for custom component placement
        panel.removeAll();
        panel.setLayout(null);

        // Add your main content components here

        // For example, let's add a button to open the Price Control window
        JButton priceControlButton = new JButton("Price Control");
        priceControlButton.setBounds(10, 20, 120, 25);
        priceControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPriceControlWindow();
            }
        });
        panel.add(priceControlButton);

        // Add other main content components as needed
    }

    private boolean isValidLogin(String email, String password) {
        // Replace this with actual authentication logic
        ManagerDAO managerDAO = new ManagerDAO();
        Manager manager = managerDAO.getManagerByEmail(email);

        return manager != null && manager.getPassword().equals(password);
    }
    private void placePriceControlComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdLabel.setBounds(10, 20, 80, 25);
        panel.add(productIdLabel);

        JTextField productIdText = new JTextField(20);
        productIdText.setBounds(100, 20, 165, 25);
        panel.add(productIdText);

        JLabel newPriceLabel = new JLabel("New Price:");
        newPriceLabel.setBounds(10, 50, 80, 25);
        panel.add(newPriceLabel);

        JTextField newPriceText = new JTextField(20);
        newPriceText.setBounds(100, 50, 165, 25);
        panel.add(newPriceText);

        JButton setPriceButton = new JButton("Set Price");
        setPriceButton.setBounds(10, 80, 120, 25);
        setPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = Integer.parseInt(productIdText.getText());
                double newPrice = Double.parseDouble(newPriceText.getText());
                priceControlController.setProductPrice(productId, newPrice);
                JOptionPane.showMessageDialog(panel, "Price set successfully!");
            }
        });
        panel.add(setPriceButton);

        // Add more components and actions as needed for Price Control functionality
    }
    private void openPriceControlWindow() {

        JFrame priceControlFrame = new JFrame("Price Control");
        priceControlFrame.setSize(300, 200);

        JPanel priceControlPanel = new JPanel();
        priceControlFrame.getContentPane().add(priceControlPanel);

        // Call the method to set up components for setting the price
        placePriceControlComponents(priceControlPanel);

        priceControlFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // The main method remains the same
        Customer customer = new Customer();
        ProductDAO productDAO = new ProductDAO();
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        PriceControlService priceControlService = new PriceControlService(productDAO, shoppingCart);
        PriceControlController priceControlController = new PriceControlController(priceControlService);

        SwingUtilities.invokeLater(() -> new DEStoreSwingGUI(priceControlController));
    }
}
