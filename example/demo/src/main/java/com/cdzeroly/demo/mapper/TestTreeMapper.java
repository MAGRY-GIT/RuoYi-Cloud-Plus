package com.cdzeroly.demo.mapper;

import com.cdzeroly.common.mybatis.annotation.DataColumn;
import com.cdzeroly.common.mybatis.annotation.DataPermission;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.demo.domain.TestTree;
import com.cdzeroly.demo.domain.vo.TestTreeVo;

/**
 * 测试树表Mapper接口
 *
 * @author Lion Li
 * @date 2021-07-26
 */
@DataPermission({
    @DataColumn(key = "deptName", value = "dept_id"),
    @DataColumn(key = "userName", value = "user_id")
})
public interface TestTreeMapper extends BaseMapperPlus<TestTree, TestTreeVo> {

}
