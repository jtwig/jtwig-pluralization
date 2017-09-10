package org.jtwig.plural.parse.parsky.interval;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.jtwig.plural.model.limits.IntervalPredicate;
import org.jtwig.plural.parse.parsky.interval.limits.InfiniteLowerLimitSequenceMatcher;
import org.jtwig.plural.parse.parsky.interval.limits.InfiniteUpperLimitSequenceMatcher;
import org.jtwig.plural.parse.parsky.interval.limits.StaticLowerLimitSequenceMatcher;
import org.jtwig.plural.parse.parsky.interval.limits.StaticUpperLimitSequenceMatcher;
import org.jtwig.plural.parse.parsky.number.NumberSequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformations;

public class IntervalSequenceMatcher extends TransformSequenceMatcher<IntervalPredicate> {
    public IntervalSequenceMatcher() {
        super(SequenceMatchers.sequence(
                SequenceMatchers.firstOf(
                        new InfiniteLowerLimitSequenceMatcher(),
                        new StaticLowerLimitSequenceMatcher(new NumberSequenceMatcher())
                ),
                SequenceMatchers.skipWhitespaces(SequenceMatchers.string(",")),
                SequenceMatchers.firstOf(
                        new InfiniteUpperLimitSequenceMatcher(),
                        new StaticUpperLimitSequenceMatcher(new NumberSequenceMatcher())
                )
        ), Transformations.fromContentList(new Function<ListContentTransformation.Request, IntervalPredicate>() {
            @Override
            public IntervalPredicate apply(ListContentTransformation.Request input) {
                return new IntervalPredicate(
                        (Predicate<Integer>) input.get(0),
                        (Predicate<Integer>) input.get(2)
                );
            }
        }));
    }
}
