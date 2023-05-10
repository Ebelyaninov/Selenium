package api;

public enum ApiServiceVersion {
    V1, V2, V3;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}