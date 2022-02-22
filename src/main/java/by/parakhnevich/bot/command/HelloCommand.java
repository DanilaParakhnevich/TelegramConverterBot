package by.parakhnevich.bot.command;

import org.telegram.telegrambots.meta.api.objects.Message;

public class HelloCommand implements Command{

    @Override
    public String execute(Message message) {
        return "Hello , " + message.getFrom().getFirstName();
    }
}
