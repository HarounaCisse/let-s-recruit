package com.maliware.let.srecruit.shared;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeGeneratorImpl implements CodeGenerator {
    @Override
    public String generateCode() {
        return "DEPT-TIC-" + Math.abs(new Random().nextInt());
    }
}
