package org.jtwig.plural.parse.parboiled;

import org.parboiled.Rule;

public class MessageParser extends BasicParser<String> {
    public MessageParser(ParserContext context) {
        super(MessageParser.class, context);
    }

    @Override
    public Rule Rule() {
        return Sequence(
                ZeroOrMore(
                        FirstOf(
                                Escape(),
                                Other()
                        )
                ),
                push(match().replace("\\|", "|").trim())
        );
    }

    Rule Other() {
        return Sequence(
                TestNot("|"),
                ANY
        );
    }

    Rule Escape() {
        return String("\\|");
    }
}
