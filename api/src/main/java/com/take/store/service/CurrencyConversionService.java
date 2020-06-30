package com.take.store.service;

import com.take.store.model.conversion.CurrencyExchange;
import org.springframework.stereotype.Service;

import static java.lang.Math.round;

@Service
public class CurrencyConversionService {
    public CurrencyExchange convert(double value, String fromCurrencyCode, String toCurrencyCode) {
        return CurrencyExchange.builder()
                .originalValue(value)
                .convertedValue(round(convertToOtherCurrency(convertToPln(value, fromCurrencyCode), toCurrencyCode) * 100) / 100.0)
                .currencyCode(toCurrencyCode)
                .build();
    }

    private double convertToPln(double value, String currencyCode) {
        return value * getExchangeRateByCurrencyCode(currencyCode);
    }

    private double convertToOtherCurrency(double value, String currencyCode) {
        return value / getExchangeRateByCurrencyCode(currencyCode);
    }

    private double getExchangeRateByCurrencyCode(String currencyCode) {
        switch (currencyCode.toUpperCase()) {
            case "EUR":
                return 4.46;
            case "PLN":
                return 1;
            case "USD":
                return 3.96;
            default:
                throw new IllegalStateException(String.format("Currency conversion is not possible for the currency " +
                        "code: %s.", currencyCode));
        }
    }
}
