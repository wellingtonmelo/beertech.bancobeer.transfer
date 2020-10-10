package beertech.becks.api.model;

public enum TypeOperation {

    DEPOSITO("DEPOSITO"),
    SAQUE("SAQUE");

    private String description;

    TypeOperation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

