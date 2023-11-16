package com.destore.test.dataTests;

import com.destore.data.ManagerDAO;
import com.destore.model.Manager;

public class ManagerDAOTest {
    public static void main(String[] args) {
        testGetManagerById();
        testAddManager();
        testUpdateManager();
        testDeleteManager();
    }

    private static void testGetManagerById() {
        ManagerDAO managerDAO = new ManagerDAO();
        int managerId = 1; // Assuming a valid manager ID
        Manager manager = managerDAO.getManagerById(managerId);

        if (manager != null) {
            System.out.println("Retrieved Manager:");
            System.out.println("Manager ID: " + manager.getManagerId());
            System.out.println("Manager Name: " + manager.getName());
            System.out.println("Manager Email: " + manager.getEmail());
            System.out.println();
        } else {
            System.out.println("Manager not found.");
        }
    }

    private static void testAddManager() {
        ManagerDAO managerDAO = new ManagerDAO();
        Manager newManager = new Manager();
        newManager.setName("New Manager");
        newManager.setEmail("new.manager@destore.com");

        managerDAO.addManager(newManager);
    }

    private static void testUpdateManager() {
        ManagerDAO managerDAO = new ManagerDAO();
        int managerIdToUpdate = 2; // Assuming a valid manager ID
        Manager updatedManager = new Manager();
        updatedManager.setManagerId(managerIdToUpdate);
        updatedManager.setName("Updated Manager");
        updatedManager.setEmail("updated.manager@destore.com");

        managerDAO.updateManager(updatedManager);
    }

    private static void testDeleteManager() {
        ManagerDAO managerDAO = new ManagerDAO();
        int managerIdToDelete = 3; // Assuming a valid manager ID

        managerDAO.deleteManager(managerIdToDelete);
    }
}
