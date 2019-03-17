package cn.sxwl.lvyou.common.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import cn.sxwl.lvyou.common.entity.Scenic;

public class ScenicSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    public String insertSelective(Scenic record) {
        BEGIN();
        INSERT_INTO("scenic");
        
        if (record.getSid() != null) {
            VALUES("sid", "#{sid,jdbcType=INTEGER}");
        }
        
        if (record.getSname() != null) {
            VALUES("sname", "#{sname,jdbcType=VARCHAR}");
        }
        
        if (record.getSaddress() != null) {
            VALUES("saddress", "#{saddress,jdbcType=VARCHAR}");
        }
        
        if (record.getSprice() != null) {
            VALUES("sprice", "#{sprice,jdbcType=DECIMAL}");
        }
        
        if (record.getSimg() != null) {
            VALUES("simg", "#{simg,jdbcType=VARCHAR}");
        }
        
        if (record.getSstudent() != null) {
            VALUES("sstudent", "#{sstudent,jdbcType=DECIMAL}");
        }
        
        if (record.getScomment() != null) {
            VALUES("scomment", "#{scomment,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    public String updateByPrimaryKeySelective(Scenic record) {
        BEGIN();
        UPDATE("scenic");
        
        if (record.getSname() != null) {
            SET("sname = #{sname,jdbcType=VARCHAR}");
        }
        
        if (record.getSaddress() != null) {
            SET("saddress = #{saddress,jdbcType=VARCHAR}");
        }
        
        if (record.getSprice() != null) {
            SET("sprice = #{sprice,jdbcType=DECIMAL}");
        }
        
        if (record.getSimg() != null) {
            SET("simg = #{simg,jdbcType=VARCHAR}");
        }
        
        if (record.getSstudent() != null) {
            SET("sstudent = #{sstudent,jdbcType=DECIMAL}");
        }
        
        if (record.getScomment() != null) {
            SET("scomment = #{scomment,jdbcType=VARCHAR}");
        }
        
        WHERE("sid = #{sid,jdbcType=INTEGER}");
        
        return SQL();
    }
}