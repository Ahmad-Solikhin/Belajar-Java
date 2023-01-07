package sumber.app;

import sumber.ArrayHelper;

public class ArrayHelperApp {
    public static void main(String[] args) {
        String[] names = {"Ahmad", "Solikhin", "Gayuh", "Raharjo"};
        Integer[] numbers = {1, 2, 3, 4};

        System.out.println(ArrayHelper.count(names));
        System.out.println(ArrayHelper.count(numbers));
    }
}
