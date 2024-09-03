package org.example;

import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.util.ArrayList;

public class DataBase implements IDB {

    private static DataBase instance;

    private static final String filename = "database.dxd";

    public static DataBase getInstance(){
        if (instance==null){
            instance = new DataBase();
        }
        return instance;
    }

    private static void writeStringToFile(String data){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
            writer.write(data+"\n");
            System.out.println("Brand new data was wrote to database file: "+data);
        } catch (IOException e) {
            System.out.println("An error was occurred 'someone ate all of my biscuit (british+)'");
        }
    }

    private static void writeArrayToFile(ArrayList<String> data){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
            for (String s : data){
                writer.write(s+"\n");
            }
            System.out.println("Brand new data was wrote to database file: "+data);
        } catch (IOException e) {
            System.out.println("An error was occurred 'someone ate all of my biscuit (british+)'");
        }
    }

    private static void erase(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("");
            System.out.println("erase complete successfully");
        } catch (IOException e) {
            System.out.println("An error was occurred 'someone ate all of my biscuit (british+)'");
        }
    }

    @Override
    public void saveStringData(String data) {
        writeStringToFile(data);
    }

    @Override
    public void saveArrayData(ArrayList<String> data) {
        writeArrayToFile(data);
    }

    @Override
    public ArrayList<String> loadData() {
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error was occurred 'someone ate all of my biscuit (british+)'");
        }
        return list;
    }

    @Override
    public void deleteData(String data) {
        ArrayList<String> list = loadData();
        list.remove(data);
        saveArrayData(list);
    }

    @Override
    public void eraseData() {
        erase();
    }

    @Override
    public void updateData(String what,String to) {
        ArrayList<String> list = loadData();
        String replace = "";
        for (String s : list){
            if (s.equals(what)){
                replace = to;
                list.remove(s);
                list.add(replace);
                return;
            }
        }
    }
}
