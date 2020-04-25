package org.java.solution.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.java.solution.exceptions.UtilityClass.checkContentExistence;
import static org.java.solution.exceptions.UtilityClass.checkLength;


/**
 * З тексту вилучити всі слова заданої довжини, що починаються на приголосну букву.
 */
public class Task1 {

    static final String[] CONSONANTS = {"б", "в", "г", "д", "ж", "з", "к", "л", "м", "н", "п", "р", "с", "т", "ф", "х", "ш", "щ", "ч", "ц", "й"};
    static final String FILE_NAME ="утро.txt";

    public static void main() {
        Runner creator = new Runner();
        List<String> resultList = creator.createResultList();
        resultList.forEach(System.out::println);
    }

    static class Runner {

        private  List<String> resultList;

        public Runner() {
            resultList = new ArrayList<>();
        }

        public List<String> createResultList() {
            try (BufferedReader br = new BufferedReader(getFileFromResources(FILE_NAME))) {
                String str = br.readLine();

                try {
                    checkContentExistence(str);
                } catch (EmptyFileException exc) {
                    throw new InvalidDataException("Файл пустой.");
                }

                int length;
                try {
                    length = readLength();
                    checkLength(length);
                } catch (NumberFormatException exc) {
                    System.out.println("Некорректная длина.");
                    return createResultList();
                }

                while (str != null) {

                    str = str.replaceAll(",", " ").replaceAll("\\.", " ").replaceAll("\\?", " ");
                    String[] words = str.split(" ");
                    str = br.readLine();

                    for (String word : words) {
                        int d = word.length() - 1;
                        if (d == (length - 1)) {
                            findWordStartsWithConsonant(word);
                        }
                    }

                }
            } catch (IOException exc) {
                throw new InvalidDataException("Проблема с открытием файла. Повторите попытку.");
            }
            return resultList;
        }

        public int readLength() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("Введите длину:");
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException exc) {
                throw new InvalidDataException("Некорректная длина.");
            }
        }

        public void findWordStartsWithConsonant(String word) {
            for (String element : Task1.CONSONANTS) {
                if (word.startsWith(element)) {
                    resultList.add(word);
                }
            }
        }

        Reader getFileFromResources(String fileName) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(fileName);
            if (Objects.isNull(is)) {
                throw new InvalidDataException("Проблема с открытием файла. Проверьте наличие файла");
            }

            return new InputStreamReader(is);
        }
    }
}
