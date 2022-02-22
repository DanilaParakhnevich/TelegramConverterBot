package by.parakhnevich.bot.command;

import by.parakhnevich.bot.collection.Currency;
import by.parakhnevich.bot.service.JsonParser;
import by.parakhnevich.bot.service.MessageService;
import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.service.exception.BadNumberException;
import by.parakhnevich.bot.service.exception.UnexpectedException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class ConvertCommand implements Command{
    private final String URL = "https://belarusbank.by/api/kursExchange?city=Минск";
    Map<Long, Currency> currenciesFrom;
    Map<Long, Currency> currenciesTo;
    JsonParser parser;
    MessageService service;

    public ConvertCommand() {
        parser = new JsonParser();
        currenciesTo = new HashMap<>();
        currenciesFrom = new HashMap<>();
        service = new MessageService();
    }

    @Override
    public String execute(Message message) throws MalformedURLException, UnexpectedException, BadCurrencyException, BadNumberException {
        Long id = message.getChatId();
        if (currenciesFrom.get(id) == null) {
            throw new BadCurrencyException("First you should enter /f value.\nSee /help");
        } else if (currenciesTo.get(id) == null) {
            throw new BadCurrencyException("First you should enter /t value.\nSee /help");
        }
        BigDecimal fromValue =
                new BigDecimal(parser.getExchangeRate(URL, currenciesFrom.get(id).toString()).get(0));
        BigDecimal toValue =
                new BigDecimal(parser.getExchangeRate(URL, currenciesTo.get(id).toString()).get(0));
        try {
            BigDecimal valueToConvert = new BigDecimal(service.getFirstParameterFromMessage(message));
            if (valueToConvert.compareTo(new BigDecimal("0")) <= 0) {
                return "You have input incorrect value";
            }
            return toValue.divide(fromValue).multiply(valueToConvert).toString();
        } catch (NumberFormatException e) {
            throw new BadNumberException("You have input incorrect value");
        }
    }

    public Map<Long, Currency> getCurrenciesFrom() {
        return currenciesFrom;
    }

    public void setCurrenciesFrom(Map<Long, Currency> currenciesFrom) {
        this.currenciesFrom = currenciesFrom;
    }

    public Map<Long, Currency> getCurrenciesTo() {
        return currenciesTo;
    }

    public void setCurrenciesTo(Map<Long, Currency> currenciesTo) {
        this.currenciesTo = currenciesTo;
    }
}
