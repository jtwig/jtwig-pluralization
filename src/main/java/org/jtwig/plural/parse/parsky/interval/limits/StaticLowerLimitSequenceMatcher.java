package org.jtwig.plural.parse.parsky.interval.limits;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.jtwig.plural.model.limits.StaticLowerLimit;
import org.jtwig.plural.parse.parsky.number.NumberSequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformations;

public class StaticLowerLimitSequenceMatcher extends TransformSequenceMatcher<Predicate<Integer>> {
    public StaticLowerLimitSequenceMatcher(NumberSequenceMatcher numberSequenceMatcher) {
        super(SequenceMatchers.sequence(
                new InclusiveSequenceMatcher(true),
                SequenceMatchers.skipWhitespaces(numberSequenceMatcher)
        ), Transformations.fromContentList(new Function<ListContentTransformation.Request, Predicate<Integer>>() {
            @Override
            public Predicate<Integer> apply(ListContentTransformation.Request input) {
                return new StaticLowerLimit(input.get(0, Boolean.class), input.get(1, Integer.class));
            }
        }));
    }
}
