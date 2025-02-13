package net.bodkasoft.billiards.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Pair<K, V> {
    public final K key;
    public final V value;
}
