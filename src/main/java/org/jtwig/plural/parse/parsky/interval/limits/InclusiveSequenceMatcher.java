package org.jtwig.plural.parse.parsky.interval.limits;

import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.Transformations;

public class InclusiveSequenceMatcher extends TransformSequenceMatcher<Boolean> {
    public InclusiveSequenceMatcher(boolean start) {
        super(SequenceMatchers.firstOf(
                SequenceMatchers.transform(
                        SequenceMatchers.string(start ? "[" : "]"),
                        Transformations.constant(true)
                ),
                SequenceMatchers.transform(
                        SequenceMatchers.string(start ? "]" : "["),
                        Transformations.constant(false)
                )
        ), Transformations.as(Boolean.class));
    }
}
