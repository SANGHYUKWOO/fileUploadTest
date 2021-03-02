package com.example.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MemberVO;
import com.example.service.InsertTestService;
import com.example.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileUploadTestController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadTestController.class);
	
	@Inject
	private InsertTestService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/fileUplodad", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "fileUpload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		//System.out.println("파일 이름 : " + file.getOriginalFilename());
	    //System.out.println("파일 크기 : " + file.getSize());
	    //반환용 리스트
        List<String> ret = new ArrayList<String>();
        BufferedReader br = null;

	    try(
	      // 윈도우일 경우
	      //FileOutputStream fos = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
	    		FileOutputStream fos = new FileOutputStream("d:\\" + file.getOriginalFilename());
	    		InputStream is = file.getInputStream();
	    ){
		    int readCount = 0;
		    byte[] buffer = new byte[1024];
		    while((readCount = is.read(buffer)) != -1){
		    	fos.write(buffer,0,readCount);
		    }
		    OutputStream output = new FileOutputStream("d:/" + file.getOriginalFilename());
			//String str ="오늘 날씨는 아주 좋습니다.";
			byte[] by=file.getBytes();
			output.write(by);
		    //System.out.println("파일 이름 : " + file.getOriginalFilename());
	    	br = Files.newBufferedReader(Paths.get("d:\\"+ file.getOriginalFilename()));
	    	String line = "";
            
            while((line = br.readLine()) != null){
                //CSV 1행을 저장하는 리스트
                //List<String> tmpList = new ArrayList<String>();
                String array = line;
                //배열에서 리스트 반환
                //tmpList = Arrays.asList(array);
                //System.out.println(tmpList);
                ret.add(array);
            }
            
            /*int size = (int) Math.ceil(ret.size()/5000);
            System.out.println("size = "+size);
            List<String>  subList = new ArrayList<String>();
            for(int i = 0;i<size;i++) {
            	if(i!=size) {
            		ret.subList
            		subList = ret.subList((i*5000), (i*5000)+4999);
            	} 
            	else if(i==size) {
            		subList = ret.subList((i*5000), ret.size()-1 );
            	}
            	//System.out.println("i번째   >"+ret);
            	//do someThing
            	//logger.info(i+"번째   >"+subList);
            	subList.clear();
            	
            }*/
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss.SSS");
            Date time = new Date();
    		
            String time1 = format1.format(time);

            String parm = ret.toString();
            System.out.println("DB 등록 시작  > "+time1);
            int test = service.InsertTest(parm);
            System.out.println("DB 등록 끝    > "+time1);
            
	    }catch(Exception ex){
	      throw new RuntimeException("file Save Error "+ex.getMessage());
	    }finally{
            try{
                if(br != null){
                    br.close();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }		
		
		return "fileUpload";
	}
	
}
