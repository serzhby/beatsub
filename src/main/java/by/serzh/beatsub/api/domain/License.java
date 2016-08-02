package by.serzh.beatsub.api.domain;

import java.sql.Timestamp;

public class License extends AbstractEntity {

    private boolean valid;
    private String email;
    private Timestamp licenseExpires;
    private Timestamp trialExpires;
    private Integer serverId;

    public License() {
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Timestamp getLicenseExpires() {
        return licenseExpires;
    }

    public void setLicenseExpires(Timestamp licenseExpires) {
        this.licenseExpires = licenseExpires;
    }

    public Timestamp getTrialExpires() {
        return trialExpires;
    }

    public void setTrialExpires(Timestamp trialExpires) {
        this.trialExpires = trialExpires;
    }
}
