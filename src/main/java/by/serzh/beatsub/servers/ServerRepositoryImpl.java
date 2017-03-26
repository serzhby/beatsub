package by.serzh.beatsub.servers;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.domain.querydsl.QLicense;
import by.serzh.beatsub.domain.querydsl.QServers;
import by.serzh.beatsub.repository.RepositoryConnection;
import by.serzh.beatsub.utils.ExcludeMapper;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.types.JSR310InstantType;
import com.querydsl.sql.types.TimestampType;
import com.querydsl.sql.types.UtilDateType;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServerRepositoryImpl implements ServerRepository {
    private static QServers servers = QServers.servers;
    private static QLicense licenses = QLicense.license;
    private SQLQueryFactory queryFactory;

    private static QBean<Server> serverProjection = Projections.fields(Server.class, servers.all());
    private static QBean<License> licenseProjection = Projections.bean(License.class, licenses.all());

    public ServerRepositoryImpl() {
        Configuration configuration = new Configuration(new DerbyTemplates());
        configuration.register(new UtilDateType());
        configuration.register(new JSR310InstantType());
        configuration.register(new TimestampType());
        queryFactory = new SQLQueryFactory(configuration, RepositoryConnection.dataSource());
    }

    @Override
    public Collection<Server> findAll() {
        List<Tuple> list = queryFactory
                .select(serverProjection, licenseProjection)
                .from(servers)
                .leftJoin(licenses)
                .on(licenses.serverFk.on(servers))
                .fetch();
        List<Server> servers = list.stream().map(this::tupleToServer).collect(Collectors.toList());
        return servers;
    }

    @Override
    public Server find(Integer id) {
        Tuple tuple = queryFactory.select(serverProjection, licenseProjection)
                .from(servers)
                .where(servers.id.eq(id))
                .leftJoin(licenses)
                .on(licenses.serverFk.on(servers)).fetchFirst();
        Server server = Optional.ofNullable(tuple).map(this::tupleToServer).orElse(null);
        return server;
    }

    @Override
    public Server save(Server server) {
        Integer id = queryFactory.insert(servers)
                .populate(server, ExcludeMapper.getInstance(servers.id))
                .executeWithKey(servers.id);
        License license = server.getLicense();
        license.setServerId(id);
        queryFactory.insert(licenses)
                .populate(license, ExcludeMapper.getInstance(licenses.id))
                .execute();
        server = find(id);
        return server;
    }

    @Override
    public long delete(Server server) {
        Objects.requireNonNull(server);
        return delete(server.getId());
    }

    @Override
    public long delete(Integer id) {
        Objects.requireNonNull(id);
        return queryFactory.delete(servers).where(servers.id.eq(id)).execute();
    }

    private Server tupleToServer(Tuple tuple) {
        License license = tuple.get(licenseProjection);
        Server server = tuple.get(serverProjection);
        server.setLicense(license);
        return server;
    }
}
