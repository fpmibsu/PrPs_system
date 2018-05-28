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


    private static void administratorMenu() {
        while (true) {
            System.out.println("------администратор------");
            System.out.println("Выберите действие: ");
            System.out.println("[1] - добавиить информацию");
            System.out.println("[2] - удалить информацию");
            System.out.println("[3] - обновить информацию");
            System.out.println("[0] - выход");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
            }
            if ("0".equals(input)) {
                break;
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("------абитуриент------");
            System.out.println("Выберите действие: ");
            System.out.println("[0] - выход");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
            }
            if ("0".equals(input)) {
                break;
            }
        }
    }


    public static void main(String [] args) {
        UserType userType = getUserType();


        switch (userType) {
            case user:
                userMenu();
                break;
            case administrator:
                administratorMenu();
                break;
        }
    }

}