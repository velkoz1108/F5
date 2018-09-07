package com.f5.mapper.base;

import com.f5.model.base.Roles;
import com.f5.model.base.RolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int countByExample(RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int deleteByExample(RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int insert(Roles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int insertSelective(Roles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    List<Roles> selectByExample(RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    Roles selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") Roles record, @Param("example") RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int updateByExample(@Param("record") Roles record, @Param("example") RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int updateByPrimaryKeySelective(Roles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbggenerated Fri Sep 07 16:51:36 CST 2018
     */
    int updateByPrimaryKey(Roles record);
}