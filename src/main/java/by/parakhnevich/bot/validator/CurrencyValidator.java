package by.parakhnevich.bot.validator;

import by.parakhnevich.bot.collection.Currency;
import by.parakhnevich.bot.service.exception.BadCurrencyException;

import java.util.Locale;

public class CurrencyValidator {
    public void validate (String currency) throws BadCurrencyException {
        try {
            Currency.valueOf(currency.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new BadCurrencyException("You have entered incorrect currency.\nSee /cs");
        }
    }
}
