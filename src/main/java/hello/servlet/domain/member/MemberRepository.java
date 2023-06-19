package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
    동시성 문제가 전혀 고려되어있자않음, **** 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려 ***

 */
public class MemberRepository {

    //아이디의 1. Long 타입과 2. Member 타입 두가지 타입을 가진 store 객체 생성
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤 타입으로 객체 생성 -> 스프링 컨테이너 사용시엔 스프링을 사용하기 때문에 지정한다.
    // 스프링 사용시엔 싱글톤을 보장해주므로 굳이 싱글톤 지정하지 않아도 된다.
    private static final MemberRepository instance = new MemberRepository();

    //이 메서드(getInstance)로만 조회할수있게 만든다.
    public static MemberRepository getInstance() {
        return instance;
    }

    // 프라이빗으로 생성자를 막아야 한다 -> 아무나 생성자 사용못하게(생성 못하게)
    private MemberRepository() { }

    // 회원저장(회원가입)용 메서드
    public Member save(Member member) {
        // 아이디 값에 시퀀스 지정(하나씩 자동증가, AutoIncreament)
        member.setId(++sequence);
        // Map 객체인 클라이언트의 입력 id에 맞춘 member 객체를 매개변수 사용해서 데이터 입력
        store.put(member.getId(), member);
        return member;
    }

    // 회원정보조회용 메서드(1개)
    public Member findById(Long id) {
        return store.get(id);
    }
    
    // 회원목록조회용 메서드(다수) - java.util.List 사용
    public List<Member> findAll() {
        // 모든 값을 가져오기 위한 List 인터페이스 안 values() 메서드 사용
        return new ArrayList<>(store.values());
    }

    // 회원목록 전체 삭제를 위한 테스트 메서드
    public void clearStore() {
        store.clear();
    }
}
