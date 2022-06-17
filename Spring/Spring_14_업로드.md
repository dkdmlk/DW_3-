# 업로드 == (베포 or deploy)

## 언어마다 배포하는 방법이 다름
+ 배포 툴 : gralde, maven
>>gradle, maven은 java만 있음. (Spring, Android)

우리가 흔히 사용하는 폴더 압축은
+ .zip

jaba는 .zip이 아님.
1. .war : 바로 실행 가능한 어플리케이션(프로그램) X
2. .jar : 바로 실행 가능한 어플리케이션(프로그램)

### Spring boot는 jar, war 둘다가능 (내장톰캣이 있기 때문에)

빌드 - > 컴파일(커뮤터가 알아들수 있게) -> 실행
>+ 빌드 : 설치한 걸 한번에 모아줌(컴파일 준비)

![war만들기.PNG](war%EB%A7%8C%EB%93%A4%EA%B8%B0.PNG)

![war파일위치.PNG](war%ED%8C%8C%EC%9D%BC%EC%9C%84%EC%B9%98.PNG)


board.War 파일이름 -> ROOT.War -> AWS클라우드 컴퓨터에 파일 옮기기후

~~~
*** System restart required ***
Last login: Wed Jun 15 02:28:55 2022 from 115.94.162.198
ubuntu@ip-172-31-42-132:~$ clear
ubuntu@ip-172-31-42-132:~$ pwd                                                  //ROOT.War파일위치
/home/ubuntu
ubuntu@ip-172-31-42-132:~$ ls
ROOT.war
ubuntu@ip-172-31-42-132:~$ su -                                                  //루트계정으로 접속
Password: 
root@ip-172-31-42-132:~# pwd
/root
root@ip-172-31-42-132:~# cd /home/ubuntu/
root@ip-172-31-42-132:/home/ubuntu# ls
ROOT.war
root@ip-172-31-42-132:/home/ubuntu# cd ..
root@ip-172-31-42-132:/home# ls                                                    //폴더확인
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
root@ip-172-31-42-132:/home/tomcat/webapps# rm -rf *                            //webapp파일 모두 지우기
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
root@ip-172-31-42-132:/home/ubuntu# mv ROOT.war /home/tomcat/webapps/            //ROOT.war파일 webapp으로옮기기
root@ip-172-31-42-132:/home/ubuntu# ls
root@ip-172-31-42-132:/home/ubuntu# cd /home/tomcat/webapps/
root@ip-172-31-42-132:/home/tomcat/webapps# ls                                   //잘옮겨졋는지확인
ROOT  ROOT.war
~~~

## 배포?
EC2->
0. 운영체제?
1. 리눅스 명령어
2. 배포경로?
3. 프로그램?

- aws-빈즈톡 : 쉬운배포(마우스)
- aws-람다 : 코드에집중(배포 불필요) 