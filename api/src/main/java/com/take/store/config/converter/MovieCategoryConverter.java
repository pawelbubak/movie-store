package com.take.store.config.converter;

import com.take.store.model.movie.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MovieCategoryConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        return Category.forValue(value);
    }
}
