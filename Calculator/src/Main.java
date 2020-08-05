public class Main {

    private void run() {
        Calculator calculator = new Calculator();
        System.out.println(calculator.Addition(2.2,1.1));
        System.out.println(calculator.multiply(new double[]{2.1,2.3,1.1,3.5}));
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
