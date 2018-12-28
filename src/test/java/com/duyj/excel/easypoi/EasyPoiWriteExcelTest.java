package com.duyj.excel.easypoi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.duyj.excel.AbstractWriteExcel.KeyTitle;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/25
 */
public class EasyPoiWriteExcelTest {

    EasyPoiWriteExcel easyPoiWriteExcel = new EasyPoiWriteExcel();

    @Test
    public void writeExcel() {
        int columnNum = 10, dataName = 100;
        List<KeyTitle> titleList = new ArrayList<>(2);
        for (int i = 0; i < columnNum; i++) {
            titleList.add(new KeyTitle("key" + i, "title" + i));
        }
        KeyTitle keyTitle = titleList.get(1);
        List<KeyTitle> subTitleList = new ArrayList<>(2);
        for (int i = 0; i < 3; i++) {
            subTitleList.add(new KeyTitle("subKey" + i, "subTitle" + i));
        }
        keyTitle.setSubTitleList(subTitleList);
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < dataName; i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < columnNum; j++) {
                if (j == 1) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    Map<String, Object> subMap = new HashMap<>();
                    for (int k = 0; k < 3; k++) {
                        subMap.put("subKey" + k, i + "_sV-" + k);
                    }
                    list.add(subMap);
                    //list.add(subMap);
                    map.put("key" + j, list);
                } else {
                    map.put("key" + j, i + "_v-" + j);
                }
            }
            dataList.add(map);
        }
        easyPoiWriteExcel.writeExcel(dataList, titleList);
    }
}