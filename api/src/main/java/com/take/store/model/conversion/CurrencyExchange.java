package com.take.store.model.conversion;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CurrencyExchange {
    private double originalValue;
    private double convertedValue;
    private String currencyCode;
}
