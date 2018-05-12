public class Calculator {
    
    int a, b;

    Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getGCD() {
        Calculator c = new Calculator(a, b);
        return c.calGCD(a, b);
    }

    public static int calGCD(int a, int b) {
        if(b == 0)  return a;
        return calGCD(b, a % b);
    }

    public int getLCM() {
        Calculator c = new Calculator(a, b);
        return a * b / c.calGCD(a, b);
    }
}
