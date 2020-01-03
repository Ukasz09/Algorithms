package com.Ukasz09.github.onp;

import com.Ukasz09.github.utilities.ListStack;
import com.Ukasz09.github.utilities.IStack;

import java.util.ArrayList;

public abstract class CalculatorONP {
    public static final String ADDITION_SIGN = "+";
    public static final String SUBTRACTION_SIGN = "-";
    public static final String MULTIPLICATION_SIGN = "*";
    public static final String DIVISION_SIGN = "/";
    public static final String EXPONENTIATION_SIGN = "^";
    public static final String ROOTS_SIGN = "sqrt";
    public static final String LOGARITHM_SIGN = "log";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void divideStringToWords(String textLine, ArrayList inputsElements) {
        inputsElements.clear();
        String tmpElement = "";
        for (int i = 0; i < textLine.length(); i++) {
            if (textLine.charAt(i) == ' ') {
                inputsElements.add(tmpElement);
                tmpElement = "";
            } else tmpElement += textLine.charAt(i);
        }

        if (!tmpElement.equals(" "))
            inputsElements.add(tmpElement);
    }

    private static boolean wordIsNumber(String word) {
        try {
            Double.parseDouble(word);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static double doOperation(String operation, double lastVal, double preLastVal) {
        switch (operation) {
            case ADDITION_SIGN:
                return preLastVal + lastVal;
            case SUBTRACTION_SIGN:
                return preLastVal - lastVal;
            case MULTIPLICATION_SIGN:
                return preLastVal * lastVal;
            case DIVISION_SIGN: {
                if (lastVal == 0) {
                    System.err.println("Dzielenie przez 0");
                    throw new IllegalArgumentException();
                }
                return preLastVal / lastVal;
            }
            case EXPONENTIATION_SIGN:
                return Math.pow(preLastVal, lastVal);
            case ROOTS_SIGN:
                return Math.pow(preLastVal, 1 / lastVal);
            case LOGARITHM_SIGN: {
                return Math.log(preLastVal) / Math.log(lastVal);
            }

            default:
                throw new IllegalArgumentException();
        }
    }

    public static double calculate(String input) {
        ArrayList<String> inputsElements = new ArrayList<>();
        ListStack<Double> listStack = new ListStack<>();
        divideStringToWords(input, inputsElements);
        double lastElem;
        double preLastElem;
        double resultOfOperation;
        for (String elem : inputsElements) {
            if (wordIsNumber(elem))
                listStack.push(Double.parseDouble(elem));
            else {
                try {
                    lastElem = listStack.pop();
                    preLastElem = listStack.pop();
                } catch (IStack.EmptyStackException e) {
                    throw new IllegalArgumentException();
                }
                resultOfOperation = doOperation(elem, lastElem, preLastElem);
                listStack.push(resultOfOperation);
            }
        }

        double retVal;
        try {
            retVal = listStack.pop();
        } catch (IStack.EmptyStackException e) {
            throw new IllegalArgumentException();
        }
        return retVal;
    }
}
