package com.siggy.pro1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	//user -> Controller -> Service -> DAO -> mybatis -> DB
	
	//AutoWired 말고 resource로 연결
	@Resource(name = "boardService")
	//자바가 이름으로 연결해줍니다
	private BoardService boardService;
	
	
	
	@GetMapping("/board")
	public String board(Model model) {
		//서비스에서 값 가져오기
		// boardService.boardList(); //한줄로 줄이기
		model.addAttribute("list", boardService.boardList());
		return "board";
	}
	//http://localhost:8080/pro1/detail?bno=105
	//파라미터로 들어오는값 잡기
	@GetMapping("/detail")  //model은 jsp에 값을 붙이기 위해서 넣었습니다
	public String detail(HttpServletRequest request, Model model) {
		String bno = request.getParameter("bno");
		//bno에 요청하는 값이 있습니다. 이 값을 db까지 보내겠습니다.
		//System.out.println("bno" + bno);
		BoardDTO dto = boardService.detail(bno);
		model.addAttribute("dto", dto);
		
		return "detail";
	}
	
	
	
	
}
