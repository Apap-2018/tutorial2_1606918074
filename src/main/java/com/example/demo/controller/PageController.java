package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name")String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
	}
	
	@RequestMapping(value={"/challenge", "/challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String viralGenerator(@RequestParam(value="a", required=false, defaultValue="0") String a,
			@RequestParam(value="b", required=false, defaultValue="0")String b, Model model){
		/*
		 * mendefinisikan variabel
		 */
		String hm = "hm";
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		
		int anum = Integer.parseInt(a);
		int bnum = Integer.parseInt(b);
		
		/*
		 * menambahkan "m"
		 */
		if (anum>1) {
			for(int i=0; i<anum-1; i++) {
				hm+="m";
			}
		}
		
		/*
		 * melipatgandakan suku kata
		 */
		hm += " ";
		String hmresult = hm;
		if (bnum>1) {
			for (int i=0; i<bnum-1; i++) {
				hmresult+= hm;
			}
		}
		model.addAttribute("hmresult", hmresult);
		return "generator";
	}
}
