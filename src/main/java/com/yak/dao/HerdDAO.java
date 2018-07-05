package com.yak.dao;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.yak.been.Herd;

@ComponentScan
@Component
public class HerdDAO {
	
	
	private Herd yakList;

	private String fileName;
	
	public HerdDAO() {
		this.fileName =  "herd.xml";
	}
	public HerdDAO(String fileName) {
		
		File yakFile = new File(fileName);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Herd.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			yakList = (Herd)unmarshaller.unmarshal(yakFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Herd getYakList() {
		if(this.yakList == null) {
			return new HerdDAO(this.fileName).yakList;
		}
		return this.yakList;
	}

}
