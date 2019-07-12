package com.example.webservice.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
// JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 합니다.
@EntityListeners(AuditingEntityListener.class)
// BaseTimeEntity클래스에 Auditing 기능을 포함시킵니다.
public abstract class BaseTimeEntity {
	
	// Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.
	@CreatedDate
	private LocalDateTime createdDate;
	
	// 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
}
