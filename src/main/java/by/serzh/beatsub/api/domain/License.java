package by.serzh.beatsub.api.domain;

import java.time.Instant;

public class License {

    private boolean valid;
    private String email;
    private Instant licenseExpires;
    private Instant trialExpires;

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
}
