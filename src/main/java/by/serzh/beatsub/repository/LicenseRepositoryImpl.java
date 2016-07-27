package by.serzh.beatsub.repository;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.domain.querydsl.QLicense;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.SQLQueryFactory;

public class LicenseRepositoryImpl implements LicenseRepository {
    private QLicense licenses = QLicense.license;
    private SQLQueryFactory queryFactory;

    public LicenseRepositoryImpl() {
        Configuration configuration = new Configuration(new DerbyTemplates());
        queryFactory = new SQLQueryFactory(configuration, RepositoryConnection.dataSource());
    }

    @Override
    public License save(License license) {
        queryFactory.insert(licenses).populate(license).execute();
        return license;
    }

}
