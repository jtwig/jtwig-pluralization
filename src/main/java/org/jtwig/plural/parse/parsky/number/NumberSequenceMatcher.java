package org.jtwig.plural.parse.parsky.number;

import com.google.common.base.Function;
import org.parsky.character.CharacterMatchers;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ContentTransformation;
import org.parsky.sequence.transform.Transformations;

public class NumberSequenceMatcher extends TransformSequenceMatcher<Integer> {
    public NumberSequenceMatcher() {
        super(SequenceMatchers.matchedText(
                SequenceMatchers.sequence(
                        SequenceMatchers.optional(SequenceMatchers.string("-")),
                        SequenceMatchers.oneOrMore(SequenceMatchers.match(
                                CharacterMatchers.range('0', '9')
                        ))
                )
        ), Transformations.fromString(new Function<ContentTransformation.Request<String>, Integer>() {
            @Override
            public Integer apply(ContentTransformation.Request<String> input) {
                return Integer.parseInt(input.getValue());
            }
        }));
    }
}
