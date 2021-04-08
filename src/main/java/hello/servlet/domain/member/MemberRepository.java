package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용고려
 */
public class MemberRepository {

    private static  Map<Long, Member> store = new HashMap<>(); // 아이디기 때문에 많이 생성 안되고 한번만 됨
    private static long sequence = 0L;

    // 싱글톤으로 만들것
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance( ){
        return instance ; // 싱글톤이기 때문에 무조건 이걸로 불러와야함
    }

    // 싱글톤을 만들때는 private로 , 아무나 생성자를 만들지 못하도록 함.
    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    // 이렇게 하면, 스토어에 있는 모든 값들을 다 꺼내서 새로운 리스트로 넘겨줌 - 왜냐면, arraylist를 조작해도, store를 건들고 싶지 않아서 이렇게 함.
    // 이렇게 하더라고, 스토어에있는 멤버를 직접가져와서 수정하면, 수정이 되버리긴 함.
    // 그래서 스토어 자체를 보호하기 위해서 짠 코드
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
