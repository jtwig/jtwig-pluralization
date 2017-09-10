package org.jtwig.plural.parse.parsky.interval.limits;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.jtwig.plural.model.limits.StaticUpperLimit;
import org.jtwig.plural.parse.parsky.number.NumberSequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformations;

public class StaticUpperLimitSequenceMatcher extends TransformSequenceMatcher<Predicate<Integer>> {
    public StaticUpperLimitSequenceMatcher(NumberSequenceMatcher numberSequenceMatcher) {
        super(SequenceMatchers.sequence(
                numberSequenceMatcher,
                SequenceMatchers.skipWhitespaces(new InclusiveSequenceMatcher(false))
        ), Transformations.fromContentList(new Function<ListContentTransformation.Request, Predicate<Integer>>() {
            @Override
            public Predicate<Integer> apply(ListContentTransformation.Request input) {
                return new StaticUpperLimit(input.get(1, Boolean.class), input.get(0, Integer.class));
            }
        }));
    }
}
