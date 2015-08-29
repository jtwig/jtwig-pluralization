package org.jtwig.plural.parse.parboiled;

import org.jtwig.plural.model.PluralOption;
import org.jtwig.plural.parse.parboiled.limits.AnyPredicateParser;
import org.parboiled.Rule;

import static org.parboiled.Parboiled.createParser;

public class PluralOptionParser extends BasicParser<PluralOption> {
    public PluralOptionParser(ParserContext context) {
        super(PluralOptionParser.class, context);

        createParser(AnyPredicateParser.class, context);
        createParser(MessageParser.class, context);
    }

    @Override
    public Rule Rule() {
        AnyPredicateParser anyPredicateParser = parserContext().parser(AnyPredicateParser.class);
        SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
        MessageParser messageParser = parserContext().parser(MessageParser.class);
        return Sequence(
                anyPredicateParser.Rule(),
                spacingParser.Rule(),
                messageParser.Rule(),

                push(new PluralOption(messageParser.pop(), anyPredicateParser.pop()))
        );
    }
}
