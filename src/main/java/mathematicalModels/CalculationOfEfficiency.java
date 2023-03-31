package mathematicalModels;

import dataSourse.PowerDevice;

import java.util.ArrayList;

public class CalculationOfEfficiency {

    public static String calculationKpd(ArrayList<PowerDevice> listPower,String power) {
        int sumInputPower = 0;
        int sumEffPower = 0;
        int timeSum = 0;
        for (PowerDevice powerDevice : listPower) {
            sumInputPower += powerDevice.inputPower;
            sumEffPower += powerDevice.effectivePower;
            timeSum += powerDevice.time;
        }
        float valueKpd = (((float) sumEffPower/sumInputPower)*100f);
        String kpd = String.format("%.2f", valueKpd);
        return "<html>"+"Расчёт среднего значения КПД.<br><br>" +
                "<font color='#708090'>Исходная мощность устройства: </font>"+power+" ватт<br><br>" +
                "<font color='#708090'>Oбщая потребляемая мощность: </font>"+sumInputPower+" ватт<br>" +
                "<font color='#708090'>Oбщая производимая полезная мощность: </font>"+sumEffPower+" ватт<br>" +
                "<font color='#708090'>Oбщее время работы: </font>"+timeSum+" cек<br>  <br>" +
                "<font color='#708090'>Среднее </font>КПД = "+kpd+"%";
    }
}
