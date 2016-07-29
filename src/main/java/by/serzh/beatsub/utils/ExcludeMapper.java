package by.serzh.beatsub.utils;

import com.querydsl.core.types.Path;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.dml.DefaultMapper;

import java.util.Map;

public class ExcludeMapper extends DefaultMapper {

    private Path path;

    public static ExcludeMapper getInstance(Path path) {
        return new ExcludeMapper(path);
    }

    private ExcludeMapper(Path path) {
        super(true);
        this.path = path;
    }

    @Override
    protected Map<String, Path<?>> getColumns(RelationalPath<?> path) {
        Map<String, Path<?>> map = super.getColumns(path);
        map.remove(this.path.getMetadata().getName());
        return map;
    }
}