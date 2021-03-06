# 2주차. [자바 데이터 타입, 변수 그리고 배열](https://github.com/whiteship/live-study/issues/2)
- 날짜 : 20.11.21
- 목표 : 자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법 이해

<hr>

## 프리미티브 타입과 레퍼런스 타입
- 타입(Type)이란 데이터가 메모리에 어떻게 저장되고, 프로그램에서 어떻게 처리되어야 하는지를 명시적으로 알려줌
- 자바에는 크게 **프리미티브 타입(Primitive Type)**과 **레퍼런스 타입(Reference Type)**이 존재함
    - 프리미티브 타입 :
        - 미리 정의하여 제공되는 8가지의 기본형 타입
        - 기본값이 있기 때문에 `null`이 존재하지 않으며 실제 값은 스택 메모리에 저장됨
    - 레퍼런스 타입 : 
        - 프리미티브 타입 외의 타입들
        - 빈 객체를 의미하는 `null`이 존재하며 실제 값이 저장되어 있는 주소값이 힙 메모리에 저장됨
        - 문법상으로는 에러가 없을 수 있고, 실행했을 때 런타임 에러가 발생할 수 있음 
        - 배열(array), 열거(enum), 클래스(class), 인터페이스(interface)가 있음

<br>

## 프리미티브 타입 종류와 값의 범위 그리고 기본 값
| 종 류 |  타 입   |   값의 범위  | 기본값  |  메모리 크기  | 
| ---- |  ------ | ---------- | ----- | ---------- |
| boolean | 논리형 | true/false | false | 1 byte |
| byte | 정수형 | -128 ~ 127 | 0 | 1 byte |
| short | 정수형 | -32,768 ~ 32,767 | 0 | 2 byte |
| int | 정수형 | -2,147,483,648 ~ 2,147,483,647 | 0 | 4 byte |
| long | 정수형 | -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807 | 0L | 8 byte |
| float | 실수형 |  (3.4 X 10^-38) ~ (3.4 X 10^38) 의 근사값 | 0.0F | 4 byte |
| double | 실수형 |  (3.4 X 10^-308) ~ (3.4 X 10^308) 의 근사값 | 0.0 | 8 byte |
| char | 문자형 | 0 ~ 65,535 | '\u0000' | 2 byte |

<br>

## 리터럴
- 변수에 넣지 않은, 변하지 않는 데이터 그 자체를 뜻함 <br>
*상수와 리터럴 둘 다 변하지 않는 값이지만, 상수는 변하지 않는 변수를 뜻함*
    ```java
    int num1 = 5;    // 리터럴은 5이고,
    final num2 = 10; // 아래 경우는 상수!
    ```

<br>

## 변수 선언 및 초기화하는 방법
- 변수를 선언하고 처음으로 값을 저장하는 것을 변수 초기화라고 함
    ```java
    int a;
    String b;

    a= 1;
    b = "hello java";
    ```
- 변수 초기화 방법 3가지
    - 명시적 초기화 (explicit initialization) : 변수 선언과 동시에 초기화하는 것 
        ```java
        int a = 3;
        String b = "hello";
        ```
    - 생성자(constructor)
    - 초기화 블럭(initialization block)

<br>

## 변수의 스코프와 라이프타임
- 스코프란 영역, 즉 변수가 존재할 수 있는 범위를 뜻하며 전역 스코프와 지역 스코프가 있음. 변수가 선언된 위치가 스코프를 결정함
- 중괄호 `{ }`로 한 영역이 생성된다면 그 영역에 관한 scope가 형성됨
- 전역 스코프의 라이프 타임은 전역에 선언되어 어디서든 접근이 가능하여 프로그램이 종류까지 유지되고, 지역 스코프는 변수가 선언된 스코프 안에서만 접근이 가능하여 선언 스코프내에서만 유지됨

<br>

## 타입 변환, 캐스팅 그리고 타입 프로모션
- 타입 변환이란 하나의 타입을 다른 타입으로 바꾸는 것을 의미함
- boolean형을 제외한 프리미티브 타입간의 타입 변환은 가능하고, 강제 형변환(캐스팅)과 자동 형변환(타입 프로모션)이 있음
    - **강제 형변환(explicit conversion, casting)** : 사용자가 타입 캐스트 연산자`()`를 통해 강제로 타입을 변환함
        ```java
        int a = 1;
        int b = 4;

        double num1 = a/b;
        double num2 = (double) a/b;

        System.out.println(num1);   // 0.0
        System.out.println(num2);   // 0.25
        ```
    - **자동 형변환(implicit conversion)** : 연산 과정에서 컴파일러가 자동으로 타입을 변환함
        ```java
        double a = 10;
        System.out.println(a);   // 10.0
        ```
        - 자바에서 자동 형변환은 데이터의 손실이 최소화되는 방향으로 이루어짐 <br>
        *byte > short > int > long > float > double*    
    
<br>

## 1차 및 2차 배열 선언하기
- 배열(array)이란 같은 타입의 변수들로 이루어진 집합
- 배열을 선언하는 방식에 따라 1차원 배열, 2차원 배열, 다차원 배열이 가능함
    - **1차원 배열** : 가장 기본적인 배열
        ```java
        // 1차원 배열 선언 방법
        int arr1[];
        int[] arr2;
        // int arr3[10];    -> 크기 선언 불가
        // arr4[0] = 1;     -> 초기화나 생성 전 사용 불가

        // 1차원 배열 초기화
        int arr1[] = {1, 2, 3}
        for(int i =0; i<= arr2.length; i++) {
            arr2[i] = i;
        }
        ```
    - **2차원 배열** : 1차원 배열을 가지는 배열 
        ```java
        // 2차원 배열 선언 방법
        int arr1[][];
        int[][] arr2;

        // 2차원 배열 초기화
        int arr1[][] = {{1,2,3}, {4,5,6}}
        int arr2[] = new int[1][2];
        // 
        }
        ```
<br>

## 타입 추론, var
- 타입 추론이란 코드 작성 당시 타입이 정해지지 않았지만 컴파일러가 그 타입을 유추하는 것을 뜻함
- 자바10 이상에서는 타입 추론을 지원하는 `var`가 추가됨. 단, 지역변수로 사용해야하고 선언과 동시에 초기화가 필요함
    ```java
    // java 9 이하
    String msg1 = "hello";
    // java 10 이상
    var msg2 = "hello java";
    ```
<br>

### 참고 자료 
- [자바의 정석](http://www.yes24.com/Product/Goods/24259565)
- https://gbsb.tistory.com/6#recentComments
- https://doublesprogramming.tistory.com/73
- https://improver.tistory.com/428
- http://www.tcpschool.com/java/
- https://velog.io/@bk_log/Java-%ED%83%80%EC%9E%85-%EC%B6%94%EB%A1%A0
