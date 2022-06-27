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

Ubuntu 컴퓨터에 톰캣 설치
1. wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.64/bin/apache-tomcat-9.0.64.tar.gz
2. tar xf apache-tomcat-9.0.64.tar.gz
3. mv apache-tomcat-9.0.64 tomcat (폴더명 수정)
4. sh startup.sh(톰캣 실행 bin폴더 들어가야 함)
~~~
*** System restart required ***
Last login: Wed Jun 15 02:28:55 2022 from 115.94.162.198
ubuntu@ip-172-31-42-132:~$ clear
ubuntu@ip-172-31-42-132:~$ pwd
/home/ubuntu
ubuntu@ip-172-31-42-132:~$ ls
ROOT.war
ubuntu@ip-172-31-42-132:~$ su -
Password: 
root@ip-172-31-42-132:~# pwd
/root
root@ip-172-31-42-132:~# cd /home/ubuntu/
root@ip-172-31-42-132:/home/ubuntu# ls
ROOT.war
root@ip-172-31-42-132:/home/ubuntu# cd ..
root@ip-172-31-42-132:/home# clear
root@ip-172-31-42-132:/home# ls
apache-tomcat-9.0.64.tar.gz  tomcat  ubuntu
root@ip-172-31-42-132:/home# cd ubuntu/
root@ip-172-31-42-132:/home/ubuntu# ls
ROOT.war
root@ip-172-31-42-132:/home/ubuntu# cd ..
root@ip-172-31-42-132:/home# cd ubuntu/
root@ip-172-31-42-132:/home/ubuntu# ls
ROOT.war
root@ip-172-31-42-132:/home/ubuntu# cd ..
root@ip-172-31-42-132:/home# ls
apache-tomcat-9.0.64.tar.gz  tomcat  ubuntu
root@ip-172-31-42-132:/home# cd tomcat/
root@ip-172-31-42-132:/home/tomcat# ls
BUILDING.txt     LICENSE  README.md      RUNNING.txt  conf  logs  webapps
CONTRIBUTING.md  NOTICE   RELEASE-NOTES  bin          lib   temp  work
root@ip-172-31-42-132:/home/tomcat# cd webapps
root@ip-172-31-42-132:/home/tomcat/webapps# ls
ROOT  docs  examples  host-manager  manager
root@ip-172-31-42-132:/home/tomcat/webapps# rm -rf *
root@ip-172-31-42-132:/home/tomcat/webapps# ls
root@ip-172-31-42-132:/home/tomcat/webapps# cd home
-su: cd: home: No such file or directory
root@ip-172-31-42-132:/home/tomcat/webapps# cd ..
root@ip-172-31-42-132:/home/tomcat# cd ..
root@ip-172-31-42-132:/home# ls
apache-tomcat-9.0.64.tar.gz  tomcat  ubuntu
root@ip-172-31-42-132:/home# cd ubuntu/
root@ip-172-31-42-132:/home/ubuntu# ls
ROOT.war
root@ip-172-31-42-132:/home/ubuntu# mv ROOT.war /home/tomcat/
BUILDING.txt     README.md        conf/            webapps/
CONTRIBUTING.md  RELEASE-NOTES    lib/             work/
LICENSE          RUNNING.txt      logs/            
NOTICE           bin/             temp/            
root@ip-172-31-42-132:/home/ubuntu# mv ROOT.war /home/tomcat/webapps/
root@ip-172-31-42-132:/home/ubuntu# ls
root@ip-172-31-42-132:/home/ubuntu# cd /home/tomcat/webapps/
root@ip-172-31-42-132:/home/tomcat/webapps# ls
ROOT  ROOT.war
root@ip-172-31-42-132:/home/tomcat/webapps# ^C
~~~

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

EC2
> 내 개인 컴퓨터
>+ 자바,톰캣,mySql

S3
>대표적인 이미지,호스팅 서버

RDS
>설치된 DB를 구매

# RDS
데이터 베이스전용 서버 구매

+ AWS

데이터베이스 생성 -> 사용SQL 선택 -> 자격증면선택 (root 권한이름) -> 암호설정(DB암호) ->구매할 컴퓨터 -> 스토리지(최소 20개 defult) ->
+ 퍼블릭 값

(회사 는 당연히 private) 예제 사용을 위해 public -> 보안 defult 선택 -> DB생성

+ 보안그룹

-> 인바운드&아웃바운드 MYSQL생성후 포트번호 3306 입력 후 예 -> Debver 등 프로그램에서 DB생성 -> serverhost : 만든 RDS 주소 사용자이름 비밀번호 입력 -> 만들어진 DB이름수정후 
기존 데이터를 옮기거나 데이터 생성 - > yaml 파일수정