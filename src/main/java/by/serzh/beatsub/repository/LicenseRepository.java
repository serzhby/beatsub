package by.serzh.beatsub.repository;

import by.serzh.beatsub.api.domain.License;

public interface LicenseRepository {
    License save(License license);
}
