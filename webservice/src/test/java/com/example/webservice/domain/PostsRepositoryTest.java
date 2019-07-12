package com.example.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.posts.Posts;
import com.example.webservice.domain.posts.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
	
	@Autowired
    private PostsRepository postsRepository;
	
	@After
    public void cleanup() {
        /** 
        이후 테스트 코드에 영향을 끼치지 않기 위해 
        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        **/
        postsRepository.deleteAll();
    }
	
	@Test
	public void openList() {
		// given -> 테스트 환경 구축 단계
		postsRepository.save(Posts.builder().title("알라딘").content("재밌나").author("명관").build());
		// 개쩌네... posts엔티티에 builder메소드로 걍 바로 insert시켜버림...
		
		// when -> 테스트 하고자 하는 행위 선언. 여기선 Posts가 DB에 insert 되는것을 확인하기 위함
		List<Posts> list = postsRepository.findAll();
		
		// then -> 테스트 결과 검증. 실제로 DB에 insert 되었는지 확인하기 위해 조회후, 입력된 값 확인
		Posts post = list.get(0);
		assertThat(post.getAuthor(), is("명관"));
		assertThat(post.getTitle(), is("알라딘"));
		assertThat(post.getContent(), is("재밌나"));
	}
	
	@Test
	public void timetest() {
		//given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder().title("시간이").content("무사히").author("들어갔을까?").build());
		
		//when
		List<Posts> list = postsRepository.findAll();
		
		//then
		Posts posts = list.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
	}
	
	// DB가 설치가 안되어있는데 Repository를 사용할 수 있는 이유는, SpringBoot에서의 테스트 코드는 메모리 DB인 H2를 기본적으로 사용하기 때문입니다. 
	// 테스트 코드를 실행하는 시점에 H2 DB를 실행시킵니다. 
	// 테스트가 끝나면 H2 DB도 같이 종료됩니다.
	
	
}
