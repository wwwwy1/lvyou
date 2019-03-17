package cn.sxwl.lvyou.common.dao;

import cn.sxwl.lvyou.common.entity.Contact;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContactMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    @Delete({
        "delete from contact",
        "where contactid = #{contactid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer contactid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    @Insert({
        "insert into contact (contactid, content, ",
        "contactname, contactemail)",
        "values (#{contactid,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{contactname,jdbcType=VARCHAR}, #{contactemail,jdbcType=VARCHAR})"
    })
    int insert(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    @InsertProvider(type=ContactSqlProvider.class, method="insertSelective")
    int insertSelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    @Select({
        "select",
        "contactid, content, contactname, contactemail",
        "from contact",
        "where contactid = #{contactid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="contactid", property="contactid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactname", property="contactname", jdbcType=JdbcType.VARCHAR),
        @Result(column="contactemail", property="contactemail", jdbcType=JdbcType.VARCHAR)
    })
    Contact selectByPrimaryKey(Integer contactid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    @UpdateProvider(type=ContactSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    @Update({
        "update contact",
        "set content = #{content,jdbcType=VARCHAR},",
          "contactname = #{contactname,jdbcType=VARCHAR},",
          "contactemail = #{contactemail,jdbcType=VARCHAR}",
        "where contactid = #{contactid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Contact record);

    @Select({
            "select",
            "contactid, content, contactname, contactemail",
            "from contact"
    })
    @Results({
            @Result(column="contactid", property="contactid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="contactname", property="contactname", jdbcType=JdbcType.VARCHAR),
            @Result(column="contactemail", property="contactemail", jdbcType=JdbcType.VARCHAR)
    })
    List<Contact> selectAll();
}