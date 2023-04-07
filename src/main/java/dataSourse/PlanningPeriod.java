package dataSourse;

public class PlanningPeriod {
    public float D;
    public float K;
    public float H;
    public int T;

    public int id;

    public PlanningPeriod(int id, float D, float K, float H, int T) {
        this.D = D;
        this.K = K;
        this.H = H;
        this.T = T;

        this.id = id;
    }
}
