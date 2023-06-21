package com.company.repositories.interfaces;

import com.company.entities.Medicine;

import java.util.ArrayList;

public interface IRepository {
    public ArrayList<Medicine> searchMedicineByName(String name);      //methods that will be used in the Menu part
    public Medicine getMedicineById(int id);
    public boolean addMedicine(Medicine medicine);
    public boolean removeMedicineById(int id);
    public boolean getQuantityOfMedicine(int id);
}
