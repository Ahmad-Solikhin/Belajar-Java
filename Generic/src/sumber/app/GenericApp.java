package sumber.app;

import sumber.MyData;

public class GenericApp {
    public static void main(String[] args) {

        MyData<String> stringMyData  = new MyData<>("Data 1");
        MyData<Integer> integerMyData = new MyData<>(1);

        String stringValue = stringMyData.getData();
        Integer integerValue = integerMyData.getData();

        System.out.println(stringValue);
        System.out.println(integerValue);

    }
}
