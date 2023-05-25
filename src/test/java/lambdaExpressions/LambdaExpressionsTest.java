package lambdaExpressions;

public class LambdaExpressionsTest {
    public static void main(String[] args) {
        RunnerForExecute runnerForExecute = new RunnerForExecute();
        //Реализацию интерфейса Executable можно выполнить 3 способами
        //Создать класс ExecutableImplementation и реализовать там интерфейс с логикой
//        runnerForExecute.run(new ExecutableImplementation());
        //С помощью анонимного класса
        runnerForExecute.run(new Executable() {
            @Override
            public int execute(int x, int y) {
                return x + y;
            }
        });
        //Использования LambdaExpression
        runnerForExecute.run((x, y) -> x + y);
    }
}


interface Executable {
    int execute(int x, int y);
}

class RunnerForExecute {
    public void run(Executable e) {
        int a = e.execute(10, 15);
        System.out.println(a);
    }
}

//class ExecutableImplementation implements Executable {
//
//    @Override
//    public void execute() {
//        System.out.println("Hello!");
//    }
//}
