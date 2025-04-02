import java.time.LocalDate;
import java.util.List;

public record Person(
        int id,
        String lastname,
        String firstname,
        char gender,
        LocalDate dayOfBirth,
        int height,
        double weight_T1,
        double weight_T2) {


    public String toString() {
        StringBuilder person = new StringBuilder();
        if(weight_T2 == 0){
            person.append("* ");
        } else {
            person.append("  ");
        }

        String name = lastname + " " + firstname;
        String toAppend = "%3d %-40s %c %s (%2d) BMI %.1f-%-14s %3d %5.1f | %5.1f (%+.1f)"
                .formatted(id, name, gender, dayOfBirth, getAge(), getBMI(), getBMIClass(), height,weight_T1, weight_T2, getWeightDifference());
        person.append(toAppend);
        return person.toString();
    }

    public boolean hasAborted() {
        return weight_T2 == 0;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        int age =today.getYear() -  dayOfBirth.getYear();
        if(today.getDayOfYear() < dayOfBirth.getDayOfYear()) {
            age--;
        }
        return age;
    }

    public double getBMI() {
       return weight_T1 / Math.pow((float)height/100, 2);
    }

    public String getBMIClass() {
        double BMI = getBMI();
        String BMIClass = "";

        if(BMI > 0 && BMI < 18.5) {
            BMIClass = "underweight";
        } else if(BMI >= 18.5 && BMI < 25) {
            BMIClass = "normal weight";
        } else if(BMI >= 25 && BMI < 30){
            BMIClass = "overweight";
        } else if(BMI >= 30) {
            BMIClass = "obese";
        } else {
            BMIClass = "ERROR: negativ BMI";
        }

        return BMIClass;
    }

    public double getWeightDifference() {
        return weight_T2 - weight_T1;
    }

}
