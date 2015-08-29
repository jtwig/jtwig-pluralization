package org.jtwig.plural.parse.parboiled.limits;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.jtwig.plural.parse.parboiled.BasicParser;
import org.jtwig.plural.parse.parboiled.NumberParser;
import org.jtwig.plural.parse.parboiled.ParserContext;
import org.jtwig.plural.parse.parboiled.SpacingParser;
import org.parboiled.Rule;

public class EqualToLimitParser extends BasicParser<Predicate<Integer>> {
    public EqualToLimitParser(ParserContext context) {
        super(EqualToLimitParser.class, context);
    }

    @Override
    public Rule Rule() {
        SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
        NumberParser numberParser = parserContext().parser(NumberParser.class);
        return Sequence(
                String("{"),
                spacingParser.Rule(),
                numberParser.Rule(),
                spacingParser.Rule(),
                String("}"),

                push(Predicates.equalTo(numberParser.pop()))
        );
    }
}
