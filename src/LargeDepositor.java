public class LargeDepositor {       // a large depositor class with the information about a depositor
    private int AFM;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;


    LargeDepositor(){}
    LargeDepositor(int AFM, String firstName, String lastName, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;


    }

    // setters and getters
    public int key() {
        return AFM;
    }

    public int getAFM() {
        return AFM;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSavings() {
        return savings;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }

    public void setAFM(int AFM) {
        this.AFM = AFM;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public void setTaxedIncome(double taxedIncome) {
        this.taxedIncome = taxedIncome;
    }

    @Override   // to string method with all the details for the depositor
    public String toString(){
        return "AFM: " + getAFM()+ " "+ "First Name: " + getFirstName() + " " + "Last Name: " + getLastName()+ " " + "Savings: " + getSavings() + " " + "Taxed Income: " + getTaxedIncome();
    }

    public int compareTo(LargeDepositor other){     // a compare method for option 8
        if ( (this.taxedIncome < 8000) && other.getTaxedIncome() < 8000 )
            return Double.compare(Double.MAX_VALUE, Double.MAX_VALUE);
        else
            return Double.compare(this.getSavings() - this.getTaxedIncome(), other.getSavings() - other.getTaxedIncome());
    }
}

