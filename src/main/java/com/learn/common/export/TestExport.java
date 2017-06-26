package com.learn.common.export;

import com.learn.common.export.excel.ExcelDataExportor;
import com.learn.common.export.excel.MODE;
import com.learn.common.export.txt.TxtDataExportor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rui on 2017/6/25.
 */
public class TestExport {
    public static void main(String[] args) throws Exception{
        DataField[] dataFields = new DataField[3];
        dataFields[0] = new DataField("序号", "no");
        dataFields[1] = new DataField("创建时间", "createTime");
        dataFields[2] = new DataField("打款批次号", "batchNo");
        ExportDataSource<Object> exportDataSource = new ExportDataSource<Object>() {
            @Override
            public List<Map<String, String>> getData() {
                List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
                Map<String, String> mapParam = new HashMap<String, String>();
                mapParam.put("no","a");
                mapParam.put("createTime","b");
                mapParam.put("batchNo","c");
                lists.add(mapParam);
                return lists;
            }
        };
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\zjims\\a.xls");
//        new ExcelDataExportor(dataFields,exportDataSource,fileOutputStream, MODE.EXCEL).export();
        FileOutputStream outputStream = new FileOutputStream("C:\\zjims\\a.txt");
        new TxtDataExportor<Object>(dataFields,exportDataSource,outputStream).export();
    }
}
