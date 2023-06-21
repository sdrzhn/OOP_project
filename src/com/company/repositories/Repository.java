package com.company.repositories;

import com.company.entities.Medicine;
import com.company.repositories.interfaces.IRepository;
import com.company.data.interfaces.IDataManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Repository implements IRepository {       //implementing IRepository
    private final IDataManager dbm;


    public Repository(IDataManager dbm) {
        this.dbm = dbm;
    }

    @Override
    public ArrayList<Medicine> searchMedicineByName(String name) {    //overriding method searchMedicineByName() from the interface IRepository
        Connection connection = null;                                 //initializing connection

        try {
            connection = dbm.getConnection();                         //getting connection

            String sql = "SELECT * FROM Medicine WHERE name LIKE '%" + name + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);   //PreparedStatement is an object representing a precompiled SQL statement

            ResultSet resultSet = preparedStatement.executeQuery();   //a data table representing a set of database results

            ArrayList<Medicine> medicines = new ArrayList<>();        //creating an array

            while (resultSet.next()) {
                Medicine medicine = new Medicine(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getString("manufacturer"),
                        resultSet.getBoolean("availability"),
                        resultSet.getInt("quantity"));

                medicines.add(medicine);
            }

            return medicines;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Medicine getMedicineById(int id) {                         //overriding method getMedicineById() from the interface IRepository
        Connection connection = null;                                 //initializing connection

        try {
            connection = dbm.getConnection();                         //getting connection

            String sql = "SELECT * FROM Medicine WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);   //PreparedStatement is an object representing a precompiled SQL statement

            preparedStatement.setInt(1, id);              //putting id to the SQL statement

            ResultSet resultSet = preparedStatement.executeQuery();   //ResultSet is a data table representing a set of database results

            Medicine medicine = new Medicine();

            if (resultSet.next()) {
                medicine.setId(resultSet.getInt("id"));
                medicine.setName(resultSet.getString("name"));
                medicine.setPrice(resultSet.getDouble("price"));
                medicine.setExpirationDate(resultSet.getDate("expirationDate").toLocalDate());
                medicine.setManufacturer(resultSet.getString("manufacturer"));
                medicine.setAvailability(resultSet.getBoolean("availability"));
                medicine.setQuantity(resultSet.getInt("quantity"));
            }

            return medicine;                                           //return medicine
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {    //overriding method addMedicine() from the interface IRepository
        Connection connection = null;                  //initializing connection

        try {
            connection = dbm.getConnection();          //getting connection

            String sql = "INSERT INTO Medicine (name, price, expirationDate, manufacturer, availability, quantity) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, medicine.getName());                                 //setting name which we get by getter getName() to the SQL statement as a value
            preparedStatement.setDouble(2, medicine.getPrice());                                //setting price which we get by getter getPrice() to the SQL statement as a value
            preparedStatement.setDate(3, Date.valueOf(medicine.getExpirationDate()));           //setting expirationDate which we get by getter getExpirationDate() to the SQL statement as a value
            preparedStatement.setString(4, medicine.getManufacturer());                         //setting manufacturer which we get by getter getManufacturer() to the SQL statement as a value
            preparedStatement.setBoolean(5, medicine.isAvailable());                            //setting availability which we get by getter isAvailable() to the SQL statement as a value
            preparedStatement.setInt(6, medicine.getQuantity());                                //setting quantity which we get by getter getQuantity() to the SQL statement as a value

            preparedStatement.execute();               //execution

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeMedicineById(int id) {               //overriding method removeMedicineById() from the interface IRepository
        Connection connection = null;                         //initializing connection

        try {
            connection = dbm.getConnection();                 //getting connection

            String sql = "DELETE FROM Medicine WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);     //putting id to the SQL statement

            preparedStatement.execute();                     //execution

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean getQuantityOfMedicine(int id) {    //overriding method getQuantityOfMedicine() from the interface IRepository
        Connection connection = null;                 //initializing connection

        try {
            connection = dbm.getConnection();         //getting connection

            String sql = "SELECT quantity FROM medicine WHERE id=?;";
            PreparedStatement pt = connection.prepareStatement(sql);
            pt.setInt(1, id);             //putting id to the SQL statement

            int currentQuantity = 0;                  //initializing current quantity

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                currentQuantity = rs.getInt("quantity");                            //currentQuantity == quantity(from the table)
            }
            currentQuantity = currentQuantity - 1;                                            //removing one medicine

            if (currentQuantity == 0) {
                String sql1 = "UPDATE medicine SET availability = false WHERE id=?;";         //if current quantity is 0 availability in the sql table will be updated to false which meaning that the medicine is not available
                PreparedStatement pt2 = connection.prepareStatement(sql1);
                pt2.setInt(1, id);
                int avl2 = pt2.executeUpdate();
                System.out.println(avl2 + "record updated. Availability false");              //printing "record updated. Availability false"
            }

            if (currentQuantity >= 0) {
                String sql2 = "UPDATE medicine SET quantity=? WHERE id=?;";                    //after removing one medicine quantity changes in the table by -1
                PreparedStatement pt2 = connection.prepareStatement(sql2);
                pt2.setInt(1, currentQuantity);
                pt2.setInt(2, id);

                int avl = pt2.executeUpdate();
                if (avl > 0) {                                                                //if quantity is more than 0, will be printed only "record updated"
                    System.out.println(avl + "record updated");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
