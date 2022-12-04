# LoaninfoProgram

전자정부 표준프레임워크 (egovframework) 기반 도서대출/반납관리시스템 📚
<br/><br/>
### 0. 프로젝트 기간
2022.01.10 ~ 2022.02.28
<br/>

### 1. Tool
   + backend : <b>jsp, java, spring (xml)</b>, jdbc & <b>ibatis</b>
   + front : <b>jsp, javascript</b>, html & css <br/>

### 2. DBMS
   + oracle <br/>

### 3. 개발환경
   + egovframework eclipse 3.8 
   + oracle 11g xe
   + jdk/jre 1.8
   + ojdbc6.jar
   + apache tomcat 8.5 <br/>

### 4. 기능 소개
   + 회원 관리
      + 회원 가입
         <center><img src="https://user-images.githubusercontent.com/90493141/205480232-9c5872e0-79aa-4009-8918-8544976d8d60.png" width="700"></center>
         
         + 회원번호는 숫자 8자리로 구성되며, 자동부여된다.
         + 이름/휴대폰 번호는 필수로 입력해야 한다.
         + 휴대폰 양식은 ***-****-****으로 입력해야 한다.
         
      + 회원 조회
         <center><img src="https://user-images.githubusercontent.com/90493141/205480841-f8fb3fb2-6d28-487a-9c31-541e6594bb2c.png" width="700"></center>
         
         + 도서관에 가입된 모든 회원을 조회할 수 있다.
         + 회원번호를 클릭하면 선택한 회원 정보를 상세보기 할 수 있으며, 해당 해원을 수정 및 삭제할 수 있다.

      + 회원 수정/삭제 (회원 상세보기)
         <center><img src="https://user-images.githubusercontent.com/90493141/205481195-f552110a-3f9f-447b-8a69-4bee6444d089.png" width="700"></center>
         
         + 회원번호를 제외한 모든 항목을 수정할 수 있다.
        
   + 도서 관리
      + 도서 등록
         <center><img src="https://user-images.githubusercontent.com/90493141/205481419-204cef4e-0348-4294-a007-e13b4e6f9e16.png" width="700"></center>
         
         + 도서명/저자/출판사/청구기호 입력란을 모두 입력해야 한다.
         + 출판사+청구기호가 이미 등록된 도서일 경우, 신규 도서 등록이 되지 않는다.
         
      + 도서 조회
         <center><img src="https://user-images.githubusercontent.com/90493141/205481595-d5d90fa6-dc6b-45bb-ae0e-27f0790534a7.png" width="700"></center>
         
         + 도서관에 등록된 모든 도서를 조회할 수 있다.
         + 도서명을 클릭하면 선택한 도서 정보를 상세보기 할 수 있으며, 해당 도서를 수정 및 삭제할 수 있다.

      + 도서 수정/삭제 (회원 상세보기)
         <center><img src="https://user-images.githubusercontent.com/90493141/205481756-1e105e61-7bce-41dd-b6a0-a8c8944e37cc.png" width="700"></center>
         
         + 청구기호를 제외한 모든 항목을 수정할 수 있다.

   + 대출/반납
      + 회원 선택
         + 회원 코드로 바로 검색
            <center><img src="https://user-images.githubusercontent.com/90493141/205482517-f35e2d87-267f-477c-9cfc-4a54b3659605.png" width="700"></center>
            
            + 도서/회원 검색에서 유효한 회원코드를 입력하면 해당 회원 정보 및 도서대출 정보가 각각 우측 상단, 하단에 표시된다.
            + 대출/반납 정보에서 해당 회원의 현재 대출 및 반납중인 도서를 조회할 수 있으며, 이미 반납한 도서는 반납 날짜가 같이 표시되고 대출중인 도서를 선택하여 반납할 수 있다.
        
         + 회원명으로 검색 후 선택
            <center><img width="300" alt="image" src="https://user-images.githubusercontent.com/90493141/205482756-93750047-5020-4505-a8e0-4b2f4655af09.png"></center>
            <center><img src="https://user-images.githubusercontent.com/90493141/205482693-7a567a4d-15dc-49d5-b292-70d587432caf.png" width="700"></center>
            
            + 회원 코드가 기억이 나지 않는 회원들을 위한 기능이며, 도서/회원 검색에서 회원 명을 입력하면 입력한 키워드가 포함된 모든 회원 리스트를 조회한다.
            + 사용자의 정보와 일치하는 회원을 선택한 후, 위와 같은 기능을 동일하게 수행할 수 있다.
            
      + 서명 검색
         <center><img width="700" alt="image" src="https://user-images.githubusercontent.com/90493141/205483156-11ca9d12-88de-4967-80a1-f506a61ff7c3.png"></center>
         
         + 회원정보가 조회된 상태에서 서명 검색을 할 수 있다.
         + 서명 검색에 입력한 키워드가 포함된 모든 도서를 조회한다.
         + 조회된 도서 리스트에서 대출 여부의 의미는 현재 조회된 사용자가 대출중인 도서인 경우 <b>'반납 가능'</b>, 다른 사용자가 대출중인 도서인 경우 <b>'대출 불가'</b>, 어떤 사용자도 대출중인 도서가 아닐 경우 <b>'대출 가능'</b>이라고 표시된다.
         + 서명 조회로도 대출 및 반납 기능을 수행할 수 있다.

      + 청구기호 조회 (도서 반납 기능)
         <center><img src="https://user-images.githubusercontent.com/90493141/205483467-c2ad4b1f-b795-46fd-8e05-af94804a76ea.png" width="700"></center>
         
         + 어떤 사용자가 대출중인 도서의 경우, 청구기호 조회로 바로 반납할 수 있다.
         + 반납이 완료되면 대출했던 사용자와 해당 도서의 도서명이 포함된 팝업 메시지를 출력한다.

