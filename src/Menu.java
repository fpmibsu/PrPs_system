import Models.*;

import java.nio.file.OpenOption;
import java.util.ArrayList;
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
            switch (input) {
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
        System.out.println("[2] - кафедра");
        System.out.println("[3] - декан");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return InfoType.values()[Integer.parseInt(input) - 1];
    }

    private static void changeInformation(ChangeInfoType operationType) {
        InfoType infoType = informationMenu();

        Scanner scanner = new Scanner(System.in);

        switch (operationType) {
            case delete:
                System.out.println("Введите код на удаление");
                String code = scanner.nextLine();

                if (database.deleteSomeInfo(code, infoType)) {
                    System.out.println("успешно удалено!");
                } else {
                    System.out.println("Ошибка!");
                }
                break;
            case update:
            case add:
                switch (infoType) {
                    case head:
                        System.out.println("Декан: ");
                        System.out.println("Введите ФИО: ");
                        String fio = scanner.nextLine();
                        System.out.println("Введите годы");
                        String years = scanner.nextLine();
                        System.out.println("Введите ученое завнпеи");
                        String degree = scanner.nextLine();
                        System.out.println("Введите факультет: ");
                        String fuc = scanner.nextLine();
                        HeadInfo item = new HeadInfo(fio, years, degree, fuc);

                        database.operationWithHeadInfo(operationType, item);

                        Boolean result = database.operationWithHeadInfo(operationType, item);

                        if (result) {
                            System.out.println("успешно!");
                        } else {
                            System.out.println("Ошибка!");
                        }

                        break;
                    case facult:
                    case pulpit:
                        System.out.println((infoType == InfoType.facult ? "Факультет: " : "Кафедра:"));

                        System.out.println("Введите название: ");
                        String name = scanner.nextLine();

                        System.out.println("Введите кортокое название");
                        String shortName = scanner.nextLine();

                        System.out.println("Введите адрес: ");
                        String address = scanner.nextLine();

                        System.out.println("Введите телефон");
                        String telephone = scanner.nextLine();

                        System.out.println("Введите сайт");
                        String site = scanner.nextLine();

                        if (infoType == InfoType.facult) {
                            result = database.operationWithEducUnit(operationType,
                                    infoType,
                                    name,
                                    shortName,
                                    address,
                                    telephone,
                                    site,
                                    null);

                            if (result) {
                                System.out.println("успешно!");
                            } else {
                                System.out.println("Ошибка!");
                            }
                            break;
                        }

                        System.out.println("Введите название факультета");
                        String facultyName = scanner.nextLine();

                        result = database.operationWithEducUnit(operationType,
                                infoType,
                                name,
                                shortName,
                                address,
                                telephone,
                                site,
                                facultyName);

                        if (result) {
                            System.out.println("успешно!");
                        } else {
                            System.out.println("Ошибка!");
                        }

                        break;
                }
                break;
        }


    }

    private static void userMenu() {
        while (true) {
            System.out.println("------абитуриент------");
            System.out.println("Выберите действие: ");
            System.out.println("[1] - список спеуиальностей и план приема");
            System.out.println("[2] - наименование специальностей, с макс балом по матиматике");
            System.out.println("[3] - список руковадителей ");
            System.out.println("[0] - выход");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if ("0".equals(input)) {
                break;
            }

            System.out.println("Введите интерес год");
            String year = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Форма обучкения дневна?");
                    String isDaily = scanner.nextLine();

                    ArrayList<PlanInfo> planInfos = database.getSpecialitiesWithPlan(year,
                            ("Да".equals(isDaily) ? Boolean.TRUE : Boolean.FALSE));

                    if (planInfos.isEmpty()) {
                        System.out.println("Ничего не найдено");
                    }
                    for (PlanInfo planInfo : planInfos) {
                        System.out.println("---------------------------");
                        System.out.println(planInfo);
                    }
                    break;
                case "2":
                    ArrayList<SpecialityInfo> specs = database.getSpecialitiesWithMaxMathGrade(year);

                    if (specs.isEmpty()) {
                        System.out.println("Ничего не найдено");
                    }
                    for (SpecialityInfo spec : specs) {
                        System.out.println("---------------------------");
                        System.out.println("Специальность:");
                        System.out.println(spec);
                    }
                    break;
                case "3":
                    ArrayList<HeadInfo> heads = database.getHeadsOfEducationUnit(year);
                    if (heads.isEmpty()) {
                        System.out.println("Ничего не найдено");
                    }
                    for (HeadInfo head : heads) {
                        System.out.println("---------------------------");
                        System.out.println("Декан:");
                        System.out.println(head);
                    }
                    break;
            }

        }
    }


    public static void main(String[] args) {

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