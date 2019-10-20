package tk.leaflame.app.optional;

import java.util.Optional;

public class OptionalInAction {

    private static String getInsuranceNameByOptional(Person person){
        return Optional.ofNullable(person).flatMap(Person::getCar)
                .flatMap(Car::getInsurance).map(Insurance::getName)
                .orElse("Unknown");
    }

    public static void main(String[] args) {
//        System.out.println(getInsuranceNameByOptional(null));
        Optional.of(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }
}
