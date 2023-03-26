package dataBaseRepository.client;

public class Client {
    private String id;
    private String name;
    private String contact;
    private String phoneNumber;
    private double capital;

    public void markNew() {

        //unitOfWork.regisertNew(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", capital=" + capital +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getCapital() {
        return capital;
    }


    public Client setId(String id) {
        this.id = id;
        return this;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public Client setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public Client setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Client setCapital(double capital) {
        this.capital = capital;
        return this;
    }
}