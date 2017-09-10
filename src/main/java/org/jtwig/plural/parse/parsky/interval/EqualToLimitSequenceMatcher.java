package org.jtwig.plural.parse.parsky.interval;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.jtwig.plural.parse.parsky.number.NumberPredicateSequenceMatcher;
import org.jtwig.plural.parse.parsky.number.NumberSequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformations;

public class EqualToLimitSequenceMatcher extends TransformSequenceMatcher<Predicate<Integer>> {
    public EqualToLimitSequenceMatcher() {
        super(SequenceMatchers.sequence(
                SequenceMatchers.string("{"),
                SequenceMatchers.skipWhitespaces(
                        new NumberPredicateSequenceMatcher(
                                new NumberSequenceMatcher()
                        )
                ),
                SequenceMatchers.string("}")
        ), Transformations.fromContentList(new Function<ListContentTransformation.Request, Predicate<Integer>>() {
            @Override
            public Predicate<Integer> apply(ListContentTransformation.Request input) {
                return (Predicate<Integer>) input.get(1);
            }
        }));
    }
}
