package flowcrmtut.typehandler;

import flowcrmtut.dao.CompanyMapper;
import flowcrmtut.model.Company;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = true)
public class CompanyHandler extends BaseTypeHandler<Company> {

    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Company parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getId());
    }
    //TODO check variants of handler

    @Override
    public Company getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return companyMapper.getCompanyById(rs.getObject(columnName, String.class));
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
