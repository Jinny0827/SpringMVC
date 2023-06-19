package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//JUnit5부터 test에서는 public 접근 제한자 없어도 됌.
class MemberRepositoryTest {
    // 스프링 사용시엔 싱글톤을 보장해주므로 굳이 싱글톤 지정하지 않아도 된다.
    // 싱글톤이라서 new 생성자 생성불가 - 싱글톤은 해당 클래스에서만 사용 가능
    // MemberRepository memberRepository = new MemberRepository();
    
    // public 으로 지정해놓은 getInstance 메서드 사용
     MemberRepository memberRepository = MemberRepository.getInstance();


     //테스트 종료시 테스트를 깔끔하게 초기화 하기 위해서 사용
     @AfterEach
    void afterEach() {
         memberRepository.clearStore();
     }

     //저장용 테스트
     @Test
    void save() {
         // given - 주어졌을때
         // 회원가입을 위한 클라이언트 입력값을 member 객체를 통해서 주입

            Member member = new Member("hello", 20);

         // when - 실행했을때
         // member에 주입된 값을 memberRepository(회원 정보 저장,조회 클래스)의 save 메서드를 통해 저장

         Member saveMember = memberRepository.save(member);

         // then - 결과가 이거여야 한다.
         // 회원정보가 저장되었는지 확인하기 위해 memberRepository 안의 저장정보 메서드를 불러와서 사용
         // findById(long id); = id는 Member을 통해 주입된 값중 getId - id 입력값을 통해서 정보 조회(이름, 나이)
         
         Member findMember = memberRepository.findById(saveMember.getId());
         // Assertions 사용시 Junit이 아닌 assertj.core.api.assertions 사용
         // findMember의 내용과 saveMember의 내용이 같은지 확인 - 찾은 멤버는 저장된 내용과 같아야 한다.
         assertThat(findMember).isEqualTo(saveMember);
     }
     
     // 실행용 테스트(회원정보 조회)
    @Test
    void findAll() {
        //given
        //조회를 위한 회원정보 저장
           Member member1 = new Member("member1", 20);
           Member member2 = new Member("member2", 30);

           memberRepository.save(member1);
           memberRepository.save(member2);
        //when
        //회원의 목록을 가져오기 위해 List 라이브러리 사용
        List<Member> result =  memberRepository.findAll();

        //then
        //저장된 내용이 2개인가? 테스트용
        // Assertions를 없애고 assertThat만 사용하기 위해서는
        // Assertions의 끝 부분에 커서를 두고 Alt + Enter를 사용하여 static import 사용
        assertThat(result.size()).isEqualTo(2);

        // result 안에 member1과 member2의 배치가 있냐
        // .contains()
        assertThat(result).contains(member1, member2);
        
    }
}
