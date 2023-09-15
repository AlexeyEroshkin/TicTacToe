import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//      Размер игрового поля
        System.out.println("Здравствуйте! Введите размер игрового поля");
        int size = scanner.nextInt();

//        Дать имя игроку
        System.out.println("Введите ваше имя игрок №1");
        scanner.nextLine();
        String player1 = scanner.nextLine();
        System.out.println("Введите ваше имя игрок №2");
        String player2 = scanner.nextLine();
//        Создать игровое поле
//       - ввести размер игрового поля
        /* X  первый игрок
         * O  второй игрок
        * */
        char[][] field = new char[size][size];
//        Заполнить игровое поле черточками
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = '-';
            }
        }
        //        Чья очередь?
        boolean turn = true;
        boolean win = false;
        while (!win) {
//        Нарисовать игровое поле
            drawField(field);

            //      С какого символа начинается игра
            char symbol;
            if (turn) {
                symbol = 'X';
            } else {
                symbol = 'O';
            }

            //        Кто каким символом ходит
            if (turn) {
                System.out.println(player1 + " ходит Х");

            } else {
                System.out.println(player2 + " ходит О");
            }

//        Варианты строки и столбца
            int row = 0, column = 0;
            while (true) {
//        Получить ряд и столбец ввода
                System.out.println("Введите номер строки");
                row = scanner.nextInt();
                System.out.println("Введите номер столбца");
                column = scanner.nextInt();


//        Действительны ли данные?
                if (row < 0 || row >=size || column < 0 || column > size) {
                    System.out.println("Неверный ввод введите значение повторно");


                } else if (field[row][column] != '-') {
                    System.out.println("Эта ячейка уже занята повторите ввод");

                }

                else {break;}
            }
            field[row][column] = symbol;


//        Определить победителя
            if (hasWinner(field) == 'X') {
                System.out.println(player1 + " победил");
                win = true;

            } else if (hasWinner(field) == 'O') {
                System.out.println(player2 + " победил");
                win = true;

            } else {
                if (isEmpty(field)) {
                    System.out.println("Ничья");
                    win = true;

                } else {
                    turn = !turn;

                }
            }
        }
        drawField(field);
    }


    public static void drawField(char[][] field) {
        for (char[] chars : field) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }


    public static char hasWinner(char[][] field) {
//        Строка

        for (char[] chars : field) {
            boolean inRow = true;
            char value = chars[0];
            if (value == '-') {
                inRow = false;
            } else {
                for (char aChar : chars) {
                    if (aChar != value) {
                        inRow = false;
                        break;
                    }
                }

            }
            if (inRow) {
                return value;
            }
        }
//        Столбец
        for (int i = 0; i < field.length; i++) {
            boolean inColumn = true;
            char value = field[0][i];
            if (value == '-') {
                inColumn = false;
            } else {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[j][i] != value) {
                        inColumn = false;
                        break;
                    }
                }
            }
            if (inColumn) {
                return value;
            }
        }
//        первая диагональ
        boolean inFirstDiagonal = true;
        char value = field[0][0];
        if (value == '-') {
            inFirstDiagonal = false;
        } else {
            for (int i = 0; i < field.length; i++) {
                if (field[i][i] != value) {
                    inFirstDiagonal = false;
                    break;
                }
            }

        }

        if (inFirstDiagonal) {
            return value;
        }
//        вторая диагональ
        boolean inSecondDiagonal = true;
        value = field[0][field.length - 1];
        if (value == '-') {
            inSecondDiagonal = false;
        } else {
            for (int i = 0; i < field.length; i++) {
                if (field[i][field.length - i - 1] != value) {
                    inSecondDiagonal = false;
                    break;
                }
            }

        }

        if (inSecondDiagonal) {
            return value;
        }
//        Нет победителя
        return '-';

    }


//    Пустая ли доска
    public static boolean isEmpty(char[][] field) {
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
