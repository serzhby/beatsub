package by.serzh.beatsub.api.domain;

import java.sql.Timestamp;
import java.time.Instant;

public class License extends AbstractEntity {

    private boolean valid;
    private String email;
    private Instant licenseExpires;
    private Instant trialExpires;
    private Integer serverId;

    public License() {
    }

    public License(boolean valid, String email, Instant licenseExpires) {
        this.valid = valid;
        this.email = email;
        this.licenseExpires = licenseExpires;
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

    public Instant getLicenseExpires() {
        return licenseExpires;
    }

    public void setLicenseExpires(Instant licenseExpires) {
        this.licenseExpires = licenseExpires;
    }

    public Instant getTrialExpires() {
        return trialExpires;
    }

    public void setTrialExpires(Instant trialExpires) {
        this.trialExpires = trialExpires;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Timestamp getLicenseExpiresTs() {
        return licenseExpires == null ? null : Timestamp.from(licenseExpires);
    }

    public void setLicenseExpiresTs(Timestamp licenseExpires) {
        this.licenseExpires = licenseExpires != null ? licenseExpires.toInstant() : null;
    }
    public Timestamp getTrialExpiresTs() {
        return trialExpires == null ? null : Timestamp.from(trialExpires);
    }

    public void setTrialExpiresTs(Timestamp trialExpires) {
        this.trialExpires = trialExpires != null ? trialExpires.toInstant() : null;
    }

}
