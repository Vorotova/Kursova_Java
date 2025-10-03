abstract class Person {
    private String enterpriseName;
    private String fullName;
    private String address;
    private String phoneNumber;

    public Person(String enterpriseName, String fullName, String address, String phoneNumber) {
        this.enterpriseName = enterpriseName;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getEnterpriseName() { return enterpriseName; }

    public String getFullName() { return fullName; }

    public String getAddress() { return address; }

    public String getPhoneNumber() { return phoneNumber; }

    public abstract void displayInfo();
}

