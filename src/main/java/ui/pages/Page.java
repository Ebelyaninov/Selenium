package ui.pages;

public interface Page<T extends BasePageSignedIn> {
    default T openViaUrl() {
        throw new IllegalStateException("Method can not be used for this page. Not implemented.");
    }

    default T openViaMenu() {
        throw new IllegalStateException("Method can not be used for this page. Not implemented.");
    }

    default T waitPageOpening() {
        throw new IllegalStateException("Method can not be used for this page. Not implemented.");
    }
}