import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String ADD_COMMAND = "add";
    private static final String SUBTRACT_COMMAND = "subtract";
    private static final String MULT_COMMAND = "multiply";
    private static final String DIVIDE_COMMAND = "divide";
    private static final String FIB_COMMAND = "fibonacci";
    private static final String BIN_COMMAND = "toBinary";

    private static final String INVALID_COMMAND_RETURN = "Invalid command";

    private static final Calculator calculator = new Calculator();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Enter command (\"exit\" to quit):");
        do {
            command = scanner.nextLine();
            if (!command.equals("exit")) {
                String output = readCommand(command.split(" "));
                System.out.println(output);
            }
        } while (!command.equals("exit"));
    }

    static private String readCommand(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            nums.add(Integer.parseInt(args[i]));
        }
        switch (args[0]) {
            case ADD_COMMAND:
                return String.valueOf(calculator.add(nums.get(0), nums.get(1)));
            case SUBTRACT_COMMAND:
                return String.valueOf(calculator.subtract(nums.get(0), nums.get(1)));
            case MULT_COMMAND:
                return String.valueOf(calculator.multiply(nums.get(0), nums.get(1)));
            case DIVIDE_COMMAND:
                return String.valueOf(calculator.divide(nums.get(0), nums.get(1)));
            case FIB_COMMAND:
                return String.valueOf(calculator.fibonacciNumberFinder(nums.get(0)));
            case BIN_COMMAND:
                return calculator.intToBinaryNumber(nums.get(0));
        }
        return INVALID_COMMAND_RETURN;
    }
}
