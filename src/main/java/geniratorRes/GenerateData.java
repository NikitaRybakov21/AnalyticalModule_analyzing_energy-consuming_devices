package geniratorRes;

import dataSourse.DevicesDeath;
import dataSourse.PowerDevice;

import java.util.ArrayList;
import java.util.Random;

public class GenerateData {

    private static final ArrayList<PowerDevice> arrayList = new ArrayList<>();
    private static final ArrayList<DevicesDeath> arrayListDeath = new ArrayList<>();
    private static final Random random = new Random();

    public static ArrayList<PowerDevice> getArrayListPowerDevices() {
        arrayList.clear();
        //----------------------InputOutPower---------------------------//
        int powerWat = 860;
        float c = 0;

        for (int i = 1; i < 150; i++) {

            int timeSec = 10*60 * i;
            float inputPower =  ((timeSec/(60f*60f)) * powerWat);

            c += -0.003f;
            float parameterRandomMAX = 7 + i * c;
            float parameterRandomMIN = -7 + i * c;

            float randomP = (float) (Math.random() * (parameterRandomMAX - parameterRandomMIN) + parameterRandomMIN);
            float powerEff = ((87f + randomP)*inputPower)/100f;

            arrayList.add(new PowerDevice(200,timeSec, inputPower, powerEff));
        }
        //-----------------------------------------------------//
        return  arrayList;
    }

    public static ArrayList<DevicesDeath> getArrayListSurvivalDevices() {
        arrayListDeath.clear();

        for (int j = 0; j < 100; j++) {
            arrayListDeath.add(new DevicesDeath(200,null));
        }
        //----------------------Survival---------------------------//
        int timeSurvive = 1500;

        for (int i = 1; i <= timeSurvive; i++) {

            int timeSec = 60*60 * i;

            for (DevicesDeath devicesDeath : arrayListDeath) {

                if (timeSec > timeSurvive*60*60) {
                    timeSec = timeSurvive*60*60;
                }
                float c = 2f;

                if (random.nextInt((int) (c*timeSurvive - c*(timeSec/(60f*60f)) + 1f)) == 0) {
                    if (devicesDeath.timeDeath == null) {
                        devicesDeath.timeDeath = Integer.toString(timeSec);
                    }
                }
            }
        }
        //--------------------------------------------------------//
        return arrayListDeath;
    }
}
