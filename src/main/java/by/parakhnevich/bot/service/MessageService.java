package by.parakhnevich.bot.service;


import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageService {
    public String getCommandFromMessage(Message message) {
        return message.getText().split(" ")[0];
    }

    public String getFirstParameterFromMessage(Message message) {
        return message.getText().split(" ")[1];
    }

    public String getSecondParameterFromMessage(Message message) {
        return message.getText().split(" ")[2];
    }
}
