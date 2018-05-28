import java.util.Scanner;

public class Menu {

    private static DBWrapper database;

    enum UserType {
        administrator,
        user
    }

    enum ChangeInfoType {
        delete,
        update,
        add
    }

    enum InfoType {
        facult,
        pulpit,
        head
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
                    changeInformation(ChangeInfoType.add);
                    break;
                case "2":
                    changeInformation(ChangeInfoType.delete);
                    break;
                case "3":
                    changeInformation(ChangeInfoType.update);
                    break;
            }
            if ("0".equals(input)) {
                break;
            }
        }
    }

    private static InfoType informationMenu() {
        System.out.println("Информация: ");
        System.out.println("[1] - фаукультет");
        System.out.println("[2] - специальность");
        System.out.println("[3] - декан");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return InfoType.values()[Integer.parseInt(input)-1];
    }

    private static void changeInformation(ChangeInfoType operationType) {
        InfoType infoType = informationMenu();

        database.operationWitInfo(operationType, infoType);
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

        database = new DBWrapper();

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