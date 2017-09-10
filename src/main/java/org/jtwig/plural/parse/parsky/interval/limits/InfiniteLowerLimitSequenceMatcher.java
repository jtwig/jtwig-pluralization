package org.jtwig.plural.parse.parsky.interval.limits;

import org.jtwig.plural.model.limits.InfiniteLimit;
import org.jtwig.plural.parse.parsky.interval.InfiniteSequenceMatcher;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.Transformations;

public class InfiniteLowerLimitSequenceMatcher extends TransformSequenceMatcher<InfiniteLimit> {
    public InfiniteLowerLimitSequenceMatcher() {
        super(SequenceMatchers.sequence(
                new InclusiveSequenceMatcher(true),
                SequenceMatchers.skipWhitespaces(new InfiniteSequenceMatcher())
        ), Transformations.<InfiniteLimit>constant(new InfiniteLimit()));
    }
}
