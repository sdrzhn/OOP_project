package com.company.controllers;

import com.company.repositories.interfaces.IRepository;
import com.company.entities.Medicine;

import java.time.LocalDate;

public class Controller {
    private final IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public String searchMedicineByName(String name) {

        return repository.searchMedicineByName(name).toString();                 //returning method searchMedicineById() by method toString()
    }

    public String getMedicineById(int id) {

        return repository.getMedicineById(id).toString();                       //returning method getMedicineById() by method toString()
    }

    public String addMedicine(String name, double price, String manufacturer, boolean availability, int quantity) {
        LocalDate expirationDate = LocalDate.now();

        boolean added = repository.addMedicine(new Medicine(name, price, expirationDate, manufacturer, availability, quantity));

        if (added) {
            return "Medicine was added!";
        }

        return "Medicine was not added!";                                       //returning the result if the medicine was added
    }

    public String removeMedicineById(int id) {
        boolean removed = repository.removeMedicineById(id);

        if (removed) {
            return "Medicine was removed!";
        }

        return "Medicine was not removed!";                                     //returning the result if the medicine was removed
    }
    
    public String getQuantityOfMedicine(int quantity) {
        boolean avl = repository.getQuantityOfMedicine(quantity);

        if (avl) {
            return "Medicine is available!";
        }
        return "Medicine is not available!";                                    //returning the result is medicine available or not
    }
}
