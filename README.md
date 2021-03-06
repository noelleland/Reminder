일정관리 & 일기 어플
==========================================================
NaLang(Natural Language)팀 졸업프로젝트 - 충남대학교 컴퓨터공학과
----------------------------------------------------------
# 목표
자연어처리 기술(음성인식, 사진)을 활용한 일정관리 및 일기 어플리케이션 개발
<br />
# 조원
- 김연훈 : 201402330, yeonhunkim@mensakorea.org (yeonhunkim@o.cnu.ac.kr) 
- 이동원 : 201402387, 201402387@o.cnu.ac.kr
- 김노은 : 201604136, 201604136@o.cnu.ac.kr
<br />

# 진행도
## 완료
#### 1.
  
## 미완료    
#### 1. 디자인
- 앱 이름, 아이콘
- 레이아웃, 이미지
<br /><br />

#### 2. 데이터베이스 설계
- 날짜, 입력 내용 저장
<br /><br />
 
#### 3. 기능(액티비티) 구현
- 메뉴 (구체화 필요)
  - 일정표(월간, 주간, 일간), 일기 입력, 프로필 관리, 일기장
<br /><br /> 

- 잠금화면에서 일기를 입력받기
  - N시간 이상 폰을 사용하지 않은 경우
    - 자고 일어난 경우에는 잠금화면에서 음성/텍스트로 꿈일기를 저장
    - 정기적 일과 또는 약속이 있었던 경우에는 있었던 일을 음성/텍스트로 저장
  - 무작위로
    - 무슨 일이 있었는지, 무엇을 먹었는지, 무슨 생각을 했는지 등의 다양한 질문(무작위 또는 선택 가능)을 통하여
      사용자가 일기를 수시로 짧게 입력할 수 있도록 유도
<br /><br />
  
- SNS 연동 (가능한지, 가능하다면 어떻게 할 수 있는지 조사 필요)
  - 카카오 계정으로 로그인
  - 통화/문자 기록을 가져와 일기에 반영
  - 카카오톡 대화 기록을 가져와 일기에 반영
  - 페이스북, 인스타 기록/사진을 가져와 일기에 반영
<br /><br />
  
- 기록 정리
  - 하루동안 입력받은 토막 일기들을 완전한 하나의 일기로 통합 
    - 시각화(원형 시간표, 타임라인)
    - 문장형
<br /><br />
  
# 참조
## Github Markdown
  - Markdown 작성법 : <https://gist.github.com/ihoneymon/652be052a0727ad59601>
   
## 안드로이드
  - 커스텀 달력 예제 : <https://namget.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%BB%A4%EC%8A%A4%ED%85%80-%EB%8B%AC%EB%A0%A5-%EC%98%88%EC%A0%9C-Android-Custom-CalendarView-Example>
  - Fragment 예제 : 
  <http://esocwiki.blogspot.com/2018/01/android-using-fragment.html>
  <https://wdprogrammer.tistory.com/9>
  - 원형 시간표 버튼 예제 : <http://www.masterqna.com/android/3865/%EB%8B%A4%EC%9D%8C-%EC%95%B1%EC%B2%98%EB%9F%BC-%EC%9B%90%EC%9C%BC%EB%A1%9C-%EB%90%98%EA%B3%A0-%EC%8B%9C%EA%B0%84%ED%91%9C%EC%B2%98%EB%9F%BC-%EB%82%98%EB%89%98%EC%96%B4%EC%A7%84-%EB%B2%84%ED%8A%BC%EC%9D%84-%EB%A7%8C%EB%93%A4%EB%A0%A4%EB%A9%B4-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%95%B4%EC%95%BC-%ED%95%98%EB%82%98%EC%9A%94>
  - 잠금화면 :
  <https://medium.com/@dlgksah/waterpin-technical-stack-3-lockscreen-b442217c9f57>
  - SharedPreference :
  <https://blog.yena.io/studynote/2017/12/18/Android-Kotlin-SharedPreferences.html>

## 데이터베이스
  - Room 라이브러리 예제 : <https://namget.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-ROOM-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-%EC%BD%94%EB%A3%A8%ED%8B%B4>
