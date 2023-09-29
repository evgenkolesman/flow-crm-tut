package ru.flowcrmtut.typehandler;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StatusHandler extends BaseTypeHandler<Status> {

    private StatusMapper statusMapper = (StatusMapper) CommonConfig.getApplicationContext().getBean("statusMapper");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getId());
    }

    @Override
    public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return statusMapper.getStatusById(String.valueOf(rs.getObject(columnName, UUID.class)));
        } catch (Exception e ) {
            log.error(e.getMessage());
            return null;
        }

    }

    @Override
    public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return statusMapper.getStatusById(String.valueOf(rs.getObject(columnIndex, UUID.class)));

    }

    @Override
    public Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return statusMapper.getStatusById(cs.getNString(columnIndex));
    }
}
