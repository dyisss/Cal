public class Calculator {

    public Calculator(){}

    public double Addition(double num1,double num2){
        return num1+num2;
    }

    double multiply(double [] numbers){
        int total = 0;
        for (int i = 0 ; i<numbers.length;i++){
            total += numbers[i] * numbers[i+1];
        }
        return total;
    }
}
