package sumber.app;

import sumber.MyData;

public class Covariant{
    public static void main(String[] args) {
        MyData<String> stringMyData = new MyData<>("Gayuh");
        proses(stringMyData);
    }

    public static void proses(MyData<? extends Object> myData){

    }
}