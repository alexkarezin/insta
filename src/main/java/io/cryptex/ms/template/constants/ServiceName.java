package io.cryptex.ms.template.constants;


public enum ServiceName {
    COMMUNICATION("be-communication");

    private String providerName;

    ServiceName(String providerName) {
        this.providerName = providerName;
    }

    public String getName() {
        return providerName;
    }
}
