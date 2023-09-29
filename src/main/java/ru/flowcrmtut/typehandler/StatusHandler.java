package ru.flowcrmtut.typehandler;

import ru.flowcrmtut.config.CommonConfig;
import ru.flowcrmtut.dao.StatusMapper;
import ru.flowcrmtut.model.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = true)
public class StatusHandler extends BaseTypeHandler<Status> {

    private StatusMapper statusMapper = (StatusMapper) CommonConfig.getApplicationContext().getBean("statusMapper");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getId());
    }

    @Override
    public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return statusMapper.getStatusById(rs.getObject(columnName, UUID.class).toString());

    }

    @Override
    public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return statusMapper.getStatusById(rs.getObject(columnIndex, String.class));

    }

    @Override
    public Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return statusMapper.getStatusById(cs.getNString(columnIndex));
    }
}
