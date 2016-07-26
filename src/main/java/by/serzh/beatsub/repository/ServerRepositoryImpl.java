package by.serzh.beatsub.repository;

import by.serzh.beatsub.api.domain.QServer;
import by.serzh.beatsub.api.domain.Server;
import com.mysema.query.sql.DerbyTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.QBean;

import java.util.Collection;

public class ServerRepositoryImpl implements ServerRepository {
    private QServer repository = QServer.server;
    private SQLTemplates dialect = new DerbyTemplates();

    public ServerRepositoryImpl() {
    }

    @Override
    public Collection<Server> findAll() {
        return createQuery().from(repository).list(new QBean<>(repository));
    }

    private SQLQuery createQuery() {
        return new SQLQuery(RepositoryConnection.getConnection(), dialect);
    }

    private SQLInsertClause createInsert() {
       //return new SQLInsertClause(RepositoryConnection.getConnection(), dialect, repository);
        return null;
    }

    @Override
    public Server save(Server server) {
        return null;
    }
}
