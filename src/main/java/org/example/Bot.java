package org.example;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;


public class Bot extends TelegramLongPollingBot {

    Long chatID;

    @Override
    public String getBotUsername() {
        return "databasebot";
    }

    @Override
    public String getBotToken() {
        return "7525783121:AAEQ_LqO5m0i2XM0wWqGiiDk5OIVfnqlNts";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            Message message = update.getMessage();
            chatID = message.getChatId();
            String text = message.getText();
            IDB dataBase = DataBase.getInstance();
            if (text.equals("load")){
                ArrayList<String> data = dataBase.loadData();
                String result = "";
                for (int i = 0; i < data.size(); i++) {
                    result+=data.get(i)+"\n";
                }
                sendText(chatID,result);
                System.out.println("data from file was send to user");
                return;
            }
            if (text.equals("erase")){
                dataBase.eraseData();
                sendText(chatID,"erase complete successfully!");
                return;
            }
            dataBase.saveStringData(text);
        }

    }



    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

}
