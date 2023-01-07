package app;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetApp {

    public static enum Gender{
        MALE, FEMALE
    }

    public static void main(String[] args) {
        Set<Gender> genders = EnumSet.allOf(Gender.class);

        for (var v : genders){
            System.out.println(v);
        }

        Gender[] values = Gender.values();
        System.out.println(values);
    }
}
