package by.parakhnevich.bot.command;

import by.parakhnevich.bot.service.JsonParser;
import by.parakhnevich.bot.service.MessageService;
import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.service.exception.UnexpectedException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;

public class GetCurrencyCommand implements Command {
    private final String URL = "https://belarusbank.by/api/kursExchange?city=Минск";
    private final MessageService service;
    JsonParser parser;
    public GetCurrencyCommand() {
        service = new MessageService();
        parser = new JsonParser();
    }
    @Override
    public String execute(Message message) throws MalformedURLException, UnexpectedException, BadCurrencyException {
        String parameter = service.getFirstParameterFromMessage(message);
        List<String> values = parser.getExchangeRate(URL,
                        parameter.toUpperCase(Locale.ROOT));
        return parameter.toUpperCase(Locale.ROOT) +
                "\nin : " + values.get(0) + " BYN" +
                "\non : " + values.get(1) + " BYN";
    }
}
