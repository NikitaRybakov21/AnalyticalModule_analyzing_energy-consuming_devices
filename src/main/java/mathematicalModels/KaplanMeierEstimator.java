package mathematicalModels;

import dataSourse.DevicesDeath;

import java.util.ArrayList;

public class KaplanMeierEstimator {

    ArrayList<DevicesDeath> listDeath;

    public KaplanMeierEstimator(ArrayList<DevicesDeath> listDeath) {
        this.listDeath = listDeath;
    }

    public float funSurviveKaplanMeier(double t) {

        double timeSecond = t * 24 * 60 * 60;

        ArrayList<Integer> timeDeathList = new ArrayList<>();

        for (DevicesDeath devicesDeath : listDeath) {
            int timeDeath;
            if (devicesDeath.timeDeath != null) {

                timeDeath = Integer.parseInt(devicesDeath.timeDeath);
                if (timeDeath <= timeSecond) {
                    timeDeathList.add(timeDeath);
                }
            }
        }

        float pMulti = 1;
        for (Integer timeDeath : timeDeathList) {

            float sumLive = 0f;
            float sumDeath = 0f;
            for (DevicesDeath devicesDeath : listDeath) {

                if (devicesDeath.timeDeath != null) {
                    int deathTime = Integer.parseInt(devicesDeath.timeDeath);
                    if (deathTime > timeDeath) {
                        sumLive++;
                    }
                    if (deathTime == timeDeath) {
                        sumDeath++;
                    }
                } else {
                    sumLive++;
                }
            }

            if(sumLive != 0) {
                pMulti *= 1 - (sumDeath/sumLive);
            } else {
                pMulti *= 0f;
            }
        }

       return pMulti;
    }

    public long getTimeLiveAVG() {
        long sumTime = 0;
        for (DevicesDeath devicesDeath : listDeath) {
            System.out.println(Integer.parseInt(devicesDeath.timeDeath));
            System.out.println(devicesDeath.timeDeath);

            if (Integer.parseInt(devicesDeath.timeDeath) > 0) {
                sumTime += Integer.parseInt(devicesDeath.timeDeath);
            }
        }
        System.out.println(sumTime);

        return sumTime/listDeath.size();
    }
}
