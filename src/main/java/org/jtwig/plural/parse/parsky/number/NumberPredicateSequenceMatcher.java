package org.jtwig.plural.parse.parsky.number;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ContentTransformation;
import org.parsky.sequence.transform.Transformations;

public class NumberPredicateSequenceMatcher extends TransformSequenceMatcher<Predicate<Integer>> {
    public NumberPredicateSequenceMatcher(NumberSequenceMatcher numberSequenceMatcher) {
        super(numberSequenceMatcher, Transformations.from(new Function<ContentTransformation.Request<Integer>, Predicate<Integer>>() {
            @Override
            public Predicate<Integer> apply(ContentTransformation.Request<Integer> input) {
                return Predicates.equalTo(input.getValue());
            }
        }));
    }
}
