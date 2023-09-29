package ru.flowcrmtut.typehandler;

import ru.flowcrmtut.config.CommonConfig;
import ru.flowcrmtut.dao.CompanyMapper;
import ru.flowcrmtut.model.Company;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = true)
public class CompanyHandler extends BaseTypeHandler<Company> {

    private CompanyMapper companyMapper = (CompanyMapper) CommonConfig.getApplicationContext().getBean("companyMapper");;
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Company parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getId());
    }
    //TODO check variants of handler

    @Override
    public Company getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return companyMapper.getCompanyById(rs.getObject(columnName, UUID.class).toString());
    }

    @Override
    public Company getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return companyMapper.getCompanyById(rs.getObject(columnIndex, String.class));
    }

    @Override
    public Company getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return companyMapper.getCompanyById(cs.getNString(columnIndex));
    }
}
