package com.sbs.basic1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
// 개발자가 스프링부트에게 지정
// 이 클래스는 웹 요청을 받아서 작업을 한다
// @Controller 애노테이션이 붙은게 컨트롤러야
public class HomeController {
  int num;
  List<Person> personList = new ArrayList<>();

  @GetMapping("/home/main")
  // 개발자가 /home/main 이라는 요청을 보내면
  // 아래 메서드를 수행해줘
  // -> 결과 : "어서오세요" 스트링 반환
  @ResponseBody
  // 아래 메서드를 실행 후 리턴값을 응답으로 삼아
  // body에 출력
  public String showHome() {
    return "어서오세요";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String showHome2() {
    return "환영합니다";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showHome3() {

    return "스프링부트는 획기적이다234";
  }

  @GetMapping("/home/cookie/increase")
  @ResponseBody
  public void showCookie(HttpServletRequest req, HttpServletResponse res) throws IOException {
    // req : 요청
    // res : 응답에 대한 답장
    req.getCookies();
    res.getWriter().append("Hello");
  }

  @GetMapping("/home/cookie/reqAndRes")
  @ResponseBody
  public void reqAndRes(HttpServletRequest req, HttpServletResponse res) throws IOException {
    int age = Integer.parseInt(req.getParameter("age"));
    res.getWriter().append("Hello. Im %d years old".formatted(age));
  }

  @GetMapping("/home/cookie/increase2")
  @ResponseBody
  public int increase2(HttpServletRequest req, HttpServletResponse res) throws IOException {
    int countInCookie = 0;

    if(req.getCookies() != null) {
      countInCookie = Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("count"))
          .map(Cookie::getValue)
          .mapToInt(Integer::parseInt)
          .findFirst()
          .orElse(0);
    }

    int newCountInCookie = countInCookie + 1;

    res.addCookie(new Cookie("count",newCountInCookie + ""));
    return newCountInCookie;
  }

  @GetMapping("/home/plus")
  @ResponseBody
  public int showPlus(int a, @RequestParam(defaultValue = "0") int b) {
    // @RequestParam(defaultValue = "0")
    // 쿼리스트링을 넣을 때 파라미터 인식가능하게끔 하는 방법
    // 1) java compiler -> -parameters
    // 2) 파라미터를 생략했을 때 기본값으로 인식하게끔 하는 방법 -> @RequestParam(defaultValue="0")
    return a + b;
  }

  @GetMapping("/home/returnBoolean")
  @ResponseBody
  public Boolean showReturnBoolean() {
    return true;
  }

  @GetMapping("/home/returnDouble")
  @ResponseBody
  public double showReturnDouble() {
    return Math.PI;
  }

  @GetMapping("/home/returnArray")
  @ResponseBody
  public int[] showReturnArray() {
    int[] arr = new int[]{10, 20, 30};
    System.out.println(arr); // 주소값
    return arr;
  }

  @GetMapping("/home/returnList")
  @ResponseBody
  public List<Integer> showReturnList() {
//    List<Integer> list = new ArrayList<>(){{
//      add(10);
//      add(20);
//      add(30);
//    }

    List<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(20);

    return list;
  }

  @GetMapping("/home/returnMap")
  @ResponseBody
  public Map<String, Object> showReturnMap() {
    Map<String, Object> map = new HashMap<>();  // 순서가 없음
    Map<String, Object> map2 = new LinkedHashMap<>(); // 순서를 보장

    map.put("id", 1);
    map.put("subject", "스프링부트는 무엇인가요?");
    map.put("content", "스프링부트는 무엇이고 어떻게 사용하나요?");

    map2.put("개발자경력", 4);
    map2.put("소속회사", "교보생명보험, 신성통상, 펄어비스");
    map2.put("희망연봉", 5500);

    return map2;
  }

  @GetMapping("/home/returnArticle")
  @ResponseBody
  public Article showReturnArticle() {
    Article art1cle = new Article(1, "직장", "교보생명", new ArrayList<>() {{
      add(10);
      add(20);
      add(30);
    }});
    return art1cle;
  }

  @GetMapping("/home/returnArticle2")
  @ResponseBody
  public Article2 showReturnArticle2() {
    Article2 art1cle2 = new Article2(1, "야구", "한화", new ArrayList<>() {{
      add(1);
      add(2);
      add(3);
    }});
    return art1cle2;
  }

  @GetMapping("/home/returnArticleMapList")
  @ResponseBody
  public List<Map<String, Object>> showReturnArticleMapList() {
    Map<String, Object> articleMap1 = new LinkedHashMap<>() {{
      put("id", 1);
      put("subject", "제목1");
      put("content", "내용1");
      put("articleNo", new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
      }});
    }};
    Map<String, Object> articleMap2 = new LinkedHashMap<>() {{
      put("id", 2);
      put("subject", "제목2");
      put("content", "내용2");
      put("articleNo", new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
      }});
    }};

    List<Map<String, Object>> list = new ArrayList<>();
    list.add(articleMap1);
    list.add(articleMap2);
    return list;
  }

  @GetMapping("/home/returnArticleList")
  @ResponseBody
  public List<Article2> showReturnArticleList() {
    Article2 art1cle1 = new Article2(1, "제목1", "내용1", new ArrayList<>() {{
      add(4);
      add(5);
      add(6);
    }});
    Article2 art1cle2 = new Article2(2, "제목2", "내용2", new ArrayList<>() {{
      add(4);
      add(5);
      add(6);
    }});

    List<Article2> list = new ArrayList<>();
    list.add(art1cle1);
    list.add(art1cle2);

    return list;
  }

  @GetMapping("/home/addPerson")
  @ResponseBody
  public String addPerson(String name, int age) {
    Person p = new Person(name, age);
    System.out.println(p);

    personList.add(p);

    return "%d번 사람이 추가되었습니다".formatted(p.getId());
  }

  @GetMapping("/home/removePerson")
  @ResponseBody
  public String removePerson(int id) {
/*
    for(Person p : personList){
      if(p.getId() == id){
        target = p;
        break;
      }
    }

    if(target == null){
      return "id %d 존재하지 않습니다".formatted(id);
    }else{
      personList.remove(target);
      return "id %d 삭제 완료했습니다.".formatted(target.getId());
    }
*/
    // 리스트에 해당 요소가 있으면 삭제
    // 삭제가 성공되면 true 반환, 실패하면 false 반환
    boolean flag = personList.removeIf(person -> person.getId() == id);

    if(flag){
      return "id %d 삭제 완료했습니다.".formatted(id);
    }else{
      return "id %d 존재하지 않습니다".formatted(id);
    }
  }

  @GetMapping("/home/modifyPerson")
  @ResponseBody
  public String modifyPerson(int id, String name, int age) {
/*
    Person target = null;
    for(Person p : personList){
      if(p.getId() == id){
        target = p;
      }
    }
*/
    Person target = personList.stream()
        .filter(p->p.getId() == id) // 해당객체의 id값에 해당하는 것만 필터링
        .findFirst()      // 조회대상 중 하나만 남는 것을 필터링
        .orElse(null);  // 없으면 null 반환

    if(target == null){
      return "id %d 대상이 없습니다".formatted(id);
    }else{
      target.setAge(age);
      target.setName(name);

      return "id %d 대상이 수정되었습니다".formatted(id);
    }
  }


  @GetMapping("/home/showPeople")
  @ResponseBody
  public List<Person> showPeople() {
    return personList;
  }

  @GetMapping("/home/personTestCase")
  @ResponseBody
  public void personTestCase() {
    personList.add(new Person("김이병",20));
    personList.add(new Person("박상병",22));
    personList.add(new Person("장병장",24));

    System.out.println("테스트데이터 설정 완료");
  }
}

class Article{
  private final int id;
  private String subject;
  private String content;
  private List<Integer> articleNo;

  public Article(int id, String subject, String content, List<Integer> articleNo) {
    this.id = id;
    this.subject = subject;
    this.content = content;
    this.articleNo = articleNo;
  }

  public int getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<Integer> getArticleNo() {
    return articleNo;
  }

  public void setArticleNo(List<Integer> articleNo) {
    this.articleNo = articleNo;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", subject='" + subject + '\'' +
        ", content='" + content + '\'' +
        ", articleNo=" + articleNo +
        '}';
  }
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Article2 {
  private final int id;
  private String subject;
  private String content;
  private List<Integer> articleNo;
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Person{
  private static int lastId;
  private final int id;
  private String name;
  private int age;

  static{
    lastId = 0;
  }

  public Person(String name, int age){
    this(++lastId, name, age);
  }
}