package org.jtwig.plural.model.limits;

import com.google.common.base.Predicate;

public class IntervalPredicate implements Predicate<Integer> {
    private final Predicate<Integer> leftLimit;
    private final Predicate<Integer> rightLimit;

    public IntervalPredicate(Predicate<Integer> leftLimit, Predicate<Integer> rightLimit) {
        if (leftLimit instanceof StaticLowerLimit && rightLimit instanceof StaticUpperLimit) {
            int lowerLimit = ((StaticLowerLimit) leftLimit).getLimit();
            int upperLimit = ((StaticUpperLimit) rightLimit).getLimit();
            if (lowerLimit > upperLimit) {
                throw new IllegalArgumentException(String.format("Invalid interval starting with %d and ending in %d", lowerLimit, upperLimit));
            }
        }
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    @Override
    public boolean apply(Integer input) {
        return leftLimit.apply(input) && rightLimit.apply(input);
    }
}
