package org.example;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

public interface IDB {

    void saveStringData(String data);
    void saveArrayData(ArrayList<String> data);
    ArrayList<String> loadData();
    void deleteData(String data);
    void eraseData();//risky
    void updateData(String what,String to);
}
