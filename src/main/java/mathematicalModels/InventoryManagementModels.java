package mathematicalModels;

import ui.componentsView.CallBackFun;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class InventoryManagementModels {

    private final float D;
    private final float K;
    private final float H;
    private final int T;

    public InventoryManagementModels(float D,float K,float H,int T) {
        this.D = D;
        this.K = K;
        this.H = H;
        this.T = T;
    }

    public CallBackFun getFunctionOrderCosts() {
        return  (x -> (D/x) * K);
    }

    public CallBackFun getFunctionStorageCost() {
        return (x -> x * (1f/2f) * H);
    }

    public CallBackFun getFunctionSumCost() {
        return  (x -> (D/x) * K + x * (1f/2f) * H);
    }

    public float getOptimalSizeOrder() {
        return (float) sqrt((2 * D * K) / H);
    }

    public int getOptimalQuantityOrder() {
        return round(D / getOptimalSizeOrder());
    }

    public int getOptimalPeriodOrder() {
        return round(T / (float) getOptimalQuantityOrder());
    }

    public int getT() { return T; }

    public CallBackFun getFunctionInventory() {
        return  (x -> functionInventory(x)*1 );
    }

    private int countPeriod = 0;
    public double functionInventory(double x) {
        if(x <= 0) {
            countPeriod = 0;
        }
        x -= countPeriod;
        if(getOptimalSizeOrder() - x * (D / T) <= 0f) {
            countPeriod += getOptimalPeriodOrder();
        }
        return getOptimalSizeOrder() - x * (D / T);
    }
}
