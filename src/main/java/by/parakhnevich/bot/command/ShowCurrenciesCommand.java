package by.parakhnevich.bot.command;

import by.parakhnevich.bot.collection.Currency;
import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.service.exception.UnexpectedException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.MalformedURLException;

public class ShowCurrenciesCommand implements Command{
    @Override
    public String execute(Message message) throws MalformedURLException, UnexpectedException, BadCurrencyException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Currency currency : Currency.values()) {
            stringBuilder.append(currency.toString()).append('\n');
        }
        return "Available currencies are : \n" + stringBuilder.toString();
    }
}
