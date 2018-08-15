package com.duyj.dbtest.mapper;

import com.duyj.dbtest.BaseTest;
import com.duyj.dbtest.entity.CommonClobTableDO;
import com.duyj.uitl.UUIDUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/07/26
 */
public class CommonClobTableDOMapperTest extends BaseTest {

    @Autowired
    CommonClobTableDOMapper commonClobTableDOMapper;

    @Test
    public void insert() {
        commonClobTableDOMapper.insert(getCommonClobTableDO());
    }

    private CommonClobTableDO getCommonClobTableDO() {
        CommonClobTableDO commonClobTableDO = new CommonClobTableDO();
        commonClobTableDO.setClobId(UUIDUtils.getUUID());
        commonClobTableDO.setClobValue("sdfffffffffffffffffffffffffffffff");
        return commonClobTableDO;
    }
}