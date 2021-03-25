package ru.luifuooj;

import java.util.HashSet;

/**
 * Расширение Set для возможности отсортировать по размеру.
 * @param <T> generic Type
 */
public class ComparableSet<T> extends HashSet<T> implements Comparable<HashSet<T>>{

    @Override
    public int compareTo(HashSet<T> o) {
        return this.size() - o.size();
    }
}
