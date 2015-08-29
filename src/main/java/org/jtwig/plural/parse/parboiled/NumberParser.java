package org.jtwig.plural.parse.parboiled;

import org.parboiled.Rule;

public class NumberParser extends BasicParser<Integer> {
    public NumberParser(ParserContext context) {
        super(NumberParser.class, context);
    }

    @Override
    public Rule Rule() {
        return Sequence(
                Sequence(
                        Optional("-"),
                        OneOrMore(CharRange('0', '9'))
                ),
                push(Integer.parseInt(match()))
        );
    }
}
