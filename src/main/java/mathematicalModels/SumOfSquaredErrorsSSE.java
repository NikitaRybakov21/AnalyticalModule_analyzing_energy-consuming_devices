package mathematicalModels;

import dataSourse.PointF;

import java.util.ArrayList;

public class SumOfSquaredErrorsSSE {

    public static PairAB sse(ArrayList<PointF> listPoint) {
        double sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0;

        int n = listPoint.size();
        for (PointF pointF : listPoint) {

            double xi = pointF.x;
            double yi = pointF.y;
            sum1 += xi * yi;
            sum2 += xi;
            sum3 += yi;
            sum4 += xi * xi;
            sum5 += xi;
        }
        double a = (n * sum1 - sum2 * sum3) / (n * sum4 - sum5 * sum5);
        double b = (sum3 - a * sum5) / n;

        return new PairAB(a,b);
    }
}
