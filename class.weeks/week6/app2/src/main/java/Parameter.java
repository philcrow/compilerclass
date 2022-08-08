public class Parameter {
    String typeName;
    String name;

    public Parameter(String typeName, String name) {
        this.typeName = typeName;
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Parameter " + typeName + " " + name;
    }
}
