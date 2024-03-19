package hello.hellospring.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
    
    @AfterEach
    public void afterEach() {
    	repository.clearStore();
    }
    
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqulTo(result);
    }
    
    @Test
    public void findByName() {
    	Member member1 = new Member();
    	member1.setName("spring1");
    	repository.save(member1);
    	
    	Member member2 = new Member();
    	member1.setName("spring2");
    	repository.save(member2);
    	
    	Member result = repository.findByName("spring1").get();
    	assertThat(result).isEqulTo(result);
    }
    
    @Test
    public void findAll() {
    	Member member1 = new Member();
    	member1.setName("spring1");
    	repository.save(member1);
    	
    	Member member2 = new Member();
    	member1.setName("spring2");
    	repository.save(member2);
    	
    	List<Member> result = repository.findAll();
    	assertThat(result.size()).isEqulTo(2);
    }
    
    //테스트는 서로 의존관계 없이 설계되어야 한다
    //저장소를 clear 해줘야한다.
}
