# 1주차. [JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가](https://github.com/whiteship/live-study/issues/1)
- 날짜 : 20.11.15
- 목표 : 자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기

<hr>

## JVM이란 무엇인가
- Java Virtual Machine. 자바 가상 머신을 뜻함
- Java 어플리케이션을 클래스 로더를 통해 읽어 들여 자바 API와 함께 *실행*하는 것
- Java와 OS사이에서 중개자 역할을 수행하고, 메모리관리와 GC를 수행함 
- 스택 기반으로 동작함

## 컴파일 하는 방법
- 자바 소스코드를 `javac.exe` 사용하여 `.java`파일로 컴파일함
- `$ javac {파일명}.java`

## 실행하는 방법
- 프로그램이 실행되면 JVM이 OS로부터 프로그램이 필요로 하는 메모리를 할당받음
- JVM은 이 메모리르 용도에 따라 여러 영역으로 나누어 관리함 
- 자바 컴파일러(javac)가 자바 소스코드(java)를 읽어들여 자바 바이트코드(class)로 변환함
- 클래스 로더를 통해 class파일들을 JVM으로 로딩함
- 로딩된 class파일들은 실행 엔진(execution engine)을 통해 읽혀져서 Runtime Data Areas에 배치되어 실행됨
- 이 과정 속에서 JVM은 필요에 따라 GC 등 메모리 관리 작업을 수행함 

## 바이트코드란 무엇인가
- 특정 하드웨어가 아닌, 가상 컴퓨터에서 돌아가는 실행 프로그램을 위한 이진 표현법으로 가상 머신이 이해할 수 있는 0과 1로 구성된 이진 코드를 의미함 <br>
*바이너리 코드는 컴퓨터가 인식할 수 있는 0과 1로 구성된 이진 코드를 의미함*
- 컴파일되어 만들어진 바이트 코드는 특정 하드웨어의 기계 코드를 만드는 컴파일러의 입력으로 사용되거나 가상 컴퓨터에서 바로 실행됨

## JIT 컴파일러란 무엇이며 어떻게 동작하는지
- 컴파일러에 의해 로딩된 클래스가 실행될 때 인터프리터와 JIT 두가지 방식이 존재함
- 인터프리터(Interpreter) 
    - 자바 바이트 코드를 명령어 단위로 한줄 씩 읽어서 수행함
    - 속도가 느림
- JIT(Just-In-Time)
    - 인터프리터 방식으로 실행하다가 적절한 시점에 바이트 코드 전체를 컴파일하여 네이티브 코드로 변경하여 이후에는 네이티브 코드로 직접 실행하는 방식 
    - 네이티브 코드는 캐싱에 보관하기 때문에 한 번 컴파일된 코드의 수행이 빨라지는 장점을 가짐 
    - 해당 코드가 얼마나 자주 수행되는지 체크하여 일정 정도를 넘을 때에만 컴파일을 수행함 

## JVM 구성 요소
- 클래스 로더
    - Runtime시 동적으로 클래스를 할당 받은 메모리 영역인 Runtime Data Area로 로드함
    - 동적이란 뜻은 컴파일 타임이 아니라 런타임에 클래스를 참조함을 말함. 즉, 클래스를 참조할 때 해당 클래스를 로드하고 링크하는 데 이를 클래스 로더가 수행함
- 실행 엔진
    - 클래스 로더로 로딩된 class이 실행 엔진에 의해 실행되며 실행 방식에는 인터프리터와 JIT 두가지 방식이 존재함
- Runtime Data Area
    - 프로그램을 수행하기 위해 OS로부터 할당받은 메모리 공간 

        <image src="https://t1.daumcdn.net/cfile/tistory/992EE9465D08E9B903" width = 50%>
        
    1. PC Register 
        - Thread가 시작할 때 생성되는 공간으로 Tread마다 하나씩 존재함
        - Thread의 실행 기록을 하는 부분으로 현재 수행 중인 JVM의 주소를 가짐
    2. Stack Area
        - 프로그램 실행과정에서 임시로 할당되었다가 메소르르 빠져나가면 바로 소멸되는 특성의 데이터를 저장하기 위한 영역
        - 각종 변수나 임시 데이터, 스레드, 메소드의 정보를 저장함
        - 메소드 안에서 사용되는 local 변수, 호출된 메소드의 매개변수, 지역변수, 리턴 값 및 연산 시 일어나는 값들을 임시로 저장함.
    3. Native method stack
        - Java가 아닌 다른 언어로 작성된 코드를 위한 공간
        - Java Native Interface를 통해 바이트 코드로 전환하여 저장함
    4. Heap Area
        - `new` 연산자로 생성된 객체와 배열을 저장함 
    5. Method Area
        - class 영역이자 static 영역임
        - 클래스 정보를 처음 메모리 공간에 올릴 때 초기화되는 대상을 저장하기 위한 메모리 공간 
- Garbage Collector
    - JVM의 메모리를 관리함

## JDK와 JRE의 차이
- JRE(Java Runtime Environment)은 자바 프로그램을 동작시킬 때 필요한 라이브러리 파일들과 기타 파일들을 가지고 있음
- JDK(Java Development Kit)은 자바 개발 도구로 [자바 프로그램 개발을 위해 필요한 툴들](https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_%EA%B0%9C%EB%B0%9C_%ED%82%A4%ED%8A%B8)을 가짐 <br>
*자바 개발 도구(JDK)를 통해 개발된 프로그램은 자바 환경(JRE)에서 가상의 컴퓨터(JVM)에 의해 구동됨*


### 참고 자료 
- [자바가상머신, JVM이란 무엇인가](https://asfirstalways.tistory.com/158)
- [스프링 입문을 위한 자바 객체 지향의 원리와 이해](https://wikibook.co.kr/java-oop-for-spring/)
- [바이트코드 위키](https://ko.wikipedia.org/wiki/%EB%B0%94%EC%9D%B4%ED%8A%B8%EC%BD%94%EB%93%9C)
- [JDK 위키](https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_%EA%B0%9C%EB%B0%9C_%ED%82%A4%ED%8A%B8)
