package queue;

import org.testng.annotations.Test;

import java.util.Stack;

public class StackTests {

    @Test(description = "stack")
    void stackTests() {
        Stack<Integer> stack = new Stack<>();
        // 5 <- 3 <- 1
        stack.push(5);
        stack.push(3);
        stack.push(1);

        //Получаем єлемент но не извлекаем его
        System.out.println(stack.peek());

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
