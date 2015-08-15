package org.jtwig.plural.parse.parboiled.limits;

import com.google.common.base.Predicate;
import org.jtwig.plural.model.limits.InfiniteLimit;
import org.jtwig.plural.model.limits.IntervalPredicate;
import org.jtwig.plural.model.limits.StaticLowerLimit;
import org.jtwig.plural.model.limits.StaticUpperLimit;
import org.jtwig.plural.parse.parboiled.BasicParser;
import org.jtwig.plural.parse.parboiled.NumberParser;
import org.jtwig.plural.parse.parboiled.ParserContext;
import org.jtwig.plural.parse.parboiled.SpacingParser;
import org.parboiled.Rule;

import static org.parboiled.Parboiled.createParser;

public class IntervalParser extends BasicParser<IntervalPredicate> {
    public IntervalParser(ParserContext context) {
        super(IntervalParser.class, context);
        createParser(StartParser.class, context);
        createParser(EndParser.class, context);
        createParser(InclusiveStartParser.class, context);
        createParser(InclusiveEndParser.class, context);
    }

    @Override
    public Rule Rule() {
        StartParser startParser = parserContext().parser(StartParser.class);
        EndParser endParser = parserContext().parser(EndParser.class);
        SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
        return Sequence(
                startParser.Rule(),
                spacingParser.Rule(),
                String(","),
                spacingParser.Rule(),
                endParser.Rule(),

                push(new IntervalPredicate(startParser.pop(1), endParser.pop()))
        );
    }

    public static class StartParser extends BasicParser<Predicate<Integer>> {
        public StartParser(ParserContext context) {
            super(StartParser.class, context);
        }

        @Override
        public Rule Rule() {
            InclusiveStartParser inclusiveStartParser = parserContext().parser(InclusiveStartParser.class);
            SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
            NumberParser numberParser = parserContext().parser(NumberParser.class);
            return Sequence(
                    inclusiveStartParser.Rule(),
                    spacingParser.Rule(),
                    FirstOf(
                            Sequence(
                                    String("Inf"),
                                    run(inclusiveStartParser.pop()),
                                    push(new InfiniteLimit())
                            ),
                            Sequence(
                                    numberParser.Rule(),
                                    push(new StaticLowerLimit(inclusiveStartParser.pop(1), numberParser.pop()))
                            )
                    )
            );
        }

        boolean run(Object value) {
            return true;
        }
    }

    public static class EndParser extends BasicParser<Predicate<Integer>> {
        public EndParser(ParserContext context) {
            super(EndParser.class, context);
        }

        @Override
        public Rule Rule() {
            InclusiveEndParser inclusiveEndParser = parserContext().parser(InclusiveEndParser.class);
            SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
            NumberParser numberParser = parserContext().parser(NumberParser.class);
            return FirstOf(
                    Sequence(
                            String("Inf"),
                            spacingParser.Rule(),
                            inclusiveEndParser.Rule(),
                            run(inclusiveEndParser.pop()),
                            push(new InfiniteLimit())
                    ),
                    Sequence(
                            numberParser.Rule(),
                            spacingParser.Rule(),
                            inclusiveEndParser.Rule(),
                            push(new StaticUpperLimit(inclusiveEndParser.pop(), numberParser.pop()))
                    )
            );
        }

        boolean run(Object value) {
            return true;
        }
    }

    public static class InclusiveStartParser extends BasicParser<Boolean> {
        public InclusiveStartParser(ParserContext context) {
            super(InclusiveStartParser.class, context);
        }

        @Override
        public Rule Rule() {
            return FirstOf(
                    Sequence(
                            String("["),
                            push(true)
                    ),
                    Sequence(
                            String("]"),
                            push(false)
                    )
            );
        }
    }

    public static class InclusiveEndParser extends BasicParser<Boolean> {
        public InclusiveEndParser(ParserContext context) {
            super(InclusiveEndParser.class, context);
        }

        @Override
        public Rule Rule() {
            return FirstOf(
                    Sequence(
                            String("]"),
                            push(true)
                    ),
                    Sequence(
                            String("["),
                            push(false)
                    )
            );
        }
    }
}
