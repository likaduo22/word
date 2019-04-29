package cn.likaduo.controller;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.likaduo.po.Word;
import cn.likaduo.service.ReadWord;

import com.ushine.common.utils.ViewObject;


/**
 * @author CHL
 * 
 */
@Controller
public class WordViewController {
	
	@Autowired
	private ReadWord readWord;
	

	@RequestMapping(value="/word.do", produces = {"text/html;charset=UTF-8;"})
	@ResponseBody
	public String ViewWordConnect (){
		ViewObject<Object> result = new ViewObject<Object>();
		//System.out.println("ddd");
		try {
			List<Word> readWords = readWord.ReadWords("E://word//sss.doc");
			result.setDataInfo(readWords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json = JSONObject.fromObject(result);
		
		return json.toString();
		
	}

}
