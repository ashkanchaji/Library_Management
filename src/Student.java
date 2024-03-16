public class Student {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String birthYear;
    private String address;

    public Student(String[] info) {
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        this.id = info[0];
        this.password = info[1];
        this.firstName = info[2];
        this.lastName = info[3];
        this.nationalId = info[4];
        this.birthYear = info[5];
        this.address = info[6];
    }
}
