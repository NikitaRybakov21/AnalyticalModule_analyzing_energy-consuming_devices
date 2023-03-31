package dataSourse;

public class ProductivityDevices {
    public int id;
    public int periodWork;
    public int productivityFirst;
    public int productivityLast;

    public ProductivityDevices(int id,int periodWork,int productivityFirst,int productivityLast) {
        this.id = id;

        this.periodWork = periodWork;
        this.productivityFirst = productivityFirst;
        this.productivityLast = productivityLast;
    }
}
