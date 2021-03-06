package cn.sxwl.lvyou.common.dao;

import cn.sxwl.lvyou.common.entity.Orders;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;


public class OrdersSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbggenerated Sun Feb 10 12:45:23 GMT+08:00 2019
     */
    public String insertSelective(Orders record) {
        BEGIN();
        INSERT_INTO("orders");
        
        if (record.getOid() != null) {
            VALUES("oid", "#{oid,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            VALUES("uid", "#{uid,jdbcType=INTEGER}");
        }
        
        if (record.getOcreatetime() != null) {
            VALUES("ocreatetime", "#{ocreatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOprice() != null) {
            VALUES("oprice", "#{oprice,jdbcType=DECIMAL}");
        }
        
        if (record.getJiesuan() != null) {
            VALUES("jiesuan", "#{jiesuan,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orders
     *
     * @mbggenerated Sun Feb 10 12:45:23 GMT+08:00 2019
     */
    public String updateByPrimaryKeySelective(Orders record) {
        BEGIN();
        UPDATE("orders");
        
        if (record.getUid() != null) {
            SET("uid = #{uid,jdbcType=INTEGER}");
        }
        
        if (record.getOcreatetime() != null) {
            SET("ocreatetime = #{ocreatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOprice() != null) {
            SET("oprice = #{oprice,jdbcType=DECIMAL}");
        }
        
        if (record.getJiesuan() != null) {
            SET("jiesuan = #{jiesuan,jdbcType=INTEGER}");
        }
        
        WHERE("oid = #{oid,jdbcType=VARCHAR}");
        
        return SQL();
    }
}