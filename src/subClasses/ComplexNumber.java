package subClasses;


import java.util.ArrayList;

public class ComplexNumber {

    static public ComplexNumber devide(ComplexNumber divisible, ComplexNumber devider){

        double commonDivisor = Math.pow(devider.imaginary, 2) + Math.pow(devider.real, 2);

        double newReal = (divisible.real * devider.real + divisible.imaginary * devider.imaginary) / (commonDivisor);
        double newImaginary = (divisible.imaginary * devider.real - divisible.real * devider.imaginary) / (commonDivisor);

        return new ComplexNumber(newReal, newImaginary);
    }

    static public ComplexNumber multiplex(ComplexNumber mul1, ComplexNumber mul2){
        return new ComplexNumber(mul1.real * mul2.real - mul1.imaginary * mul2.imaginary, mul1.real * mul2.real + mul1.imaginary * mul2.imaginary);
    }

    static public ComplexNumber summ(ComplexNumber s1, ComplexNumber s2){
        return new ComplexNumber(s1.real + s2.real, s1.imaginary + s2.imaginary);
    }

    static public ComplexNumber subtract(ComplexNumber subtractor, ComplexNumber subtractible){
        return new ComplexNumber(subtractor.real - subtractible.real, subtractor.imaginary - subtractible.imaginary);
    }

    static public ComplexNumber pow(ComplexNumber base, double degree){
        return new ComplexNumber(Math.pow(base.distance, degree), base.phi * degree, 0);
    }

    static public ArrayList<ComplexNumber> root(ComplexNumber base, int root){
        ArrayList<ComplexNumber> result = new ArrayList<>();

        double newDistance = Double.parseDouble(String.format("%.3f", Math.pow(base.distance, 1.0 / root)).replace(',', '.'));

        for(int i = 0; i < root; i++)
            result.add(new ComplexNumber(newDistance, (base.phi + 2*Math.PI*i) / root, 0));
        return result;
    }

    static public ComplexNumber naturalBaseLogarithm(ComplexNumber number){
        return new ComplexNumber(Math.log(number.distance), number.phi);
    }

    static public ComplexNumber getConjugateNumber(ComplexNumber number){
        return new ComplexNumber(number.real, (-1) * number.imaginary);
    }

    public ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
        this.updateDistance();
        this.updatePhi();
    }

    public ComplexNumber(double distance, double phi, int ignore){//bcs i have constructor with 2 param
        this.distance = distance;
        this.phi = phi;
        this.updateReal();
        this.updateImaginary();
    }

    public ComplexNumber(){
        this.real = 0;
        this.imaginary = 0;
        this.distance = 0;
        this.phi = 0;
    }

    public void setReal(double newReal){
        this.real = newReal;
        this.updateDistance();
    }

    public void setImaginary(double newImaginary){
        this.imaginary = newImaginary;
        this.updateDistance();
    }

    public void setDistance(double newDistance){
        this.distance = newDistance;
        this.updateImaginary();
        this.updateReal();
    }

    public void setPhi(double newPhi){
        this.phi = newPhi;
        this.updateReal();
        this.updateImaginary();
    }

    public double getReal(){
        return this.real;
    }

    public double getPhi(){
        return this.phi;
    }

    public double getImaginary(){
        return this.imaginary;
    }

    public double getDistance(){
        return this.distance;
    }

    public void roundUpToSomeMantissaSigns(int n){
        String roundFormat = "%." + n + "f";
        this.real = Double.parseDouble(String.format(roundFormat, this.real).replace(',', '.'));
        this.imaginary = Double.parseDouble(String.format(roundFormat, this.imaginary).replace(',', '.'));
        this.distance = Double.parseDouble(String.format(roundFormat, this.distance).replace(',', '.'));
        this.phi = Double.parseDouble(String.format(roundFormat, this.phi).replace(',', '.'));
    }

    public String toString(){
        String s = this.algebraicFormToString();
        s += ", " + this.trigonometricFormToString();
        s += ", " + this.indicativeFormToString();
        return s;
    }

    public String algebraicFormToString(){
        String s = Double.toString(this.real);

        if(this.imaginary >= 0)
            s += " + ";
        else
            s += " - ";

        return s + Math.abs(this.imaginary) + "i";
    }

    public String trigonometricFormToString(){
        String s = this.distance + " * [ cos(";
        s += (this.phi + ") + i * sin(");
        s += (this.phi + ") ]");
        return s;
    }

    public String indicativeFormToString(){
        String s = this.distance + " * exp( i * ";
        s += (this.phi + ")");
        return s;
    }

    private void updateDistance() {
        this.distance = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    }

    private void updatePhi(){

        if(this.real > 0)
            if(this.imaginary >= 0)
                this.phi = Math.atan(this.imaginary / this.real);
            else
                this.phi = Math.PI + Math.atan(this.imaginary / this.real);
        else if(this.real < 0)
            if(this.imaginary >= 0)
                this.phi = Math.PI - Math.atan(Math.abs(this.imaginary / this.real));
            else
                this.phi = 2 * Math.PI - Math.atan(Math.abs(this.imaginary / this.real));
        else{
            if(this.imaginary > 0)
                this.phi = Math.PI / 2;
            else if(this.imaginary < 0)
                this.phi = (Math.PI * 3) / 2;
            else
                this.phi = 0;
        }

    }

    private void updateReal(){
        this.real = this.distance * Math.cos(this.phi);
    }

    private void updateImaginary(){
        this.imaginary = this.distance * Math.sin(this.phi);
    }

    private double real;
    private double imaginary;
    private double distance;
    private double phi; //radians
}