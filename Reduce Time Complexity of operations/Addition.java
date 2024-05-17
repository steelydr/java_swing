import java.util.Scanner;

public class Addition {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        String num1 = scanner.nextLine();

        System.out.print("Enter the second number: ");
        String num2 = scanner.nextLine();
        long startTime = System.nanoTime();

        String result = addTwoNumbers(num1, num2);
        long endTime = System.nanoTime();
        long durationNano = endTime - startTime;
        double durationMillis = durationNano / 1_000_000.0;
        System.out.println("Sum: " + result);
        System.out.printf("Duration: %.3f milliseconds%n", durationMillis);

        scanner.close();
    }

    public static String addTwoNumbers(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        StringBuilder result = new StringBuilder();
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        int carry = 0;
        int length = num1.length();
        for (int i = 0; i < length; i++) {
            int digit1 = Character.getNumericValue(num1.charAt(i));
            int digit2 = i < num2.length() ? Character.getNumericValue(num2.charAt(i)) : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.append(sum % 10);
        }
        if (carry > 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
