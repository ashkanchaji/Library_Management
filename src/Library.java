public class Library {
    private final String id;
    private final String name;
    private final String establishYear;
    private final Integer tableCount;
    private final String address;

    public Library(String id, String name, String establishYear,
                   String tableCount, String address) {
        this.id = id;
        this.name = name;
        this.establishYear = establishYear;
        this.tableCount = Integer.parseInt(tableCount);
        this.address = address;
    }
}
