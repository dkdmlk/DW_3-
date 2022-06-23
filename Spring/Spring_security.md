UserVO
~~~java
package com.ingeunjumin.project.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersVO {
	private int userNo;
	private String userId;
	private String userPassword;
	private String userName;
	private String phone;
	private String email;
	private String auth; //사용자가 가지고 있는 권한
	private String createAt;
}
~~~

AuthVO : 사용자권한VO
~~~java
package com.ingeunjumin.project.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

public class AuthVO implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private UsersVO usersVO;
	
	public AuthVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}
	
     /**
     * UserDetails 구현
     * 해당 유저의 권한목록 리턴
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(usersVO.getAuth()));
		return authorities;
	}
      /**
     * UserDetails 구현
     * 비밀번호를 리턴
     */
	@Override
	public String getPassword() {
		return usersVO.getUserPassword();
	}
     /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
	@Override
	public String getUsername() {
		return usersVO.getUserName();//이름
	}
    /**
     * UserDetails 구현
     * 권한을 반환해준다
     */
	public String getAuth() { 
		return usersVO.getAuth(); //권한
	}
	//권한에따른 이용페이지 제한 함수들
     /**
     * UserDetails 구현
     * 계정 만료 여부
     *  true : 만료안됨
     *  false : 만료됨
     */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
     /**
     * UserDetails 구현
     * 계정 잠김 여부
     *  true : 잠기지 않음
     *  false : 잠김
     */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
    /**
     * UserDetails 구현
     * 계정 비밀번호 만료 여부
     *  true : 만료 안됨
     *  false : 만료됨
     */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
    /**
     * UserDetails 구현
     * 계정 활성화 여부
     *  true : 활성화됨
     *  false : 활성화 안됨
     */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
~~~

UserMapper
~~~java
package com.ingeunjumin.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ingeunjumin.project.vo.UsersVO;

@Mapper
public interface UsersMapper {
	
	public int insertUsers(UsersVO usersVO);

	public UsersVO selectUserInfo(@Param("userId") String userId); //유저가 맞는지 id로 비교
}

~~~

AuthService : 권한서비스
~~~java
package com.ingeunjumin.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ingeunjumin.project.mapper.UsersMapper;
import com.ingeunjumin.project.vo.AuthVO;
import com.ingeunjumin.project.vo.UsersVO;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 

	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UsersVO userVO  = usersMapper.selectUserInfo(userId);
		
		if(userVO == null) throw new UsernameNotFoundException("User Not Found"); //저장된 유저정보가 없습니다!
         
		return new AuthVO(userVO);
	}

	public int setUser(UsersVO usersVO) { //암호화 하기전 비밀번호와 입력한 비밀번호가 맞는지 체크하는 함수
		String password = usersVO.getUserPassword();
		password = passwordEncoder.encode(password);
		usersVO.setUserPassword(password);
		return usersMapper.insertUsers(usersVO);
	}
}
~~~

AuthController : 권한 컨트롤러
~~~java
package com.ingeunjumin.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingeunjumin.project.service.AuthService;
import com.ingeunjumin.project.vo.UsersVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@GetMapping("/login")
	public String loadLoginPage() { 
		log.info("[ Call /login - GET ]");
		return "login";
	}
	
	@PostMapping("/join")
	public @ResponseBody int callJoin(@RequestBody UsersVO usersVO) {
		return authService.setUser(usersVO);
	}
}

~~~

HomeController : 메인컨트롤러
~~~java
package com.ingeunjumin.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ingeunjumin.project.vo.AuthVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/home")
	public String loadHomePage(ModelMap modelMap) {
		log.info("[ Call /home - GET ]");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AuthVO autoVO = (AuthVO) auth.getPrincipal();
	    String userAuth = autoVO.getAuth();
	    String userName = autoVO.getUsername();
	    modelMap.addAttribute("userAuth",userAuth);
	    modelMap.addAttribute("userName",userName);
		return "home";
	}
}
~~~

WebConfig
~~~java
package com.ingeunjumin.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebConfig {//비밀번호를 암호화 하는걸 Spring이 관리하는것으로 넘김
	@Bean
    public BCryptPasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    }
}

~~~

WebSecurityConfig : 웹보안 컨피그
~~~java
package com.ingeunjumin.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ingeunjumin.project.service.AuthService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
    private AuthService authService; //권한서비스
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; //암호화
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider(AuthService authService){//DB인증
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {//관리자 나누기
		auth.authenticationProvider(authenticationProvider(authService));
	}

	@Override
	public void configure(WebSecurity web) {//예외처리
		web.ignoring().antMatchers("/resources/static/*/**");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
		 http
		 .authorizeRequests().antMatchers("/resources/static/*/**","/login","/join").permitAll()//security 대상에서 제외
		 .anyRequest().authenticated();
		 http
		 .csrf().disable();//CSRF 기능 off
		 http
		 .formLogin().loginPage("/login")
		 .usernameParameter("userId")
		 .passwordParameter("userPassword")
		 .defaultSuccessUrl("/home"); //로그인 성공시 이동할 경로
		 http
		 .logout()
		 .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 경로
		 .deleteCookies("JSESSIONID") //쿠키 제거
         .invalidateHttpSession(true) //로그아웃시 세션 제거
         .clearAuthentication(true) //권한정보 제거
         .permitAll();
		 http
		 .exceptionHandling()
		 .accessDeniedPage("/access-denied"); //권한없는유저가 요청시 접속할 경로
	}
	
}

~~~