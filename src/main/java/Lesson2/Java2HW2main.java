package Lesson2;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;

//1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
//   при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
//   Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
//   должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
//3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException,
//   и вывести результат расчета.

public class Java2HW2main {

    final static int Array2D_Dimensions = 4; // This value from task? for describe 4x4 array

    public static void main(String[] args) {

        // Creating 4 string arrays with different parameters and elements for demonstrate algorithm
        String[][] tableArray3x3 = {{"0","0","0"},{"1","1","1"},{"2","2","2"}};
        String[][] tableArray3x4 = {{"0","0","0","0"},{"1","1","1","0"},{"2","2","2","0"}};
        String[][] tableArrayError = {{"0","0","0","0"},{"error","1","1","1"},{"2","2","2","2"},{"3","3","3","3"}};
        String[][] tableArray = {{"0","0","0","0"},{"1","1","1","1"},{"2","2","2","2"},{"3","3","3","3"}};
        String[][] tableArray1 = {{"0","0","0","0"},{"1","1","1","1"},{"2","2","2"},{"3","3","3","3"}};

        // Add all created String[][] arrays to list
        LinkedList<String[][]> arraysList = new LinkedList<>();

        arraysList.add(tableArray3x3);
        arraysList.add(tableArray3x4);
        arraysList.add(tableArrayError);
        arraysList.add(tableArray);
        arraysList.add(tableArray1);


        for (int i = 0; i < arraysList.size(); i++){
            try {
                System.out.print("ArrayList element " + i + " result: ");
                System.out.println(getSumOfTableElements(arraysList.get(i), Array2D_Dimensions));
            }
            catch (MyArraySizeException | MyArrayDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean isNumeric(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkSquareArray(String[][] array, int countOfColsAndRows){

        for (int i = 0; i < countOfColsAndRows; i++) {
            if(array[i].length != countOfColsAndRows){
                return false;
            }
        }

        return true;
    }

    public static int getSumOfTableElements(String[][] stringTable, int countOfColsAndRows) throws MyArraySizeException, MyArrayDataException {
        int resultSum = 0;
        if(stringTable[0].length != stringTable.length){
            throw new MyArraySizeException("Array dimensions are not equal (array is not square).");
        }
        else if(!checkSquareArray(stringTable, countOfColsAndRows)){
            String exceptionMessage = "2D array dimension is not " + countOfColsAndRows + "x" + countOfColsAndRows +". Correct it, please!";
            throw new MyArraySizeException(exceptionMessage);
        }
        else
        {
            for (int i = 0; i < countOfColsAndRows; i++) {
                for (int j = 0; j < countOfColsAndRows; j++) {
                    if(isNumeric(stringTable[i][j])){
                        resultSum += Integer.parseInt(stringTable[i][j]);
                    }
                    else{
                        throw new MyArrayDataException("Not correct string format in cell [" + i + "]["+j+"].");
                    }
                }
            }
        }
        return resultSum;
    }
}
