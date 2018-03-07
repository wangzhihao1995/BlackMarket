package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.ConfigDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Mapper
public interface ConfigMapper {

    void insert(ConfigDomain configDomain);

    ConfigDomain findById(Long id);

    ConfigDomain findByKey(String k);

    void update(@Param("k") String k, @Param("v") String v);
}
