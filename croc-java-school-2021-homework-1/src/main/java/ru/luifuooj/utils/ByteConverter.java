package ru.luifuooj.utils;

public class ByteConverter {
    private double bytes;


    public ByteConverter(double bytes) {
        this.bytes = bytes;
    }

    public String humanReadable() {
        int counter = 0;
        double localBytes = bytes;

        while (localBytes >= 1024) {
            localBytes /= 1024;
            counter++;
        }

        return String.format("%.1f " + byteValue(counter), localBytes);
    }

    private String byteValue(int counter) {
        switch (counter) {
            case 0:
                return "B";
            case 1:
                return "KB";
            case 2:
                return "MB";
            case 3:
                return "GB";
            case 4:
                return "TB";
            case 5:
                return "PB";
            case 6:
                return "EB";
            case 7:
                return "ZB";
            case 8:
                return "YB";
            default: return "None";
        }
    }
}
