package org.java.solution.exceptions;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.java.solution.exceptions.UtilityClass.checkLength;

/**
 * Виконати попарне підсумовування довільного кінцевого ряду чисел
 * у такий спосіб: на першому етапі підсумуються попарно поруч варті числа,
 * на другому етапі підсумуються результати першого етапу і т.д. доти,
 * поки не залишиться одне число.
 */
public class Task3 {

    public static void main (){
        Creator creator = new Creator();
        creator.create();
    }

    static class Creator {

        private List<Integer> list;

        public Creator() {
            list = new ArrayList<>();
        }

        private List<Integer> createCollection() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Введите размер ряда:");

                try {
                    int rowLength = Integer.parseInt(reader.readLine());
                    checkLength(rowLength);

                    if (calculate(reader, rowLength)) {
                        return createCollection();
                    }
                } catch (NumberFormatException exc) {
                    System.out.println("Некорректная длина.");
                    return createCollection();
                }
            } catch (IOException exc) {
                throw new InvalidDataException("Повторите попытку.");
            }
            return list;
        }

        private boolean calculate(BufferedReader reader, int rowLength) throws IOException {
            int i = 0;
            System.out.println("Введите элементы:");
            while (i < rowLength) {

                int elem;
                try {
                    elem = Integer.parseInt(reader.readLine());
                    list.add(elem);
                    i++;
                } catch (NumberFormatException exc) {
                    System.out.println("Невалидное число. Пожалуйста повторите попытку еще раз.");
                    return calculate(reader, rowLength);
                }
            }
            return false;
        }

        private void delete(List<Integer> col, int pos) {
            col.remove(pos - 1);
        }

        private static void sum(List<Integer> col, int z) {
            ListIterator<Integer> iter = col.listIterator();
            int a, b, summa;
            int i = 0;
            while (i < z - 1) {
                a = col.get(i);
                b = col.get(i + 1);
                summa = a + b;
                iter.next();
                iter.set(summa);
                i++;
            }
        }

        void create() {
            List<Integer> col1 = createCollection();
            int z = col1.size();
            while (z > 1) {
                z = col1.size();
                sum(col1, z);
                delete(col1, z);
                print(col1);
            }
        }


        private void print(List<Integer> list) {
            System.out.println("----------------");
            list.forEach(elem -> System.out.printf("Item: %d ", elem));
            System.out.println("");
        }
    }
}