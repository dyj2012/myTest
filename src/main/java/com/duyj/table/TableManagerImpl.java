package com.duyj.table;

import com.duyj.dbtest.mapper.TableAlterMapper;
import com.duyj.dbtest.model.TablePartitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2019/03/14
 */
@Component
public class TableManagerImpl implements TableManager {

    @Autowired
    TableAlterMapper tableAlterMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addPartition(String tableName, String partValue) {
        List<TablePartitionVO> tablePartitionVOList = tableAlterMapper.selectPartition(tableName);
        int maxYear = tablePartitionVOList.stream().map(TablePartitionVO::getYear).reduce(Integer::max).get();
        int partYear = Integer.parseInt(partValue);
        TablePartitionVO tablePartitionVO = new TablePartitionVO();
        tablePartitionVO.setTableName(tableName);
        while (maxYear <= partYear) {
            maxYear++;
            tablePartitionVO.setPartName("p_" + maxYear);
            tablePartitionVO.setYear(maxYear);
            tableAlterMapper.addPartition(tablePartitionVO);
        }
    }
}
