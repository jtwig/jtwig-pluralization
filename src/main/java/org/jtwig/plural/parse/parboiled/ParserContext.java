package org.jtwig.plural.parse.parboiled;

import org.parboiled.BaseParser;

import java.util.HashMap;
import java.util.Map;

public class ParserContext {
    private final Map<Class, BaseParser> parsers = new HashMap<Class, BaseParser>();

    public <T> T parser (Class<T> type) {
        return type.cast(parsers.get(type));
    }

    public ParserContext register (Class type, BaseParser parser) {
        parsers.put(type, parser);
        return this;
    }
}
