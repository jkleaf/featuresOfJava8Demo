package tk.leaflame.app.optional;

import java.util.Objects;
import java.util.Optional;

public class OptionalUsage {

    private static String getInsuranceByOptional(Insurance insurance){
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
//        return Optional.of(insurance).map(Insurance::getName).orElse("unknown");//NullPointerException
    }

    public static void main(String[] args)
    {
        Optional<Insurance> optionalInsurance=Optional.empty();
        /*optionalInsurance.get();*/
        Optional<Insurance> optionalInsurance11=Optional.of(new Insurance());
        /*optionalInsurance11.get();*/
        Optional<Insurance> optionalInsurance2=Optional.ofNullable(null);
        /*optionalInsurance11.orElseGet(Insurance::new);*/
        /*optionalInsurance11.orElse(new Insurance());*/
        /*optionalInsurance11.orElseThrow(RuntimeException::new);*/
        /*optionalInsurance11.orElseThrow(()->new RuntimeException("no instance"));*/

        /*Insurance insurance=optionalInsurance11.filter(obj -> false).get();//Objects::isNull
        System.out.println(insurance);*/
        Optional<String> optionalName= optionalInsurance11.map(Insurance::getName);
        System.out.println(optionalName.orElse("empty value"));
        System.out.println(optionalName.isPresent());
        System.out.println(getInsuranceByOptional(null));
    }
}
