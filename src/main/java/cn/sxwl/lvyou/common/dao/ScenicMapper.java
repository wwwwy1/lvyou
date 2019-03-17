package cn.sxwl.lvyou.common.dao;

import cn.sxwl.lvyou.common.entity.Business;
import cn.sxwl.lvyou.common.entity.Scenic;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ScenicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    @Delete({
        "delete from scenic",
        "where sid = #{sid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    @Insert({
        "insert into scenic (sid, sname, ",
        "saddress, sprice, ",
        "simg, sstudent, ",
        "scomment)",
        "values (#{sid,jdbcType=INTEGER}, #{sname,jdbcType=VARCHAR}, ",
        "#{saddress,jdbcType=VARCHAR}, #{sprice,jdbcType=DECIMAL}, ",
        "#{simg,jdbcType=VARCHAR}, #{sstudent,jdbcType=DECIMAL}, ",
        "#{scomment,jdbcType=VARCHAR})"
    })
    int insert(Scenic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    @InsertProvider(type=ScenicSqlProvider.class, method="insertSelective")
    int insertSelective(Scenic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    @Select({
        "select",
        "sid, sname, saddress, sprice, simg, sstudent, scomment",
        "from scenic",
        "where sid = #{sid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="sname", property="sname", jdbcType=JdbcType.VARCHAR),
        @Result(column="saddress", property="saddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="sprice", property="sprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="simg", property="simg", jdbcType=JdbcType.VARCHAR),
        @Result(column="sstudent", property="sstudent", jdbcType=JdbcType.DECIMAL),
        @Result(column="scomment", property="scomment", jdbcType=JdbcType.VARCHAR)
    })
    Scenic selectByPrimaryKey(Integer sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    @UpdateProvider(type=ScenicSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Scenic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scenic
     *
     * @mbggenerated Sun Jan 20 13:02:47 GMT+08:00 2019
     */
    @Update({
        "update scenic",
        "set sname = #{sname,jdbcType=VARCHAR},",
          "saddress = #{saddress,jdbcType=VARCHAR},",
          "sprice = #{sprice,jdbcType=DECIMAL},",
          "simg = #{simg,jdbcType=VARCHAR},",
          "sstudent = #{sstudent,jdbcType=DECIMAL},",
          "scomment = #{scomment,jdbcType=VARCHAR}",
        "where sid = #{sid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Scenic record);
    @Update({
            "update scenic",
            "set sname = #{sname,jdbcType=VARCHAR},",
            "saddress = #{saddress,jdbcType=VARCHAR},",
            "sprice = #{sprice,jdbcType=DECIMAL},",
            "sstudent = #{sstudent,jdbcType=DECIMAL},",
            "scomment = #{scomment,jdbcType=VARCHAR}",
            "where sid = #{sid,jdbcType=INTEGER}"
    })
    int updateNotImg(Scenic record);
    @Select("select * from scenic")
    List<Scenic> selectAll();



    @Select({
            "select",
            "sid, sname, saddress, sprice, simg, sstudent, scomment",
            "from scenic",
            "where saddress like CONCAT('%',#{keyWords,jdbcType=VARCHAR},'%') and sid != #{sid,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="sname", property="sname", jdbcType=JdbcType.VARCHAR),
            @Result(column="saddress", property="saddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="sprice", property="sprice", jdbcType=JdbcType.DECIMAL),
            @Result(column="simg", property="simg", jdbcType=JdbcType.VARCHAR),
            @Result(column="sstudent", property="sstudent", jdbcType=JdbcType.DECIMAL),
            @Result(column="scomment", property="scomment", jdbcType=JdbcType.VARCHAR)
    })
    List<Scenic> selectByAddressNotSelf(@Param("keyWords") String keyWords,@Param("sid") Integer sid);

    @Select({
            "select",
            "bid, bname, baddress, bemail, bcreatetime, bbalance, bcomment, bimg",
            "from business " ,
            "where  bname like CONCAT('%',#{keyWords,jdbcType=VARCHAR},'%') or baddress like CONCAT('%',#{keyWords,jdbcType=VARCHAR},'%')"
    })
    @Results({
            @Result(column="bid", property="bid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="bname", property="bname", jdbcType=JdbcType.VARCHAR),
            @Result(column="baddress", property="baddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="bemail", property="bemail", jdbcType=JdbcType.VARCHAR),
            @Result(column="bcreatetime", property="bcreatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="bbalance", property="bbalance", jdbcType=JdbcType.DECIMAL),
            @Result(column="bcomment", property="bcomment", jdbcType=JdbcType.VARCHAR),
            @Result(column="bimg", property="bimg", jdbcType=JdbcType.VARCHAR)
    })
    List<Business> selectByAddressBusiness(@Param("keyWords") String keyWords);


}