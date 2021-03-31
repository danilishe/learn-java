package com.acme.dbo.txlog;

import static java.lang.System.*;

public class Facade {

    public static final String PRIMITIVE_PREFIX = "primitive: ";
    public static final String ARRAY_PREFIX = "primitives array: ";
    public static final String CHAR_PREFIX = "char: ";
    public static final String STRING_PREFIX = "string: ";
    public static final String REFERENCE_PREFIX = "reference: ";
    public static final String PRIMITIVE_POSTFIX = "";
    public static final String REFERENCE_POSTFIX = "";
    public static final String STRING_POSTFIX = "";
    public static final String CHAR_POSTFIX = "";
    public static final String MATRIX_PREFIX = "primitives matrix: ";

    private static String strPattern  = "";
    private static int repetitionCount = 1;
    private static int numericAccum = 0;
    private static String stringAccum = "";
    private static boolean fNumericPrinting = false;

    public static void log(int message) {
        numericAccum = numericAccum + message;
        fNumericPrinting = true;
        printMessage(decorate(PRIMITIVE_PREFIX,message, PRIMITIVE_POSTFIX));
    }

    public static void log(byte message) {
        numericAccum = numericAccum + message;
        fNumericPrinting = true;
        printMessage(decorate(PRIMITIVE_PREFIX,message, PRIMITIVE_POSTFIX));
    }

    public static void log(char message) {
        printMessage(decorate(CHAR_PREFIX,message, CHAR_POSTFIX));
    }

    public static void log(String message) {
        if (strPattern.isEmpty()) {
            strPattern = message;
            stringAccum = stringAccum + message;
        } else {
           if (strPattern.equals(message)) repetitionCount++;
           else stringAccum = stringAccum + message;
        }

      //  printMessage(decorate(STRING_PREFIX,message, STRING_POSTFIX));
    }

    public static void log(boolean message) {
        printMessage(decorate(PRIMITIVE_PREFIX,message, PRIMITIVE_POSTFIX));
    }

    public static void log(Object message) {
        printMessage(decorate(REFERENCE_PREFIX,message, REFERENCE_POSTFIX));
    }

    public static void log(int[] message) {
        stringAccum = "{";
        for (int i = 0; i < message.length; i++) {
            stringAccum = stringAccum + message[i];
            if (i < message.length-1) stringAccum = stringAccum + ", ";
        }
        stringAccum = stringAccum + "}";
        printMessage(decorate(ARRAY_PREFIX,stringAccum, PRIMITIVE_POSTFIX));
    }

    public static void log(int[][] message) {
        stringAccum = "{\n";
        for (int i = 0; i < message.length; i++) {
            stringAccum = stringAccum + "{";
            for (int j = 0; j < message[i].length; j++){
                stringAccum = stringAccum + message[i][j];
                if (j < message[i].length-1) stringAccum = stringAccum + ", ";
            }
            stringAccum = stringAccum + "}\n";
        }
        stringAccum = stringAccum + "}";
        printMessage(decorate(MATRIX_PREFIX,stringAccum, PRIMITIVE_POSTFIX));
    }

    private static String decorate(String prefix,Object message,String postfix) {
        return prefix + message + postfix;
    }

    private static void printMessage(String message) {
        if (!fNumericPrinting) {
            out.print(message + "\n");
        }
    }

    public static void flush( ) {
        if (fNumericPrinting) {
            out.print(decorate(PRIMITIVE_PREFIX,numericAccum, PRIMITIVE_POSTFIX) + lineSeparator());
            fNumericPrinting = false;
            numericAccum = 0;
        } else {
            if (!stringAccum.isEmpty());
            {
                if (repetitionCount > 1) {
                    out.print(decorate(STRING_PREFIX,strPattern + " (x"+ repetitionCount +")", STRING_POSTFIX) + lineSeparator());
                } else {
                    out.print(decorate(STRING_PREFIX,stringAccum, STRING_POSTFIX) + lineSeparator());
                }

                repetitionCount = 1;
                stringAccum = "";
                strPattern = "";
            }
        }
    }

    public static void log(String... message) {
        stringAccum = "";
        for (String current:message) {
            stringAccum = stringAccum + current + "\n";
            printMessage(decorate(MATRIX_PREFIX,stringAccum, PRIMITIVE_POSTFIX));
        }
    }

    public static void log(long... message) {
        numericAccum = 0;
        for (int i = 0; i < message.length; i++) {
             numericAccum = numericAccum + (int) message[i];
        }
        printMessage(decorate(ARRAY_PREFIX,numericAccum, PRIMITIVE_POSTFIX));
    }
}
