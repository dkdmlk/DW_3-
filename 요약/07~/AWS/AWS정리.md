# S3 (Simple Stoeage Service)

1. 이미지,영상 전용 서버로 많이 사용
2. 백업 서버로 많이 사용
3. 웹 호스팅(빌린다) 서버로 사용(v) -> 정적 웹
   > - =>S3임대해서 내 HTML을 S3에 업로드 하겠다.

- 정적 웹: HTMl , JS , JQuery
- 동적 웹: (웹 프레임워크 + 템플릿엔진)
  > Spring boot + JSP

* 차이점 : DB를 호출할수 있냐 없냐

특징 : 저장공간 무한

가격 : 시간당 저장 용량만큼 가격 책정 (\*5GB이하는 1년간 공짜)
버킷정책

```
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
```

# RDS

데이터 베이스전용 서버 구매

- AWS

데이터베이스 생성 -> 사용SQL 선택 -> 자격증면선택 (root 권한이름) -> 암호설정(DB암호) ->구매할 컴퓨터 -> 스토리지(최소 20개 defult) ->

- 퍼블릭 값

(회사 는 당연히 private) 예제 사용을 위해 public -> 보안 defult 선택 -> DB생성

- 보안그룹

-> 인바운드&아웃바운드 MYSQL생성후 포트번호 3306 입력 후 예 -> Debver 등 프로그램에서 DB생성 -> serverhost : 만든 RDS 주소 사용자이름 비밀번호 입력 -> 만들어진 DB이름수정후
기존 데이터를 옮기거나 데이터 생성 - > yaml 파일수정

# EC2

Amazon Elastic Compute Cloud(Amazon EC2)는 안전하고 크기 조정이 가능한 컴퓨팅 용량을 클라우드에서 제공하는 웹 서비스입니다.

이용자는 높은 초기비용, 유지및보수등의 다양한 제약에서 벗어나서 단시간안에 여러 가지 프로세서, 스토리지, 네트워킹, 운영 체제, 구매 모델을 선택하여 생성 할 수 있으며 사양변경, 삭제도 쉽게 실행 할 수 있는 AWS서비스 입니다.

1. 범용 인스턴스

균형 있는 컴퓨팅, 메모리 및 네트워킹 리소스를 제공하며, 다양한 여러 워크로드에 사용할 수 있습니다.

웹 서버 및 코드 리포지토리와 같이 이러한 리소스를 동등한 비율로 사용하는 애플리케이션에 적합합니다.

2. 컴퓨팅 최적화 인스턴스

고성능 프로세서를 활용하는 컴퓨팅 집약적인 애플리케이션에 적합합니다.

배치 처리 워크로드, 미디어 트랜스코딩, 고성능 웹 서버, HPC(고성능 컴퓨팅), 과학적 모델링, 전용 게임 서버 및 광고 서버 엔진, 기계 학습 추론 및 기타 컴퓨팅 집약적인 애플리케이션에 매우 적합합니다.

3. 메모리 최적화 인스턴스

메모리에서 대규모 데이터 세트를 처리하는 워크로드를 위한 빠른 성능을 제공하기 위해 설계되었습니다.

4. 과속화된 컴퓨팅 인스턴스

하드웨어 액셀러레이터 또는 코프로세서를 사용하여 부동 소수점 수 계산이나 그래픽 처리, 데이터 패턴 일치 등의 기능을 CPU에서 실행되는 소프트웨어보다 훨씬 효율적으로 수행합니다.

5. 스토리지 최적화 인스턴스

로컬 스토리지에서 매우 큰 데이터 세트에 대해 많은 순차적 읽기 및 쓰기 액세스를 요구하는 워크로드를 위해 설계되었습니다.

애플리케이션에 대해 지연 시간이 짧은, 수만 단위의 무작위 IOPS(초당 I/O 작업 수)를 지원하도록 최적화되었습니다.

- AWS컴퓨터 들어가기위해 X매니저등 프로그램이 필요
- 탄력적IP(EIP) : 고정 public IP 만듬
  ubuntu는 apt라는 명령어를 사용 하요 프로그램을 다운로드 받는다

## EC2 war설치

- 자바 JDK 설치

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

board.War 파일이름 -> ROOT.War -> AWS클라우드 컴퓨터에 파일 옮기기후

X매니저 등 접속프로그램을 이용하여 설치

리눅스 명령어

1. ls : 현재 경로 파일 및 폴더 확인
2. ll :현재 경로 파일및 폴더+권환 확인
3. cd : 폴더 접속
   > ex) cd ..(뒤로가기), cd /폴더주소/폴더주소/(폴더 주소로 이동)
4. pwd : 현재경로확인
5. clear : 명령어 내용 삭제
6. mv : 파일이동
   > ex)mv ROOT.war /home/tomcat/(루트 파일을 tomcat으로 이동)
7. rm : 파일삭제
   > - ex) rm -rf _ (폴더파일 모두(_) 강제(-rf)삭제)
