
public class Triangle {
    int s1,s2,s3;

    Triangle(int a, int b, int c){
        s1=a;
        s2=b;
        s3=c;
    }
    boolean judgement(){
        if(s1<=0 || s2<=0 || s3<=0) return false;
        else if(s1+s2 <=s3|| s1+s3 <=s2 || s2+s3 <= s1) return false;
        return true;
    }
    String shape(){
        if(judgement()){
            if(s1==s2 && s2==s3) return "Equilateral";
            else if(s1==s2 || s2==s3 || s1==s3) return "Isosceles";// triangle
            else if(s1*s1+s2*s2==s3*s3 ||s1*s1+s3*s3==s2*s2||s3*s3+s2*s2==s1*s1) return "Right";// triangle
            else if(s1*s1+s2*s2 < s3*s3 ||s1*s1+s3*s3 < s2*s2 || s3*s3+s2*s2 < s1*s1) return "Obtuse";
            else return "Acute";
        } else return "Non-triangle";
    }
}
