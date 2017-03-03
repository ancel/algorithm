package com.work.algorithm.bigdata_sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultimap;

/**
 *
 *  n个已经排序好的文件做总排序
 *  算法逻辑：将每个文件最开头的数字取出进行比较，然后输出最大的（或者最小的），然后文件向上在推出一个数字，进行下一轮比较
 *  
 * @author：ancel.wang
 * @creattime：2017年3月2日 下午8:41:35 
 * 
 */  
public class Test {
	
	public static void main(String[] args) {
		String outputFileName = "output/result";
		List<String> fileNames = new ArrayList<String>();
		fileNames.add("input/1");
		fileNames.add("input/2");
		List<FileLineIterator> fileLineIterators = new ArrayList<FileLineIterator>();
		HashMultimap<Integer,FileLineIterator> headToFileLineIteratorMap = HashMultimap.create(fileNames.size(),fileNames.size());
		for (String fileName : fileNames) {
			try {
				fileLineIterators.add(new FileLineIterator(fileName));
				FileLineIterator fli = new FileLineIterator(fileName);
				if(fli.hasNext()){
					headToFileLineIteratorMap.put(Integer.valueOf(fli.next()), fli);
				}else{
					fli.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		IntegerAscSelector selector = new IntegerAscSelector();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			while(true){
				Set<Integer> headers = headToFileLineIteratorMap.keySet();
				Integer header = selector.select(headers);
				Set<FileLineIterator> headerValues = headToFileLineIteratorMap.removeAll(header);
				for (FileLineIterator fileLineIterator : headerValues) {
					writer.write(String.valueOf(header));
					writer.newLine();
					if(fileLineIterator.hasNext()){
						headToFileLineIteratorMap.put(Integer.valueOf(fileLineIterator.next()), fileLineIterator);
					}else{
						try {
							fileLineIterator.close();
						} catch (IOException e) {
						}
					}
				}
				if(0==headToFileLineIteratorMap.size()){
					break;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=writer){
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
				}
			}
		}
		
	}
	
}
