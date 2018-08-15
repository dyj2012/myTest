/**
 * 元年软件
 * @author xiaohuzi
 * @date 2018年4月12日 上午11:16:54
 * @version V1.0
 */
package com.duyj.uitl;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import java.io.IOException;
import java.util.UUID;

/** 
 * ID生成器
 * @author 胡奇伟
 * @date 2018年4月12日 上午11:16:54 
 *  
 */
public class IDGenerator {
	
	/**
	 * 私有构造函数，防止实例化
	 */
	private IDGenerator() {		
	}
	
	/** 
	 * 获得32位的UUID
	 * @return 
	 * @throws IOException 
	 */
	public static String newID() {
		EthernetAddress nic = EthernetAddress.constructMulticastAddress();	
		UUID uuid = Generators.timeBasedGenerator(nic).generate();		
		return uuid.toString().replace("-", "");
	}
}
