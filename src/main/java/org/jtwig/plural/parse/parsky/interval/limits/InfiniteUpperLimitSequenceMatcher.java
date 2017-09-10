package org.jtwig.plural.parse.parsky.interval.limits;

import org.jtwig.plural.model.limits.InfiniteLimit;
import org.jtwig.plural.parse.parsky.interval.InfiniteSequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.Transformations;

public class InfiniteUpperLimitSequenceMatcher extends TransformSequenceMatcher<InfiniteLimit> {
    public InfiniteUpperLimitSequenceMatcher() {
        super(SequenceMatchers.sequence(
                new InfiniteSequenceMatcher(),
                SequenceMatchers.skipWhitespaces(new InclusiveSequenceMatcher(false))
        ), Transformations.<InfiniteLimit>constant(new InfiniteLimit()));
    }
}
