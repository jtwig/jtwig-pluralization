package org.jtwig.plural.parse.parboiled.limits;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.jtwig.plural.parse.parboiled.BasicParser;
import org.jtwig.plural.parse.parboiled.ParserContext;
import org.parboiled.Rule;

import static org.parboiled.Parboiled.createParser;

public class AnyPredicateParser extends BasicParser<Predicate<Integer>> {
    public AnyPredicateParser(ParserContext context) {
        super(AnyPredicateParser.class, context);

        createParser(EqualToLimitParser.class, context);
        createParser(IntervalParser.class, context);
    }

    @Override
    public Rule Rule() {
        return FirstOf(
                parserContext().parser(EqualToLimitParser.class).Rule(),
                parserContext().parser(IntervalParser.class).Rule(),
                push(Predicates.<Integer>alwaysTrue())
        );
    }
}