8. su - : 루트계정 접속
9. sudo passwd root : root 비밀번호 초기 설정

```
*** System restart required ***
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
```

포트 확인

## EC2 war설치(jar 형식으로 톰캣에다(분리하지 않는다)설치X

배포(deploy)
준비)이클립스,aws 로그인

Spring boot 는 내장톰캣.

스프링 배포 2가지
배포
1.war
2.jar (Spring boot만 가능 이유:내장톰캣이 있기 때문에)

war - w= web
jar - j = jar

스프링, 이고브 배포하는 방법 1가지

1. war로 압축

이번 포트 9090

gradle task -> bootjar

```
ubuntu@ip-172-31-42-132:~$ clear
ubuntu@ip-172-31-42-132:~$ su -
Password:
root@ip-172-31-42-132:~# ls
snap
root@ip-172-31-42-132:~# cd /home
root@ip-172-31-42-132:/home# ls
apache-tomcat-9.0.64.tar.gz  tomcat  ubuntu
root@ip-172-31-42-132:/home# cd ubuntu/
root@ip-172-31-42-132:/home/ubuntu# ls
root@ip-172-31-42-132:/home/ubuntu# pwd
/home/ubuntu

(war 파일을 ubuntu에 설치)

root@ip-172-31-42-132:/home/ubuntu# ls
hrk.jar
root@ip-172-31-42-132:/home/ubuntu# nohup java -jar hrk.war &        //실행
[1] 13203
root@ip-172-31-42-132:/home/ubuntu# nohup: ignoring input and appending output to 'nohup.out'
root@ip-172-31-42-132:/home/ubuntu# ls
hrk.jar  nohup.out
root@ip-172-31-42-132:/home/ubuntu# netstat -tnlp
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 127.0.0.53:53           0.0.0.0:*               LISTEN      661/systemd-resolve
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      975/sshd
tcp        0      0 127.0.0.1:6010          0.0.0.0:*               LISTEN      13081/sshd: ubuntu@
tcp6       0      0 :::9090                 :::*                    LISTEN      13203/java          //***확인!
tcp6       0      0 127.0.0.1:8005          :::*                    LISTEN      7271/java
tcp6       0      0 :::8080                 :::*                    LISTEN      7271/java
tcp6       0      0 :::22                   :::*                    LISTEN      975/sshd
tcp6       0      0 ::1:6010                :::*                    LISTEN      13081/sshd: ubuntu@
root@ip-172-31-42-132:/home/ubuntu#
```

## 실행이 안될경우!

```
root@ip-172-31-42-132:/home/ubuntu# netstat -tnlp
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 127.0.0.53:53           0.0.0.0:*               LISTEN      661/systemd-resolve
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      975/sshd
tcp        0      0 127.0.0.1:6010          0.0.0.0:*               LISTEN      13081/sshd: ubuntu@
tcp6       0      0 :::9090                 :::*                    LISTEN      13564/java
tcp6       0      0 127.0.0.1:8005          :::*                    LISTEN      7271/java
tcp6       0      0 :::8080                 :::*                    LISTEN      7271/java
tcp6       0      0 :::22                   :::*                    LISTEN      975/sshd
tcp6       0      0 ::1:6010                :::*                    LISTEN

9090 의 PID 번호를 없앤다

root@ip-172-31-42-132:/home/ubuntu# kill -9 13564 //9090포트 삭제!
root@ip-172-31-42-132:/home/ubuntu# rm -rf * //ubuntu안에 있는 모든 파일 삭제
root@ip-172-31-42-132:/home/ubuntu# ls // 폴더에 남아있나확인
후 재설치!
war파일 ubuntu에 설치 -> ubuntu에서 war 파일이 있을때 -> nohup java -jar hrk.war & 명령어로 설치 -> netstat -tnl로 확인! -> 실행되는지 확인

```

후실행

# 빈스톡

- 인프라에 대해 몰라도 AWS클라드에서 쉽게 배포하고 관리가가능
- 용량 밸런싱 조정 상태등을 자동으로 처리
- 별도의 서비스 사용료는 없고, 사용한 AWS 리소스만큼 비용이 과금됩니다.

* 인스턴스(EC2) 및 OS 설치
* 웹 애플리케이션 소프트웨어 구성
* 오토 스케일링 구성
* 로드 밸런서 구성
* 업데이트 배포 및 버전 관리
* 모니터링 관리 설정

구성요소

1. 애플리케이션
   > 이름
2. 환경티어
   > 환경 티어는 웹서버 환경, 작업자 환경을 선택.
3. 플랫폼
   > 플랫폼은 운영 체제(OS), 프로그래밍 언어 런타임, 웹 서버, 애플리케이션 서버 를선택
4. 소스 코드
   > jar or war 압축파일 선택
5. 환경구성
   환경은 애플리케이션 버전을 실행 중인 AWS 리소스 모음
