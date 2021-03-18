package ru.luifuooj;

import ru.luifuooj.utils.ByteConverter;

public class Main {
    public static void main(String[] args) {
        ByteConverter myByteConverter = new ByteConverter(10240865L);
        System.out.println(myByteConverter.humanReadable());
    }
}
