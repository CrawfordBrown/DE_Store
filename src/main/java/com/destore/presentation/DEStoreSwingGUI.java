package com.destore.presentation;

import com.destore.application.InventoryController;
import com.destore.application.PriceControlController;
import com.destore.business.InventoryService;
import com.destore.business.PriceControlService;
import com.destore.data.InventoryDAO;
import com.destore.data.ProductDAO;
import com.destore.data.ManagerDAO;
import com.destore.model.Customer;
import com.destore.model.ShoppingCart;
import com.destore.model.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DEStoreSwingGUI {
    private InventoryController inventoryController;
    private PriceControlController priceControlController;
    private int loggedInManagerId;
    private JFrame frame;
    private JPanel panel;


    public DEStoreSwingGUI(PriceControlController priceControlController, InventoryController inventoryController) {
        this.priceControlController = priceControlController;
        this.inventoryController = inventoryController;

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
                    // Successful login, store the manager's ID
                    ManagerDAO managerDAO = new ManagerDAO();
                    Manager manager = managerDAO.getManagerByEmail(email);
                    loggedInManagerId = manager.getManagerId();

                    // Show main content
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

        // Button to open the Inventory window
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setBounds(140, 20, 120, 25);
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInventoryWindow();
            }
        });
        panel.add(inventoryButton);

        // Add the email button
       JButton emailButton = new JButton("Emails");
        emailButton.setBounds(140, 80, 120, 25);
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to handle opening the email window
                openEmailWindow();
            }
        });
        panel.add(emailButton);


    // Add the logout button
        JButton  logoutButton = new JButton("Logout");
        logoutButton.setBounds(270, 80, 120, 25);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to handle logout
                handleLogout();
            }
        });
        panel.add(logoutButton);


    }

    private void openEmailWindow() {
        // Create a new JFrame for the email window
        JFrame emailFrame = new JFrame("Emails");
        emailFrame.setSize(300, 200);

        // Create a panel for the email window
        JPanel emailPanel = new JPanel();
        emailFrame.getContentPane().add(emailPanel);

        // Call the method to set up components for displaying emails
        placeEmailComponents(emailPanel);

        emailFrame.setVisible(true);
    }

    private void placeEmailComponents(JPanel panel) {
        JTextArea emailTextArea = new JTextArea("List of Emails\n\n");
        List<String> emails = getEmailsForLoggedInUser(loggedInManagerId);
        for (String email : emails) {
            emailTextArea.append(email + "\n\n\n\n\n\n\n\n");
        }
        emailTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(emailTextArea);
        scrollPane.setBounds(10, 20, 260, 120);
        panel.add(scrollPane);
    }


    private List<String> getEmailsForLoggedInUser(int managerId) {
        ManagerDAO managerDAO = new ManagerDAO();
        return managerDAO.getEmailsByManagerId(managerId);
    }


    private boolean isValidLogin(String email, String password) {

        ManagerDAO managerDAO = new ManagerDAO();
        Manager manager = managerDAO.getManagerByEmail(email);

        if (manager != null && manager.getPassword().equals(password)) {
            loggedInManagerId = manager.getManagerId(); // Set the logged-in manager's ID
            return true;
        } else {
            return false;
        }    }
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

    private void openInventoryWindow() {
        JFrame inventoryFrame = new JFrame("Inventory");
        inventoryFrame.setSize(300, 200);

        JPanel inventoryPanel = new JPanel();
        inventoryFrame.getContentPane().add(inventoryPanel);

        // Call the method to set up components for Inventory functionality
        placeInventoryComponents(inventoryPanel);

        inventoryFrame.setVisible(true);
    }

    private void placeInventoryComponents(JPanel panel) {
        panel.setLayout(null);

        JButton addProductButton = new JButton("Add Stock");
        addProductButton.setBounds(10, 20, 120, 25);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle adding a product
                handleAddProduct();
            }
        });
        panel.add(addProductButton);

        JButton checkStockButton = new JButton("Check Stock");
        checkStockButton.setBounds(140, 20, 120, 25);
        checkStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle checking stock
                handleCheckStock();
            }
        });
        panel.add(checkStockButton);

    }

    private void handleAddProduct() {
        // Prompt the user for Product ID and Quantity using JOptionPane
        String productIdString = JOptionPane.showInputDialog("Enter Product ID:");
        String quantityString = JOptionPane.showInputDialog("Enter Quantity:");

        // Check if the user clicked "Cancel" or closed the dialog
        if (productIdString != null && quantityString != null) {
            try {
                // Parse the input values to integers
                int productId = Integer.parseInt(productIdString);
                int quantity = Integer.parseInt(quantityString);

                // Call the corresponding method in your inventory controller
                inventoryController.addToInventory(productId, quantity);

                // Display a success message
                JOptionPane.showMessageDialog(panel, "Product added to inventory successfully!");
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid number
                JOptionPane.showMessageDialog(panel, "Invalid input. Please enter valid numbers for Product ID and Quantity.");
            }
        }
    }


    private void handleCheckStock() {

        inventoryController.checkStock();

        // Display a message to inform the user that the stock check is complete
        JOptionPane.showMessageDialog(panel, "Stock check complete. Low stock alerts sent to store managers.");

    }


    private void handleLogout() {
        // Clear the main panel and show the login page
        showLoginPage();
    }


    public static void main(String[] args) {
        // The main method remains the same
        Customer customer = new Customer();
        ProductDAO productDAO = new ProductDAO();
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        InventoryDAO inventoryDAO = new InventoryDAO();
        ManagerDAO managerDAO = new ManagerDAO();


        PriceControlService priceControlService = new PriceControlService(productDAO, shoppingCart);
        PriceControlController priceControlController = new PriceControlController(priceControlService);

        InventoryService inventoryService = new InventoryService(productDAO, inventoryDAO, managerDAO);
        InventoryController inventoryController = new InventoryController(inventoryService);


        SwingUtilities.invokeLater(() -> new DEStoreSwingGUI(priceControlController, inventoryController));
    }
}
