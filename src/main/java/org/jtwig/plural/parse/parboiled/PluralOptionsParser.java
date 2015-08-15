package org.jtwig.plural.parse.parboiled;

import org.jtwig.plural.model.PluralOption;
import org.parboiled.Rule;

import java.util.ArrayList;
import java.util.Collection;

import static org.parboiled.Parboiled.createParser;

public class PluralOptionsParser extends BasicParser<Collection<PluralOption>> {
    public PluralOptionsParser(ParserContext context) {
        super(PluralOptionsParser.class, context);

        createParser(SpacingParser.class, context);
        createParser(NumberParser.class, context);
        createParser(PluralOptionParser.class, context);
    }

    @Override
    public Rule Rule() {
        PluralOptionParser optionParser = parserContext().parser(PluralOptionParser.class);
        SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
        return Sequence(
                push(new ArrayList<PluralOption>()),
                optionParser.Rule(),
                peek(1).add(optionParser.pop()),
                ZeroOrMore(
                        spacingParser.Rule(),
                        String("|"),
                        spacingParser.Rule(),
                        optionParser.Rule(),
                        peek(1).add(optionParser.pop())
                )
        );
    }
}
