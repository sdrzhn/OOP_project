package com.company;

import com.company.repositories.interfaces.IRepository;
import com.company.repositories.Repository;
import com.company.data.interfaces.IDataManager;
import com.company.data.DataManager;
import com.company.controllers.Controller;

public class Main {

    public static void main(String[] args) {
        IDataManager dbm = new DataManager();
        IRepository repository = new Repository(dbm);
        Controller controller = new Controller(repository);
        Menu menu = new Menu(controller);
        menu.start();                                              //starting program
    }
}
