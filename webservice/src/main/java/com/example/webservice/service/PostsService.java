package com.example.webservice.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webservice.domain.posts.PostsRepository;
import com.example.webservice.domain.posts.PostsSaveRequestDto;
import com.example.webservice.dto.PostsMainResponseDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostsService {
	
	private PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		return postsRepository.save(dto.toEntity()).getId();
		// .toEntity()는 posts라는 엔티티하나 반환해내는 메소드
		// .save()는 insert메소드느낌..?
	}
	
	@Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}


// Service 메소드는 Entity를 바로 받지 않고, 이전에 생성한 Save용 DTO인 PostsSaveRequestDto를 받아서 저장합니다.
// 비지니스 로직 & 트랜잭션 관리는 모두 Service에서 관리하고, View 와 연동되는 부분은 Controller에서 담당하도록 구성합니다.
// 일반적으로 DB 데이터를 등록/수정/삭제 하는 Service 메소드는 @Transactional를 필수적으로 가져갑니다. 
