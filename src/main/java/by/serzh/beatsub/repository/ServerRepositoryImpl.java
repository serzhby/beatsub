package by.serzh.beatsub.repository;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.domain.querydsl.QServers;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.SQLQueryFactory;

import java.util.Collection;
import java.util.List;

public class ServerRepositoryImpl implements ServerRepository {
    private QServers servers = QServers.servers;
    private SQLQueryFactory queryFactory;

    public ServerRepositoryImpl() {
        Configuration configuration = new Configuration(new DerbyTemplates());
        queryFactory = new SQLQueryFactory(configuration, RepositoryConnection.dataSource());
    }

    @Override
    public Collection<Server> findAll() {
        List<Server> list = queryFactory.select(Projections.fields(Server.class, servers.all())).from(servers).fetch();
        return list;
    }

    @Override
    public Server save(Server server) {
        queryFactory.insert(servers)
                .columns(servers.host, servers.port, servers.username, servers.password)
                .values(server.getHost(), server.getPort(), server.getUsername(), server.getPassword())
                .executeWithKey(servers.id);
        return server;
    }
}
