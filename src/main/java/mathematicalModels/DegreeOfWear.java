package mathematicalModels;

import dataSourse.ProductivityDevices;

public class DegreeOfWear {
    public static String getStringProductivityDevices(ProductivityDevices productivityDevices) {

        String percent = String.format("%.2f", ((1 - ((float)productivityDevices.productivityLast)/productivityDevices.productivityFirst)*100));
        return  "<html>" + "Оценка степени износа устройства.<br><br>" +
                "<font color='#708090'>Производительность устройства в первый день периода: </font>" + productivityDevices.productivityFirst/1000f + " кВт<br>" +
                "<font color='#708090'>Производительность устройства в последний день периода: </font>" + productivityDevices.productivityLast/1000f + " кВт<br>" +
                "<font color='#708090'>Oбщее время работы устройства в периоде: </font>" + (productivityDevices.periodWork/24) + " дней<br>  <br>" +
                "<font color='#708090'>Падение производительности на </font>" +percent+"%";
    }
}
