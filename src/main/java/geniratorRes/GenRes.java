package geniratorRes;

import dataSourse.PowerDevice;

import java.util.ArrayList;

public class GenRes {

    private static final ArrayList<PowerDevice> arrayList = new ArrayList<>();

    public static ArrayList<PowerDevice> getArrayListPowerDevices() {
        arrayList.clear();

        //----------------------Lamp---------------------------//
        int powerWat = 100;

        for (int i = 1; i < 100; i++) {

            int timeSec = 10*60 * i;
            float inputPower =  ((timeSec/(60f*60f)) * powerWat);

            float c = 0f;
            float parameterRandomMAX = 1 + i * c;
            float parameterRandomMIN = -1 + i * c;

            float randomP = (float) (Math.random() * (parameterRandomMAX - parameterRandomMIN) + parameterRandomMIN);
            float powerEff = ((5f + randomP)*inputPower)/100f;

            arrayList.add(new PowerDevice(100,timeSec, inputPower, powerEff));
        }
        //-----------------------------------------------------//

        return  arrayList;
    }

}
