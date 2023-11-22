package com.destore.presentation;

import com.destore.application.*;
import com.destore.business.CustomerService;
import com.destore.business.InventoryService;
import com.destore.business.LoyaltyCardService;
import com.destore.business.PriceControlService;
import com.destore.data.*;
import com.destore.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DEStoreSwingGUI {
    private CustomerService customerService;
    private InventoryController inventoryController;
    private PriceControlController priceControlController;
    private LoyaltyCardController loyaltyCardController;
    private TransactionController transactionController;
    private static LoyaltyCardService loyaltyCardService;
    private int loggedInManagerId;
    private JFrame frame;
    private JPanel panel;


    public DEStoreSwingGUI(PriceControlController priceControlController, InventoryController inventoryController, CustomerService customerService, LoyaltyCardController loyaltyCardController, TransactionController transactionController) {
        this.priceControlController = priceControlController;
        this.inventoryController = inventoryController;
        this.customerService = customerService;
        this.loyaltyCardController = loyaltyCardController;
        this.transactionController = transactionController;

        this.loyaltyCardService = new LoyaltyCardService(new LoyaltyCardDAO());
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


        //  Price Control button
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
        emailButton.setBounds(270, 20, 120, 25);
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to handle opening the email window
                openEmailWindow();
            }
        });
        panel.add(emailButton);

        // Button to open the Loyalty Card window
        JButton loyaltyCardButton = new JButton("Loyalty Card");
        loyaltyCardButton.setBounds(400, 20, 120, 25);
        loyaltyCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoyaltyCardWindow();
            }
        });
        panel.add(loyaltyCardButton);

        //finance approval button
        JButton financeApprovalButton = new JButton("Finance Approval");
        financeApprovalButton.setBounds(530, 20, 120, 25);
        financeApprovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFinanceApprovalWindow();
            }
        });
        panel.add(financeApprovalButton);

    // Add the report and analysis button
        JButton  reportButton = new JButton("Report");
        reportButton.setBounds(660, 20, 120, 25);
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReportWindow();
            }
        });
        panel.add(reportButton);

        // Add the logout button
        JButton  logoutButton = new JButton("Logout");
        logoutButton.setBounds(790, 20, 120, 25);
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

    private void openLoyaltyCardWindow() {
        // Create a new JFrame for the Loyalty Card window
        JFrame loyaltyCardFrame = new JFrame("Loyalty Card");
        loyaltyCardFrame.setSize(400, 300);

        // Create a panel for the Loyalty Card window
        JPanel loyaltyCardPanel = new JPanel();
        loyaltyCardFrame.getContentPane().add(loyaltyCardPanel);

        // Call the method to set up components for displaying Loyalty Cards
        placeLoyaltyCardComponents(loyaltyCardPanel);

        loyaltyCardFrame.setVisible(true);
    }

    private void placeLoyaltyCardComponents(JPanel panel) {
        // Call the Loyalty Card service to get a list of Loyalty Cards
        List<LoyaltyCard> loyaltyCards = loyaltyCardService.getAllLoyaltyCards();

        // Create a table to display Loyalty Cards
        String[] columnNames = {"Customer ID", "Name", "Loyalty Points", "2for1", "3for2"};
        Object[][] data = new Object[loyaltyCards.size()][5];

        for (int i = 0; i < loyaltyCards.size(); i++) {
            LoyaltyCard loyaltyCard = loyaltyCards.get(i);
            data[i][0] = loyaltyCard.getCustomerId();
            data[i][1] = getCustomerNameById(loyaltyCard.getCustomerId());
            data[i][2] = loyaltyCard.getPoints();

            // Check if 2for1 is applied
            boolean is2For1Applied = loyaltyCardController.applyBOGOF(loyaltyCard.getPoints());
            data[i][3] = is2For1Applied ? "Applied" : "Not Applied";

            // Check if 3for2 is applied
            boolean is3For2Applied = loyaltyCardController.apply3For2(loyaltyCard.getPoints());
            data[i][4] = is3For2Applied ? "Applied" : "Not Applied";
        }


        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
    }

    private String getCustomerNameById(int customerId) {
        // Call the Customer service to get the customer by ID
        Customer customer = customerService.getCustomerById(customerId);

        // Return the customer name
        return (customer != null) ? customer.getName() : "Unknown";
    }

    private void openFinanceApprovalWindow() {
        JFrame financeWindow = new JFrame("Finance Approval");
        financeWindow.setSize(300, 150);

        JPanel financePanel = new JPanel();
        JLabel customerIdLabel = new JLabel("Enter Customer ID:");
        JTextField customerIdField = new JTextField(10);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = customerIdField.getText();
                if (!customerId.isEmpty()) {
                    financeWindow.dispose(); // Close the window after OK is clicked
                    showApprovalMessage(Integer.parseInt(customerId));
                } else {
                    JOptionPane.showMessageDialog(financeWindow, "Please enter a Customer ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        financePanel.add(customerIdLabel);
        financePanel.add(customerIdField);
        financePanel.add(okButton);

        financeWindow.add(financePanel);
        financeWindow.setVisible(true);
    }

    private void showApprovalMessage(int customerId) {
        String customerName = getCustomerNameById(customerId); // You need to implement this method
        JOptionPane.showMessageDialog(null, "Finance approved for Customer ID: " + customerId + " and Name: " + customerName);
    }

    private void openReportWindow() {
        JFrame reportFrame = new JFrame("Transaction Report");
        reportFrame.setSize(800, 600);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Call the transaction service to get all transactions
        List<Transaction> transactions = transactionController.getAllTransactions();

        // Create a panel to hold the table and total amount
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(new BorderLayout());

        // Create a table to display transactions
        String[] columnNames = {"Transaction ID", "Customer ID", "Transaction Date", "Total Amount", "Status"};
        Object[][] data = new Object[transactions.size()][5];
        double totalAmount = 0.0;

        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            data[i][0] = transaction.getTransactionId();
            data[i][1] = transaction.getCustomerId();
            data[i][2] = transaction.getTransactionDate();
            data[i][3] = transaction.getTotalAmount();
            data[i][4] = transaction.getStatus();

            // Accumulate the total amount
            totalAmount += transaction.getTotalAmount();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        // Display the total amount below the table
        JLabel totalLabel = new JLabel("Transactions Total: " + totalAmount);
        reportPanel.add(totalLabel, BorderLayout.SOUTH);

        reportFrame.add(reportPanel);
        reportFrame.setVisible(true);
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
        LoyaltyCardDAO loyaltyCardDAO = new LoyaltyCardDAO();
        CustomerDAO customerDAO = new CustomerDAO(); //
        CustomerService customerService = new CustomerService(customerDAO);
        TransactionDAO transactionDAO = new TransactionDAO();



        PriceControlService priceControlService = new PriceControlService(productDAO, shoppingCart);
        PriceControlController priceControlController = new PriceControlController(priceControlService);

        InventoryService inventoryService = new InventoryService(productDAO, inventoryDAO, managerDAO);
        InventoryController inventoryController = new InventoryController(inventoryService);
        LoyaltyCardController loyaltyCardController = new LoyaltyCardController(loyaltyCardDAO);
        TransactionController transactionController = new TransactionController(transactionDAO);


        SwingUtilities.invokeLater(() -> new DEStoreSwingGUI(priceControlController, inventoryController, customerService, loyaltyCardController, transactionController));
    }
}
