package by.parakhnevich.bot.command;

import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.service.exception.UnexpectedException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.MalformedURLException;

public class HelpCommand implements Command {
    @Override
    public String execute(Message message) throws MalformedURLException, UnexpectedException, BadCurrencyException {
        return "Available commands are :\n/cs\n/info 'currency'\n/f\n/t\n/c 'value to convert'\n/hello" +
                "\nAll info taken from Belarusbank api";
    }
}
