import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите выражение: ");
        String input = reader.readLine();
        System.out.println("Ответ: " + calc(input));
    }

    public static String calc(String input) throws Exception {
        input = input.replace(" ","");
        String operatorPattern = "[\\+\\-\\/\\*]";
        List<Operand> operands = setOperands(input, operatorPattern);
        byte operandsType = setOperandsType(operands);
        Integer[] operandsValues = setOperandsValues(operands);
        operands.clear();
        String operator = setOperator(input, operatorPattern);
        int aggregate = aggregate(operandsValues, operator);
        return convert(aggregate, operandsType);
    }

    private static List<Operand> setOperands(String input, String operatorsPattern) throws Exception {
        if (input.substring(0, 1).equals("-")) {
            throw new Exception("Операнд не удовлетворяет заданию - положительные числа");
        }
        String inputOperands[] = input.split(operatorsPattern);
        if (inputOperands.length != 2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        List<Operand> operands = new ArrayList<>();
        for (String inputOperand:inputOperands) {
            Operand operand = new Operand(inputOperand);
            operands.add(operand);
        }
        return operands;
    }

    private static byte setOperandsType(List<Operand> operands) throws Exception {
        byte operandsType = 0;
        for (Operand operand:operands) {
            byte operandType = operand.getType();
            if (operandsType != 0 && operandsType != operandType) {
                throw new Exception("Используются одновременно разные системы счисления");
            }
            operandsType = operandType;
        }
        return operandsType;
    }

    private static Integer[] setOperandsValues(List<Operand> operands) {
        List<Integer> operandsValues = new ArrayList<>();
        for (Operand operand:operands) {
            operandsValues.add(operand.getValue());
        }
        return operandsValues.toArray(Integer[]::new);
    }

    private static String setOperator(String input, String operatorsPattern) {
        String operator = null;
        Pattern pattern = Pattern.compile(operatorsPattern);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            operator = matcher.group();
        }
        return operator;
    }

    private static int aggregate(Integer[] operands, String operator) {
        int aggregate = operands[0];
        for (int i = 1; i < operands.length; i++) {
            switch (operator) {
                case ("+") -> aggregate += operands[i];
                case ("-") -> aggregate -= operands[i];
                case ("/") -> aggregate /= operands[i];
                case ("*") -> aggregate *= operands[i];
            }
        }
        return aggregate;
    }

    private static String convert(int value, byte type) throws Exception {
        if (type == 2) {
            if (value == 0) {
                throw new Exception("В римской системе нет нуля");
            }
            if (value < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            return Number.convertToRoman(value);
        }
        if (value == 0) {
            return "0";
        }
        return String.valueOf(value);
    }
}
