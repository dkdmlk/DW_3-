# 사이트 로그인
+ POST : 중요한 데이터를 서어베 전송할 때 POST를 사용해서 데이터를 Body에 숨긴다.

+ 쿠키 : 쿠키는 웹브라우저에 데이터를 임시 저장

+ 세션 : 세션은 서버에 데이터를 임시 저장

# AWS(클라우드)
클라우드를 사용하는 이유
1. 상황에 맞는 컴퓨터 구입
2. 시간 절약 (민첩성)
3. Auto Scale (자동 확장)

+ 비용절감
+ 애자일

## EC2
+ 탄력적IP(EIP) : 고정 public IP 만듬
ubuntu는 apt라는 명령어를 사용 하요 프로그램을 다운로드 받는다

* 자바 JDK 설치
1. sudo apt update
2. sudo apt instaill
3. javac -version

Ubuntu ROOT 계정 만들기
1. sudo passwd root (root 비밀번호 초기 설정)
2. su - (root 로그인)

톰캣 설치
1. wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.64/bin/apache-tomcat-9.0.64.tar.gz
2. tar xf apache-tomcat-9.0.64.tar.gz
3. mv apache-tomcat-9.0.64 tomcat (폴더명 수정)
4. sh startup.sh(톰캣 실행 bin폴더 들어가야 함)

포트 확인
1. netstat -tnlp

## S3 (Simple Stoeage Service)
1. 이미지,영상 전용 서버로 많이 사용
2. 백업 서버로 많이 사용
3. 웹 호스팅(빌린다) 서버로 사용(v) -> 정적 웹 
>+ =>S3임대해서 내 HTML을 S3에 업로드 하겠다.

- 정적 웹: HTMl , JS , JQuery
- 동적 웹: (웹 프레임워크 + 템플릿엔진)
> Spring boot + JSP
+ 차이점 : DB를 호출할수 있냐 없냐

특징 : 저장공간 무한

가격 : 시간당 저장 용량만큼 가격 책정 (*5GB이하는 1년간 공짜)
버킷정책
~~~
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:*",
            "Resource": "arn:aws:s3:::hyunsangwon.pro/*"
        }
    ]
}
~~~
