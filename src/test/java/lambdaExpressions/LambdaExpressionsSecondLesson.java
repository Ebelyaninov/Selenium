package lambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExpressionsSecondLesson {
    public static void main(String[] args) {
        int[] arr1 = new int[10];
        List<Integer> list1 = new ArrayList<>();
        fillArr(arr1);
        fillKist(list1);

        System.out.println("List is " + list1);
        System.out.println("Array is " + Arrays.toString(arr1));

//        for (int i = 0; i < 10; i++) {
//            arr2[i] = arr2[i] * 2;
//            list2.set(i, list2.get(i) * 2);
//        }

        // map method
        arr1 = Arrays.stream(arr1).map(a -> a * 2).toArray();
        //[1,2,3,] -> a = 1 -> a * 2; a = 2 -> a * 2;
        list1 = list1.stream().map(a -> a * 2).collect(Collectors.toList());

        System.out.println("New list1 is " + list1);
        System.out.println("New array1 is " + Arrays.toString(arr1));

        //Filter method
        int[] arr2 = new int[10];
        List<Integer> list2 = new ArrayList<>();
        fillArr(arr2);
        fillKist(list2);

        arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray();
        list2 = list2.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());

        System.out.println("New list2 is " + list2);
        System.out.println("New array2 is " + Arrays.toString(arr2));

        //for each
        Arrays.stream(arr2).forEach(System.out::println);
        list2.stream().forEach(a -> System.out.println(a));

        //reduce берем набор данных и сжимаем в 1 элемент
        int[] arr3 = new int[10];
        List<Integer> list3 = new ArrayList<>();
        fillArr(arr3);
        fillKist(list3);

        int product = list3.stream().reduce((acc, b) -> acc * b).get();
        // acc акумулирует даннные (временная переменная счетчик)
        // [1,2,3] 1st step => acc == 1 (По умолчанию == первому элементу массива) значит берем b == 2 ;
        // Если мы не указываем аккумулятор, то начнем итерацию со 2 элемента
        //
        int sum1 = Arrays.stream(arr3).reduce(0, (acc, b) -> acc + b);

        System.out.println(sum1);
        System.out.println(product);

        int[] arr4 = new int[10];
        fillArr(arr4);

        int[] newArray = Arrays.stream(arr4).filter(a -> a % 2 != 0)
                .map(a -> a * 2).toArray();
        System.out.println("New array4 is " + Arrays.toString(newArray));
    }

    private static void fillKist(List<Integer> list) {
        for (int i = 0; i < 10; i++)
            list.add(i + 1);
    }

    private static void fillArr(int[] arr) {
        for (int i = 0; i < 10; i++)
            arr[i] = i + 1;
    }
}
