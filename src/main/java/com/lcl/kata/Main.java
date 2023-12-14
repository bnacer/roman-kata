package com.lcl.kata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lcl.kata.service.RomainConversionService;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        if(args == null || args.length != 2){
            logger.error("usage : java main roman IV or java main number 3 ");
            throw new IllegalArgumentException("usage : java main roman IV or java main number 3 ");
        }
        switch (args[0]){
            case "roman" -> System.out.println(args[1] + " -> " + new RomainConversionService().fromRoman(args[1]));
            case "number" -> System.out.println(args[1] + " -> " + new RomainConversionService().toRoman(Integer.parseInt(args[1])));
            default -> System.out.println("Unknown action, usage : java main roman IV or java main number 3 ");
        }
    }
}