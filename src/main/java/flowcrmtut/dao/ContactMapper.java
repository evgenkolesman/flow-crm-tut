package flowcrmtut.dao;

import flowcrmtut.model.Company;
import flowcrmtut.model.Contact;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ContactMapper {

    @Select("""
            SELECT * FROM flowcrmtut.contact
            """)
    List<Company> getContactsList();

    @Select("""
            SELECT * FROM flowcrmtut.contact WHERE id = #{id}
            """)
    List<Company> getContactByName(@Param("id") UUID id);


    @Insert("""
            INSERT INTO flowcrmtut.contact(first_name, last_name, email, status_id, company_id)
            VALUES(#{first_name},#{last_name}, #{email}, #{contact.status.id}, #{contact.company.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id",
            keyColumn = "id")
    void insertContact(Contact contact);

    @Delete("""
            DELETE FROM flowcrmtut.contact
            WHERE id = #{id}
            """)
    void deleteContactById(@Param("id") String name);




}
