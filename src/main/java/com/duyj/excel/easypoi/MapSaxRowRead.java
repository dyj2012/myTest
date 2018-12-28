package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.sax.SaxReadCellEntity;
import cn.afterturn.easypoi.excel.imports.sax.parse.ISaxRowRead;
import cn.afterturn.easypoi.handler.inter.IExcelReadRowHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/17
 */
public class MapSaxRowRead implements ISaxRowRead {

    private List<String> mapKey;
    private IExcelReadRowHandler iExcelReadRowHandler;
    private ImportParams importParams;

    public MapSaxRowRead(ImportParams importParams, PageReadExcelHandle iExcelReadRowHandler) {
        this.iExcelReadRowHandler = iExcelReadRowHandler;
        this.importParams = importParams;
    }

    @Override
    public <T> List<T> getList() {
        return new ArrayList<>();
    }

    @Override
    public void parse(int index, List<SaxReadCellEntity> datas) {
        //标题行跳过
        if (index < importParams.getTitleRows()) {
            return;
        }
        //表头行
        if (index < importParams.getTitleRows() + importParams.getHeadRows()) {
            mapKey = new ArrayList<>(datas.size());
            for (SaxReadCellEntity data : datas) {
                mapKey.add(data.getValue().toString());
            }
        } else {
            LinkedHashMap<String, String> rowMap = new LinkedHashMap<>(datas.size());
            for (int i = 0; i < datas.size(); i++) {
                rowMap.put(mapKey.get(i), String.valueOf(datas.get(i).getValue()));
            }
            iExcelReadRowHandler.handler(rowMap);
        }
    }
}
