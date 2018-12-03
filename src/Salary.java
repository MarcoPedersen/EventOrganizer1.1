public class Salary {

    public String calculateSalary(String hours, boolean isWeekend) {
        double time = Double.parseDouble(hours);
        double totalSalary = 0;
        if(isWeekend) {
            if(time%0.5 != 0) {
                double timeLeft = time - time%1;
                if (time%1 > 0.5) {
                    totalSalary = (timeLeft*700) + 700;
                } else {
                    totalSalary = (timeLeft*700) + 350;
                }
            } else {
                totalSalary = time*700;
            }
        } else {
            if(time%0.5 != 0) {
                double timeLeft = time - time%1;
                if (time%1 > 0.5) {
                    totalSalary = (timeLeft*500) + 500;
                } else {
                    totalSalary = (timeLeft*500) + 250;
                }
            } else {
                totalSalary = time*500;
            }
        }
        String priceOfEvent = String.valueOf(totalSalary);
        return priceOfEvent;
    }
}
