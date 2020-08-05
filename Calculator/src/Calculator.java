public class Calculator {

    public Calculator(){}

    double multiply(double [] numbers){
        int total = 0;
        for (int i = 0 ; i<numbers.length;i++){
            total += numbers[i] * numbers[i+1];
        }
        return total;
    }
}
