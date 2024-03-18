package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository{
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L; //회원 ID의 연속성을 유지하기 위한 변수, 회원을 저장할 때마다 증가시켜 새로운 ID를 할당

	@Override
	//회원을 저장하는 save 메서드
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	//ID를 기반으로 회원을 찾는 메서드
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id)); //null인 경우도 있으니 ofNullable로 감싸준다
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
			.filter(member -> member.getName().equals(name)) //member로 넘어온 getName이 파라미터로 넘어온 name과 같은지 비교
			.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

}
