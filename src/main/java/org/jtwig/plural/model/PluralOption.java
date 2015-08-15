package org.jtwig.plural.model;

import com.google.common.base.Predicate;

public class PluralOption implements Predicate<Integer> {
    private final String message;
    private final Predicate<Integer> include;

    public PluralOption(String message, Predicate<Integer> include) {
        this.message = message;
        this.include = include;
    }

    @Override
    public boolean apply(Integer input) {
        return include.apply(input);
    }

    public String getMessage() {
        return message;
    }
}
