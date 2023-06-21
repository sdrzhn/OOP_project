package com.company;

import com.company.controllers.Controller;

import java.util.Scanner;

public class Menu {
    private final Controller controller;
    private final Scanner scanner;

    public Menu(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while(true) {
            System.out.println("Choose one option:\n 1) Search medicine by name\n 2) Get medicine by id\n 3) Add medicine\n 4) Remove medicine\n 5) Take one medicine by id\n 0) Exit\n");
            int choice = scanner.nextInt();               //getting number of the option by using scanner

            if (choice==1) {                              //if entered 1 searchMedicineByNameMenu() will be executed
                searchMedicineByNameMenu();
            }
            else if (choice==2) {                         //if entered 2 getMedicineByIdMenu() will be executed
                getMedicineByIdMenu();
            }
            else if (choice==3) {                         //if entered 3 addMedicineMenu() will be executed
                addMedicineMenu();
            }
            else if (choice==4) {                         //if entered 4 removeMedicineByIdMenu() will be executed
                removeMedicineByIdMenu();
            }
            else if (choice==5) {                         //if entered 5 getQuantityOfMedicineMenu() will be executed
                getQuantityOfMedicineMenu();
            }
            else {
                break;
            }

            System.out.println("------------------------------");
        }
    }

    public void searchMedicineByNameMenu() {
        System.out.println("The name of medicine:");                   //asking to enter the name of the medicine
        String name = scanner.next();                                  //scanning the name
        String result = controller.searchMedicineByName(name);         //executing method searchMedicineByName() in the controller using name from the scanner
        System.out.println(result);                                    //printing the result
    }

    public void getMedicineByIdMenu() {
        System.out.println("The id of medicine:");                     //asking to enter the id of the medicine
        int id = scanner.nextInt();                                    //scanning the id
        String result = controller.getMedicineById(id);                //executing method getMedicineById() in the controller using id from the scanner
        System.out.println(result);                                    //printing the result
    }

    public void addMedicineMenu() {
        System.out.println("The name of medicine:");                   //asking to enter the name of the medicine
        String name = scanner.next();                                  //scanning the name
        System.out.println("Medicine's price:");                       //asking to enter the price of the medicine
        double price = scanner.nextDouble();                           //scanning the price
        System.out.println("Medicine's manufacturer:");                //asking to enter the manufacturer of the medicine
        String manufacturer = scanner.next();                          //scanning the manufacturer
        boolean availability = true;                                   //availability is always true when the medicine is added
        System.out.println("How much medicine to add?");               //asking to enter the quantity of the medicine
        int quantity = scanner.nextInt();                              //scanning the quantity

        String result = controller.addMedicine(name, price, manufacturer, availability, quantity);    //executing method addMedicine() in the controller using values from the scanner

        System.out.println(result);                                    //printing the result
    }

    public void removeMedicineByIdMenu() {
        System.out.println("Medicine's id:");                          //asking to enter the id of the medicine
        int id = scanner.nextInt();                                    //scanning the id

        String result = controller.removeMedicineById(id);             //executing method removeMedicineById() in the controller using id from the scanner

        System.out.println(result);                                    //printing the result
    }

    public void getQuantityOfMedicineMenu() {
        System.out.println("Medicine's id:");                          //asking to enter the id of the medicine
        int id = scanner.nextInt();                                    //scanning the id

        String result = controller.getQuantityOfMedicine(id);          //executing method getQuantityOfMedicine() in the controller using id from the scanner

        System.out.println(result);                                    //printing the result
    }
}
