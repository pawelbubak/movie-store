package com.take.store.api.conversion;

import com.take.store.model.conversion.CurrencyExchange;
import com.take.store.service.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("conversion")
public class ConversionController {
    private final CurrencyConversionService currencyConversionService;

    @GetMapping
    public CurrencyExchange convert(@RequestParam double value,
                                    @RequestParam String fromCurrencyCode,
                                    @RequestParam String toCurrencyCode) {
        return currencyConversionService.convert(value, fromCurrencyCode, toCurrencyCode);
    }
}
