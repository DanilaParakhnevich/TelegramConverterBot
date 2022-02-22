package by.parakhnevich.bot.command;

import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.service.exception.BadNumberException;
import by.parakhnevich.bot.service.exception.UnexpectedException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.MalformedURLException;

public interface Command {
    public String execute(Message message) throws MalformedURLException, UnexpectedException, BadCurrencyException, BadNumberException;
}
