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

            // Add more components and actions as needed for other functionalities
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

