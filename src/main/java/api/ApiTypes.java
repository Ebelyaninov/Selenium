package api;

public enum ApiTypes {
    SOAP,
    REST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}