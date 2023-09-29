package ru.flowcrmtut.dao;

import ru.flowcrmtut.model.Company;
import ru.flowcrmtut.model.Contact;
import ru.flowcrmtut.model.Status;
import ru.flowcrmtut.typehandler.CompanyHandler;
import ru.flowcrmtut.typehandler.StatusHandler;
import ru.flowcrmtut.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ContactMapper {

    @Results({
            @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "email", property = "email"),
            @Result(column = "status_id", property = "status",
                    javaType = Status.class,
                    typeHandler = StatusHandler.class,
                    one = @One(columnPrefix = "status")),
            @Result(column = "company_id", property = "company",
                    javaType = Company.class,
                    typeHandler = CompanyHandler.class,
                    one = @One(columnPrefix = "company"))
    })
    @Select("""
            SELECT * FROM flowcrmtut.contact
            """)
    List<Contact> getContactsList();

    @Results({
            @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
            @Result(column = "first_name", property = "firstName"),
            @Result(column = "last_name", property = "lastName"),
            @Result(column = "email", property = "email"),
            @Result(column = "status_id", property = "status",
                    javaType = Status.class,
                    typeHandler = StatusHandler.class,
                    one = @One(columnPrefix = "status")),
            @Result(column = "company_id", property = "company",
                    javaType = Company.class,
                    typeHandler = CompanyHandler.class,
                    one = @One(columnPrefix = "company"))
    })
    @Select("""
            SELECT id, first_name, last_name, email, status_id::varchar , company_id::varchar
            FROM flowcrmtut.contact WHERE id = #{id}
            """)
    Contact getContactById(@Param("id") UUID id);
        @Results({
                @Result(column = "id", property = "id", typeHandler = UUIDTypeHandler.class),
                @Result(column = "first_name", property = "firstName"),
                @Result(column = "last_name", property = "lastName"),
                @Result(column = "email", property = "email"),
                @Result(column = "status_id", property = "status",
                        javaType = Status.class,
                        typeHandler = StatusHandler.class,
                        one = @One(columnPrefix = "status")),
                @Result(column = "company_id", property = "company",
                        javaType = Company.class,
                        typeHandler = CompanyHandler.class,
                        one = @One(columnPrefix = "company"))
        })
        @Select("""
                select c from flowcrmtut.contact c where lower(c.first_name) like lower(concat('%', #{searchTerm}, '%'))
                or lower(c.last_name) like lower(concat('%', #{searchTerm}, '%'))
                """)
        List<Contact> getContactBySearchData(@Param("searchTerm") String searchTerm);


    @Select("""
            INSERT INTO flowcrmtut.contact(first_name, last_name, email, status_id, company_id)
            VALUES(#{firstName},#{lastName}, #{email},
            #{status, javaType=Status, jdbcType=VARCHAR, typeHandler = ru.flowcrmtut.typehandler.StatusHandler},
            #{company, javaType=Company, jdbcType=VARCHAR, typeHandler = ru.flowcrmtut.typehandler.CompanyHandler})
            RETURNING id
            """)
    @Options(useGeneratedKeys = true,
            keyProperty = "id",
            keyColumn = "id")
    @Result(id = true, column = "id")
    String insertContact(Contact contact);
//JdbcType
    @Delete("""
            DELETE FROM flowcrmtut.contact
            WHERE id = #{id}
            """)
    void deleteContactById(@Param("id") UUID id);
 @Delete("""
            DELETE  FROM flowcrmtut.contact
            """)
    void deleteContactAll(@Param("id") UUID id);

}
