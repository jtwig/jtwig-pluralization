package org.jtwig.plural.parse.parboiled;

import org.parboiled.Rule;

public class SpacingParser extends BasicParser<Object> {
    public SpacingParser(ParserContext context) {
        super(SpacingParser.class, context);
    }

    @Override
    public Rule Rule() {
        return ZeroOrMore(
                AnyOf(" \t\r\n\f")
        );
    }
}
