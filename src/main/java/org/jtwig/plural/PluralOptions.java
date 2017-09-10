package org.jtwig.plural;

import com.google.common.base.Optional;
import org.jtwig.plural.model.PluralOption;
import org.jtwig.plural.parse.parsky.PluralOptionsSequenceMatcher;
import org.parsky.Parsky;

import java.util.Collection;

public class PluralOptions {
    private static final Parsky<PluralOptions> PARSER = new Parsky<>(PluralOptions.class, new PluralOptionsSequenceMatcher());

    public static PluralOptions parse (String representation) {
        return PARSER.parse(representation).output();
    }

    private final Collection<PluralOption> pluralOption;

    public PluralOptions(Collection<PluralOption> pluralOption) {
        this.pluralOption = pluralOption;
    }

    public Optional<String> select (int value) {
        for (PluralOption option : pluralOption) {
            if (option.apply(value)) {
                return Optional.of(option.getMessage());
            }
        }
        return Optional.absent();
    }
}
