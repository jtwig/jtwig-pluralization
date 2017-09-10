package org.jtwig.plural.parse.parsky.interval;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.Transformations;

public class InfiniteSequenceMatcher extends TransformSequenceMatcher<Predicate<Integer>> {
    public InfiniteSequenceMatcher() {
        super(SequenceMatchers.string("Inf"), Transformations.constant(Predicates.<Integer>alwaysTrue()));
    }
}
