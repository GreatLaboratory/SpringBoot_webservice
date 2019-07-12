package com.example.webservice.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.webservice.domain.BaseTimeEntity;

// 특히 서비스 구축단계에선 테이블 설계(여기선 Entity설계)가 빈번하게 변경되는데, 이때 Lombok의 어노테이션들은 코드 변경량을 최소화시켜주기 때문에 아주 강력 추천하는 라이브러리입니다.
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 여기서 Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity클래스라고도 합니다. 
// JPA를 사용하시면 DB 데이터에 작업할 경우 실제 쿼리를 날리기 보다는, 이 Entity 클래스의 수정을 통해 작업합니다.

@NoArgsConstructor(access= AccessLevel.PROTECTED)
// 기본 생성자 자동 추가 + 기본생성자의 접근 권한을 protected로 제한
// Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity 클래스를 생성하는것은 허용하기 위해 추가
@Getter
// 클래스내 모든 필드의 Getter 메소드를 자동생성
@Entity
// 테이블과 링크될 클래스임을 나타냅니다. 언더스코어 네이밍(_)으로 이름을 매칭합니다. ex) SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity{
	
	@Id
	// 해당 테이블의 PK 필드를 나타냅니다.
	@GeneratedValue
	// PK의 생성 규칙을 나타냅니다. 기본값은 AUTO 로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 됩니다.
	private Long id;
	
	@Column(length = 500, nullable = false)
	// 테이블의 컬럼을 나타내면, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됩니다. 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있을경우 사용합니다.
	// 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex: title), 타입을 TEXT로 변경하고 싶거나(ex: content) 등의 경우에 사용됩니다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    // 해당 클래스의 빌더패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함, static으로 쓰이는듯
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
