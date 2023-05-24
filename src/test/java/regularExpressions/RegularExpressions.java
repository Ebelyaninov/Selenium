package regularExpressions;

import java.util.Arrays;

public class RegularExpressions {
    public static void main(String[] args) {
        String a = "1234";
        String b = "1";
        String c = "";
        //Вернем true только если строки будут совпадать, но можем передать на вход регулярное выражение
        System.out.println(a.matches("1234"));
        /*
            \\d - одна цифра
            + - один и более
         */
        System.out.println(a.matches("\\d"));
        System.out.println(b.matches("\\d"));
        System.out.println(a.matches("\\d+"));
        System.out.println(b.matches("\\d+"));
        System.out.println(c.matches("\\d+"));
        //  * - 0 или более
        System.out.println(c.matches("\\d*"));
        System.out.println(a.matches("-\\d*"));
        System.out.println("-1234".matches("-\\d*"));
        //  ? - 0 или 1 символов до
        System.out.println("12345".matches("-?\\d*"));
        System.out.println("-12345".matches("-?\\d*"));
        System.out.println("+12345".matches("-?\\d*"));
        //  () - указываем какие-то вероятные вещи (которые могут быть (+ | -))
        System.out.println("-12345".matches("(-|\\+)?\\d*"));
        System.out.println("+12345".matches("(\\+|-)?\\d*"));
        System.out.println("12345".matches("(\\+|-)?\\d*"));
        /*
            [] - большие множества -> [a-ZA-Z]
            [abc] == (a|b|c)
            [a-zA-z] - Верхний и нижний регистр
            [1-9] == \\d
         */
        System.out.println("dsadadg12345".matches("[a-zA-Z]+\\d+"));

        String e = "hello";
        //[^abc] - мы хотим все символы кроме
        System.out.println(e.matches("[^abc]*"));
        System.out.println("helloc".matches("[^abc]*"));
        //. - любой символ
        String url = "http://www.google.com";
        String url2 = "http://www.test.ua";
        System.out.println(url.matches("http://www\\..+\\.(com|ua)"));
        System.out.println(url2.matches("http://www\\..+\\.(com|ua)"));
        System.out.println("http://www.yandx.ru".matches("http://www\\..+\\.(com|ua)"));
        /*
        точное кол-во символов или цифр
        {2} - 2 символа до (\\d{2})
        {2,} - 2 или более
        {2,4} - от 2 до 4
         */
        System.out.println("123".matches("\\d{2}"));
        System.out.println("123".matches("\\d{2,}"));

        // \\w - одна англ буква == [a-zA-Z]

        String d = "Hello there hey";
        String[] words = d.split(" ");
        System.out.println(Arrays.toString(words));


    }
}
