import java.util.Scanner;

public class Menu {

    enum UserType {
        administrator,
        user
    }

    private static UserType getUserType() {
        System.out.println("Выберите тип пользователя: ");
        System.out.println("[1] - администратор");
        System.out.println("[2] - абитуриент");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if ("1".equals(input)) {
            return UserType.administrator;
        }
        return UserType.user;
    }

    public static void main(String [] args) {
        UserType userType = getUserType();
    }

}