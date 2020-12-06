package com.humuson.huboard.model.dto;


import java.util.HashMap;
import java.util.Map;

public class CounterDto<T> {
	Map<T,Integer> counts = new HashMap<>();
	
	public void add(T t) {
        counts.merge(t, 1, Integer::sum);
    }

    public int count(T t) {
        return counts.getOrDefault(t, 0);
    }
}