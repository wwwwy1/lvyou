package cn.sxwl.lvyou.common.dao;


import cn.sxwl.lvyou.common.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated Thu Mar 07 20:02:41 GMT+08:00 2019
     */
    @Delete({
        "delete from product",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer pid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated Thu Mar 07 20:02:41 GMT+08:00 2019
     */
    @Insert({
        "insert into product (pid, bid, ",
        "pname, pimg, pprice, ",
        "pup, pdiscount)",
        "values (#{pid,jdbcType=INTEGER}, #{bid,jdbcType=INTEGER}, ",
        "#{pname,jdbcType=VARCHAR}, #{pimg,jdbcType=VARCHAR}, #{pprice,jdbcType=DECIMAL}, ",
        "#{pup,jdbcType=INTEGER}, #{pdiscount,jdbcType=DECIMAL})"
    })
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated Thu Mar 07 20:02:41 GMT+08:00 2019
     */
    @InsertProvider(type=ProductSqlProvider.class, method="insertSelective")
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated Thu Mar 07 20:02:41 GMT+08:00 2019
     */
    @Select({
        "select",
        "pid, bid, pname, pimg, pprice, pup, pdiscount",
        "from product",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="bid", property="bid", jdbcType=JdbcType.INTEGER),
        @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
        @Result(column="pimg", property="pimg", jdbcType=JdbcType.VARCHAR),
        @Result(column="pprice", property="pprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="pup", property="pup", jdbcType=JdbcType.INTEGER),
        @Result(column="pdiscount", property="pdiscount", jdbcType=JdbcType.DECIMAL)
    })
    Product selectByPrimaryKey(Integer pid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated Thu Mar 07 20:02:41 GMT+08:00 2019
     */
    @UpdateProvider(type=ProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated Thu Mar 07 20:02:41 GMT+08:00 2019
     */
    @Update({
        "update product",
        "set bid = #{bid,jdbcType=INTEGER},",
          "pname = #{pname,jdbcType=VARCHAR},",
          "pimg = #{pimg,jdbcType=VARCHAR},",
          "pprice = #{pprice,jdbcType=DECIMAL},",
          "pup = #{pup,jdbcType=INTEGER},",
          "pdiscount = #{pdiscount,jdbcType=DECIMAL}",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);

    @Select({
            "select",
            "pid, bid, pname, pimg, pprice, pup, pdiscount",
            "from product",
            "where bid = #{bid,jdbcType=INTEGER} and pup=1"
    })
    @Results({
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="bid", property="bid", jdbcType=JdbcType.INTEGER),
            @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
            @Result(column="pimg", property="pimg", jdbcType=JdbcType.VARCHAR),
            @Result(column="pprice", property="pprice", jdbcType=JdbcType.DECIMAL),
            @Result(column="pup", property="pup", jdbcType=JdbcType.INTEGER),
            @Result(column="pdiscount", property="pdiscount", jdbcType=JdbcType.DECIMAL)
    })
    List<Product> selectByBid(Integer bid);
}