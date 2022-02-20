package nextstep.subway.domain.fare;

import nextstep.subway.domain.DiscountType;
import nextstep.subway.domain.Fare;

public class DiscountPolicy implements FarePolicy {

    private DiscountPolicy() {
    }

    private static class DiscountPolicyHolder {
        private static final DiscountPolicy INSTANCE = new DiscountPolicy();
    }

    public static DiscountPolicy getInstance() {
        return DiscountPolicy.DiscountPolicyHolder.INSTANCE;
    }

    @Override
    public Fare apply(Fare fare, FareParams fareParams) {
        DiscountType discountType = DiscountType.from(fareParams.getAge());
        int discountFare = discountType.discountFare(fare.getDistanceFare());
        return fare.discount(discountFare);
    }

}