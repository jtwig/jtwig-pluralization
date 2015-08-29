package org.jtwig.plural.parse.parboiled;

import org.parboiled.BaseParser;
import org.parboiled.Rule;

public abstract class BasicParser<T> extends BaseParser<T> {
    private final ParserContext context;

    public BasicParser(Class type, ParserContext context) {
        context.register(type, this);
        this.context = context;
    }

    public ParserContext parserContext() {
        return context;
    }

    public abstract Rule Rule ();

}
