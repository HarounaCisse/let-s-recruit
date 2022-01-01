package com.maliware.let.srecruit.shared;

import java.util.Random;
import java.util.function.Supplier;

public class CodeGeneratorProcessing {
    public static Supplier<String> cvCodeGen = () ->
            "DEPT-TIC-" + Math.abs(new Random().nextInt());
}
