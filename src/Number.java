import java.util.HashMap;
import java.util.Map;

class Number {
    public static boolean isArabian(String value) {
        return value.matches("[0-9]+");
    }
    public static boolean isRoman(String value) {
        return value.matches("[IVXLCDM]+");
    }
    public static int convertToArabic(String value) {
        int result = 0;

        if (value == null || value.length() == 0) {
            return 0;
        }

        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);

        for (int i = 0; i < value.length() - 1; i++) {
            if (charMap.get(value.charAt(i)) >= charMap.get(value.charAt(i + 1))) {
                result += charMap.get(value.charAt(i));
            } else {
                result -= charMap.get(value.charAt(i));
            }
        }

        result += charMap.get(value.charAt(value.length() - 1));

        return result;
    }

    public static String convertToRoman(int value) {
        String result = "";

        while (value > 0) {
            while (value >= 1000) {
                result += "M";
                value -= 1000;
            }
            while (value >= 900) {
                result += "CM";
                value -= 900;
            }
            while (value >= 500) {
                result += "D";
                value -= 500;
            }
            while (value >= 400) {
                result += "CD";
                value -= 400;
            }
            while (value >= 100) {
                result += "C";
                value -= 100;
            }
            while (value >= 90) {
                result += "XC";
                value -= 90;
            }
            while (value >= 50) {
                result += "L";
                value -= 50;
            }
            while (value >= 40) {
                result += "XL";
                value -= 40;
            }
            while (value >= 10) {
                result += "X";
                value -= 10;
            }
            while (value >= 9) {
                result += "IX";
                value -= 9;
            }
            while (value >= 5) {
                result += "V";
                value -= 5;
            }
            while (value >= 4) {
                result += "IV";
                value -= 4;
            }
            while (value >= 1) {
                result += "I";
                value -= 1;
            }
        }

        return result;
    }
}
