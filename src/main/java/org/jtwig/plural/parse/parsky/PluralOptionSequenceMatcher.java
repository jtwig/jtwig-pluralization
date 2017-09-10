package org.jtwig.plural.parse.parsky;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.jtwig.plural.model.PluralOption;
import org.jtwig.plural.parse.parsky.interval.EqualToLimitSequenceMatcher;
import org.jtwig.plural.parse.parsky.interval.IntervalSequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformations;

import static com.google.common.base.Predicates.alwaysTrue;

public class PluralOptionSequenceMatcher extends TransformSequenceMatcher<PluralOption> {
    public PluralOptionSequenceMatcher() {
        super(SequenceMatchers.sequence(
                SequenceMatchers.skipWhitespaces(SequenceMatchers.firstOf(
                        new IntervalSequenceMatcher(),
                        new EqualToLimitSequenceMatcher(),
                        SequenceMatchers.constant(alwaysTrue())
                )),
                new MessageSequenceMatcher()
        ), Transformations.fromContentList(new Function<ListContentTransformation.Request, PluralOption>() {
            @Override
            public PluralOption apply(ListContentTransformation.Request input) {
                return new PluralOption(input.get(1, String.class), (Predicate<Integer>) input.get(0));
            }
        }));
    }
}
