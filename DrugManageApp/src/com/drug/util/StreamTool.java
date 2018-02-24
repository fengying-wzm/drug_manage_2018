/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 *
 * @author Legend-novo
 */
public class StreamTool {
    	/**
	 * 读取流中的数据
	 * @param stream 传入的流
	 * @return
	 * @throws Exception
	 */
	public static byte[] read(InputStream stream) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=stream.read(buffer)) != -1){
			outputStream.write(buffer, 0, len);
		}
		return outputStream.toByteArray();
	}
}
