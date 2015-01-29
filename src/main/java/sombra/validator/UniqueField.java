package sombra.validator;


public enum UniqueField {
    NAME("name"),
    EMAIL("email"),
    PHONE("phone");

    private final String value;

    UniqueField(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
