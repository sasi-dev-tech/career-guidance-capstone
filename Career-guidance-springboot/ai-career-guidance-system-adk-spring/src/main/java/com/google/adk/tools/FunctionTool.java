package com.google.adk.tools;

public interface FunctionTool<I, O> {
    String getName();
    O execute(I input);
}
