package by.parakhnevich.bot.command;

import by.parakhnevich.bot.collection.Currency;
import by.parakhnevich.bot.service.MessageService;
import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.validator.CurrencyValidator;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Locale;

public class SetToCurrencyCommand implements Command{
    MessageService service;
    ConvertCommand command;
    CurrencyValidator validator;

    public SetToCurrencyCommand(ConvertCommand command) {
        service = new MessageService();
        this.command = command;
        validator = new CurrencyValidator();
    }

    @Override
    public String execute(Message message) throws BadCurrencyException {
        String param = service.getFirstParameterFromMessage(message);
        validator.validate(param);
        command.getCurrenciesTo().put(message.getChatId(),
                Currency.valueOf(param.toUpperCase(Locale.ROOT)));
        return "Setted " + param.toUpperCase(Locale.ROOT) + " as to currency";
    }
}
