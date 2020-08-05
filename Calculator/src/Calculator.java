class Calculator {

    Calculator(){}

    double Addition(double num1, double num2){
        return Math.round(num1+num2);
    }

    double multiply(double [] numbers){
        double total = 1;
        double prev = 0;
        for (double i : numbers){
            if(prev == 0){
                prev = i;
            }else{
                total = (total*prev) * i;
                prev = 0;
            }
        }
        return Math.round(total);
    }
}
