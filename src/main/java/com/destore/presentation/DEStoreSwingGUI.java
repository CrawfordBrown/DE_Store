package com.destore.presentation;

import com.destore.application.PriceControlController;
import com.destore.business.PriceControlService;
import com.destore.data.ProductDAO;
import com.destore.model.Customer;
import com.destore.model.ShoppingCart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DEStoreSwingGUI {
    private PriceControlController priceControlController;

    public DEStoreSwingGUI(PriceControlController priceControlController) {
        this.priceControlController = priceControlController;

        // Initialize and set up your Swing components
        JFrame frame = new JFrame("DE-Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton priceControlButton = new JButton("Price Control");
        priceControlButton.setBounds(10, 20, 120, 25);
        priceControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPriceControlWindow();
            }
        });
        panel.add(priceControlButton);

        JButton inventoryControlButton = new JButton("Inventory Control");
        inventoryControlButton.setBounds(10, 50, 120, 25);
        inventoryControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to open Inventory Control window
                JOptionPane.showMessageDialog(panel, "Inventory Control functionality");
            }
        });
        panel.add(inventoryControlButton);

        JButton loyaltyCardButton = new JButton("Loyalty Card");
        loyaltyCardButton.setBounds(10, 80, 120, 25);
        loyaltyCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to open Loyalty Card window
                JOptionPane.showMessageDialog(panel, "Loyalty Card functionality");
            }
        });
        panel.add(loyaltyCardButton);

        JButton financeApprovalButton = new JButton("Finance Approval");
        financeApprovalButton.setBounds(10, 110, 120, 25);
        financeApprovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to open Finance Approval window
                JOptionPane.showMessageDialog(panel, "Finance Approval functionality");
            }
        });
        panel.add(financeApprovalButton);

        JButton reportsAndAnalysisButton = new JButton("Reports and Analysis");
        reportsAndAnalysisButton.setBounds(10, 140, 150, 25);
        reportsAndAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to open Reports and Analysis window
                JOptionPane.showMessageDialog(panel, "Reports and Analysis functionality");
            }
        });
        panel.add(reportsAndAnalysisButton);
    }

    private void openPriceControlWindow() {
        // Create a new JFrame for Price Control functionality
        JFrame priceControlFrame = new JFrame("Price Control");
        priceControlFrame.setSize(300, 200);

        JPanel priceControlPanel = new JPanel();
        priceControlFrame.getContentPane().add(priceControlPanel);

        // Call the method to set up components for setting the price
        placePriceControlComponents(priceControlPanel);

        priceControlFrame.setVisible(true);
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

    public static void main(String[] args) {
        Customer customer = new Customer();
        ProductDAO productDAO = new ProductDAO();
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        PriceControlService priceControlService = new PriceControlService(productDAO, shoppingCart);
        PriceControlController priceControlController = new PriceControlController(priceControlService);

        SwingUtilities.invokeLater(() -> new DEStoreSwingGUI(priceControlController));
    }
}
