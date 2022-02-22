package by.parakhnevich.bot.service;

import by.parakhnevich.bot.service.exception.BadCurrencyException;
import by.parakhnevich.bot.service.exception.UnexpectedException;
import by.parakhnevich.bot.validator.CurrencyValidator;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class JsonParser {
    CurrencyValidator validator;

    public JsonParser() {
        validator = new CurrencyValidator();
    }

    private String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            return "";
        }
        return stringBuilder.toString();
    }


    public List<String> getExchangeRate (String url, String currency) throws MalformedURLException, BadCurrencyException, UnexpectedException {
        JSONArray jsonArray = new JSONArray(parseUrl(new URL(url)));
        validator.validate(currency);
        if (jsonArray.isEmpty()) {
            throw new UnexpectedException("Error with application.");
        }
        if (currency.toUpperCase(Locale.ROOT).equals("BYN")) {
            return Arrays.asList("1.0", "1.0");
        }
        return Arrays.asList(
                String.valueOf(jsonArray.getJSONObject(0).getBigDecimal(currency + "_in")),
                String.valueOf(jsonArray.getJSONObject(0).getBigDecimal(currency + "_out")));
    }
}
