package geniratorRes;

import dataSourse.PowerDevice;

import java.util.ArrayList;

public class GenRes {

    private static final ArrayList<PowerDevice> arrayList = new ArrayList<>();

    public static ArrayList<PowerDevice> getArrayListPowerDevices() {
        arrayList.clear();

        //----------------------Lamp---------------------------//
        int powerWat = 860;
        float c = 0;

        for (int i = 1; i < 150; i++) {

            int timeSec = 10*60 * i;
            float inputPower =  ((timeSec/(60f*60f)) * powerWat);

            c += -0.0003f;
            float parameterRandomMAX = 7 + i * c;
            float parameterRandomMIN = -7 + i * c;

            float randomP = (float) (Math.random() * (parameterRandomMAX - parameterRandomMIN) + parameterRandomMIN);
            float powerEff = ((87f + randomP)*inputPower)/100f;

            arrayList.add(new PowerDevice(200,timeSec, inputPower, powerEff));
        }
        //-----------------------------------------------------//

        return  arrayList;
    }

}
