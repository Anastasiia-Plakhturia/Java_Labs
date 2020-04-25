package org.java.solution.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import static org.java.solution.exceptions.UtilityClass.checkEmailExistence;

/**
 * Написати регулярне вираження, що визначає чи є даний рядок валидным E-mail адресою згідно RFC під номером 2822.
 * Приклад правильних виражень: mail@mail.ru, valid@megapochta.com.
 * Приклад неправильних виражень: bug@@@com.ru, @val.ru, Just Text2.
 */

// пы.сы. для проверки на эксепшен нажать клавишу Enter после запроса на ввод имейла
public class Task2 {

    public static void main()  throws IOException {
        String email = "one";
        Validator validator = new Validator();
        validator.validate(email);
    }

    static class Validator {

        boolean validate(String email) throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите e-mail:");
            if (email =="one"){
            email = input.readLine();}

            try {
                checkEmailExistence(email);
            } catch (EmptyRowException exc) {
                System.out.println("Вы ввели пустую строку. Повторите попытку.");
                return validate(email);
            }

            if (isEmailMatches(email)) {
                System.out.printf("email %s  валидный!", email);
                return true;
            } else {
                System.out.printf("email %s не валидный :)", email);
                return false;
            }
        }

        public boolean isEmailMatches(String s) {
            String regex = "(\\w+)@(\\w+\\.)(\\w+)(\\.\\w+)*";
            return Pattern.matches(regex, s);
        }
    }
}
