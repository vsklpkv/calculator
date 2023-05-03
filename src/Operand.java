class Operand {
    private final String operand;
    private final byte type;
    private final int value;

    public Operand(String operand) throws Exception {
        this.operand = operand;
        this.type = setType();
        this.value = setValue();
    }

    private byte setType() throws Exception {
        if (Number.isArabian(operand)) {
            return 1;
        }
        if (Number.isRoman(operand)) {
            return 2;
        }
        throw new Exception("Операнд \"" + operand + "\" не удовлетворяет заданию - целые положительные арабские или римские числа");
    }

    public byte getType() {
        return type;
    }

    private int setValue() throws Exception {
        int value = convertValue(operand);
        if (value < 1 || value > 10) {
            throw new Exception("Операнд \"" + operand + "\" не удовлетворяет заданию - число от 1 до 10");
        }
        return value;
    }

    private int convertValue(String value) {
        if (type == 2) {
            return Number.convertToArabic(operand);
        }
        return Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }
}
