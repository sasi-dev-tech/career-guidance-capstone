package com.career.navigator.memory;
import java.util.HashMap;
import java.util.Map;
public class MemoryBank {
    private static final Map<String,String> STORE = new HashMap<>();
    public static void save(String key,String value){STORE.put(key,value);} 
    public static String read(String key){return STORE.getOrDefault(key,"No data");}
}
