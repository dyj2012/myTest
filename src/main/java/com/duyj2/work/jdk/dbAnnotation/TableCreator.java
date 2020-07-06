package com.duyj2.work.jdk.dbAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

	private static String getCon(EOption[] options) {
		String ret = "";
		if (EOption.NOT_NULL.withIn(options)) {
			ret += " NOT NULL ";
		} 
		if (EOption.PRIMARY.withIn(options)) {
			ret += " PRIMARY KEY ";
		} 
		if (EOption.UNIQUE.withIn(options)) {
			ret += " UNIQUE ";
		}
		return ret;
	}

	public static void main(String[] args) throws ClassNotFoundException {

        String classname = "com.duyj2.work.jdk.dbAnnotation.UserBean";

		Class<?> c = Class.forName(classname);
		DTable table = (DTable) c.getAnnotation(DTable.class);
		if (table == null) {
			System.out.println(c.getName() + " no DTable Annotation");
			return;
		}
		String tableName = table.name();
		if (tableName.length() < 1) {
			tableName = c.getName().toLowerCase();
		}

		List<String> columnNames = new ArrayList<>();
		for (Field f : c.getDeclaredFields()) {

			Annotation[] a = f.getDeclaredAnnotations();
			if (a.length < 1) {
				continue;
			}
			DColumn dcol = (DColumn) a[0];
			String cName = dcol.name().length() < 1 ? f.getName().toLowerCase() : dcol.name();
			
			if (dcol.type() == EType.INT) 
			{
				columnNames.add(cName + " INT" + getCon(dcol.option()));
			} 
			else if (dcol.type() == EType.VARCHAR) 
			{
				columnNames.add(cName + " VARCHAR(" + dcol.value() + ")" + getCon(dcol.option()));
			}

		}

		StringBuilder sb = new StringBuilder();
		sb.append("create table ");
		sb.append(tableName);
		sb.append(" (\n");
		for (String s : columnNames) {
			sb.append(s);
			sb.append(",\n");
		}
		sb.append(")");

		System.out.println(sb.toString());
	}

}
