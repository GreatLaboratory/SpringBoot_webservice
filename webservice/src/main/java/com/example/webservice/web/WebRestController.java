package com.example.webservice.web;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.webservice.domain.posts.PostsSaveRequestDto;
import com.example.webservice.dto.PostsMainResponseDto;
import com.example.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsService postsService;
	
	@GetMapping("/main")
    public ModelAndView main(Model model) {
		ModelAndView mv = new ModelAndView("/main");
		List<PostsMainResponseDto> posts = postsService.findAllDesc();
		mv.addObject("posts", posts);
        return mv;
    }
	
	@GetMapping("/hello")
    public String hello() {
        return "Hello TWOSOMEPLACE";
    }
	
	@PostMapping("/posts")
	public void savePosts(@RequestBody PostsSaveRequestDto dto) {
		postsService.save(dto);
	}
	
//	@PostMapping("/posts")
//	public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
//		// 클라이언트의 request를 받아와서 파라미터로 쓸 때는 꼭 dto로 받아와줘야한다. (entity쓰면 안됨)
//		return postsService.save(dto);
//	}
}


// @RestController는 @ResponseBody를 모든 메소드에서 적용해줍니다. 
// 즉 hello 메소드의 결과는 "HelloWorld" 라는 문자열을 JSON 형태로 반환하게 됩니다. 

/**
보시면 postsRepository 필드에 @Autowired가 없습니다. 스프링프레임워크에선 Bean 을 주입받는 방식들이 아래와 같이 있는데요.

@Autowired
setter
생성자

이중 가장 권장하는 방식이 생성자로 주입받는 방식입니다.  (@Autowired는 비권장방식입니다.)
즉, 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과를 볼 수 있다는 것입니다.

그러면 위에서 생성자는 어디있을까요? 바로 @AllArgsConstructor 에서 해결해줍니다. 
모든 필드를 인자값으로 하는 생성자를 Lombok의 @AllArgsConstructor이 대신 생성해 준 것입니다. 
위 코드는 실제로는 아래와 같은 형태입니다.

@RestController
public class WebRestController {

    private PostsRepository postsRepository;

    public WebRestController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    ...
}
생성자를 직접 안쓰고 Lombok 어노테이션을 사용한 이유는 간단합니다. 
해당 클래스의 의존성 관계가 변경될때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함입니다. 
Lombok 어노테이션이 있으면 해당 컨트롤러에 새로운 서비스를 추가하거나, 기존 컴포넌트를 제거하는 등이 발생해도 생성자 코드는 전혀 손대지 않아도 됩니다.
**/