package com.duyj.work.jdk.dbAnnotation;

@DTable(name = "tbl_user_t")
public class UserBean {
	
	@DColumn(type=EType.INT, option={EOption.NOT_NULL, EOption.PRIMARY})
	private int id;
	
	@DColumn(30)
	private String name;
	
	@DColumn(type=EType.INT)
	private Integer age;
	
	@DColumn(30)
	private String email;
	
	@DColumn(type=EType.DATE)
	private String create_date;
	
}
