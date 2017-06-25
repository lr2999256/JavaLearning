package com.learn.common.export.csv;

import java.io.OutputStream;

import com.learn.common.export.DataField;
import com.learn.common.export.ExportDataSource;
import com.learn.common.export.txt.TxtDataExportor;


/**
 * 描述: csv格式数据导出工具
 * @author Hill
 *
 */
public class CsvDataExportor<T> extends TxtDataExportor<T> {
	public CsvDataExportor(DataField[] fields, ExportDataSource<T> dataSource,OutputStream os) {
		super(fields, dataSource, os,",");
	}
}
