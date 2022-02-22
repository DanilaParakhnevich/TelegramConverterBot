package by.parakhnevich.bot.bean;

import by.parakhnevich.bot.handler.SimpleOnUpdateReceivedHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    SimpleOnUpdateReceivedHandler handler =
            new SimpleOnUpdateReceivedHandler();

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(SendMessage.builder()
                    .text(handler.handle(update))
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
