package cn.sxwl.lvyou.common.dao;



import cn.sxwl.lvyou.common.entity.Contact;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class ContactSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    public String insertSelective(Contact record) {
        BEGIN();
        INSERT_INTO("contact");
        
        if (record.getContactid() != null) {
            VALUES("contactid", "#{contactid,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getContactname() != null) {
            VALUES("contactname", "#{contactname,jdbcType=VARCHAR}");
        }
        
        if (record.getContactemail() != null) {
            VALUES("contactemail", "#{contactemail,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contact
     *
     * @mbggenerated Mon Mar 11 19:56:18 GMT+08:00 2019
     */
    public String updateByPrimaryKeySelective(Contact record) {
        BEGIN();
        UPDATE("contact");
        
        if (record.getContent() != null) {
            SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getContactname() != null) {
            SET("contactname = #{contactname,jdbcType=VARCHAR}");
        }
        
        if (record.getContactemail() != null) {
            SET("contactemail = #{contactemail,jdbcType=VARCHAR}");
        }
        
        WHERE("contactid = #{contactid,jdbcType=INTEGER}");
        
        return SQL();
    }
}