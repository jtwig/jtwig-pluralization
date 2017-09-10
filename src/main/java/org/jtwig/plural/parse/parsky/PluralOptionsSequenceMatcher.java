package org.jtwig.plural.parse.parsky;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.jtwig.plural.PluralOptions;
import org.jtwig.plural.model.PluralOption;
import org.parsky.sequence.SequenceMatchers;
import org.parsky.sequence.TransformSequenceMatcher;
import org.parsky.sequence.transform.ListContentTransformation;
import org.parsky.sequence.transform.Transformations;

public class PluralOptionsSequenceMatcher extends TransformSequenceMatcher<PluralOptions> {
    public PluralOptionsSequenceMatcher() {
        super(SequenceMatchers.flatten(
                SequenceMatchers.sequence(
                        new PluralOptionSequenceMatcher(),
                        SequenceMatchers.zeroOrMore(
                                SequenceMatchers.transform(
                                        SequenceMatchers.sequence(
                                                SequenceMatchers.string("|"),
                                                new PluralOptionSequenceMatcher()
                                        ),
                                        Transformations.fromContentList(new Function<ListContentTransformation.Request, Object>() {
                                            @Override
                                            public Object apply(ListContentTransformation.Request input) {
                                                return input.get(1);
                                            }
                                        })
                                )
                        )
                )
        ), Transformations.fromContentList(new Function<ListContentTransformation.Request, PluralOptions>() {
            @Override
            public PluralOptions apply(ListContentTransformation.Request input) {
                return new PluralOptions(Lists.transform(input.getValues(), new Function<Object, PluralOption>() {
                    @Override
                    public PluralOption apply(Object input) {
                        return (PluralOption) input;
                    }
                }));
            }
        }));
    }
}
