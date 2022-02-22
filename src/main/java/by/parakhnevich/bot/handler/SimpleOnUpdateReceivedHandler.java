package by.parakhnevich.bot.handler;

import by.parakhnevich.bot.command.*;
import by.parakhnevich.bot.service.MessageService;
import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.service.exception.BadNumberException;
import by.parakhnevich.bot.service.exception.UnexpectedException;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class SimpleOnUpdateReceivedHandler {
    private final Map<String, Command> commands;
    private final MessageService service;

    public SimpleOnUpdateReceivedHandler() {
        commands = new HashMap<>();
        ConvertCommand convertCommand = new ConvertCommand();
        commands.put("/hello", new HelloCommand());
        commands.put("/info", new GetCurrencyCommand());
        commands.put("/c", convertCommand);
        commands.put("/t", new SetToCurrencyCommand(convertCommand));
        commands.put("/f", new SetFromCurrencyCommand(convertCommand));
        commands.put("/cs", new ShowCurrenciesCommand());
        commands.put("/help", new HelpCommand());
        service = new MessageService();
    }

    public String handle(Update update) {
        try {
            if (update.getMessage().isCommand() &&
                    commands.containsKey(service.getCommandFromMessage(update.getMessage()))) {
                return commands
                        .get(service.getCommandFromMessage(update.getMessage()))
                        .execute(update.getMessage());
            } else {
                return "Bad command, print /help";
            }
        } catch (MalformedURLException e) {
            return "Bad url";
        } catch (UnexpectedException | BadNumberException |BadCurrencyException e) {
            return e.getMessage();
        }
    }
}
