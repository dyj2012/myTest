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
        int columnNum = 10, dataName = 100000;
        List<KeyTitle> titleList = new ArrayList<>(2);
        for (int i = 0; i < columnNum; i++) {
            titleList.add(new KeyTitle("key" + i, "title" + i));
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < dataName; i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < columnNum; j++) {
                map.put("key" + j, i);
            }
            dataList.add(map);
        }
        easyPoiWriteExcel.writeExcel(dataList, titleList);
    }
}