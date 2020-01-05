package com.terahorse.fobit.model;

public class Version {

    private String version;
    private String build;

    @SuppressWarnings("unused")
    public Version() {
    }

    public Version(String version, String build) {
        this.version = version;
        this.build = build;
    }

    public String getVersion() {
        return version;
    }

    public String getBuild() {
        return build;
    }

}
