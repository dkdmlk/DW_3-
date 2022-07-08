1. recaptcha key 생성

2. gradle 에 Json 을가져온다

3. yaml파일에 등록

```yaml
google:
  recaptcha:
    key:
      site: 발급받은 key
      secret: 발급받은 secretkey
      url: https://www.google.com/recaptcha/api/siteverify
```

4. 템플릿엔진(JSP)에 등록

```jsp
<div
    id="captcha"
    class="g-recaptcha"
    data-sitekey="${getSite}"
    data-callback="recaptchaCallback"
></div>
```

5. 자바스크립트 등록

```js
var isRecaptchachecked = false;

function recaptchaCallback() {
  // 리캡챠 체크 박스 클릭시 isRecaptchachecked 값이 true로 변경

  isRecaptchachecked = true;
}
```

6. VO등록

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
@Getter
@Setter
public class CaptchaSettings {
	 private String site;
	 private String secret;
	 private String url;
}
```

7. recaptcha ajax

```js
function doVaildRecaptcha() {
  var formData = $("#loginForm").serialize();
  $.ajax({
    type: "POST",
    contentType: "application/x-www-form-urlencoded",
    url: "/valid-recaptcha",
    data: formData,
    dataType: "text",
    cache: false,
    success: function (response) {
      if (response) {
        $("#loginForm").submit(); //시큐리티에 전송
      } else {
        alert("인증되지 않은 주소입니다.");
      }
    },
    error: function (xhr, status, error) {
      console.log(error);
    },
  });
}
```

8. service

```java
public boolean verifyRecaptcha(String recaptcha) {

    	final String SECRET_KEY = captchaSettings.getSecret(); // 비밀키 호출
    	final String RE_URL = captchaSettings.getUrl(); // 인증할 URL

		try {
			URL obj = new URL(RE_URL);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");

			String postParams = "secret=" + SECRET_KEY + "&response=" + recaptcha;
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			return jsonObject.getBoolean("success"); //최종 Return 값 : true or false

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
```

9. Controller

```java
@PostMapping("/valid-recaptcha")
    public @ResponseBody String validRecaptcha(HttpServletRequest request){
    	String result = null;
    	String response = request.getParameter("g-recaptcha-response");
    	boolean isRecaptcha = homeService.verifyRecaptcha(response); //인증 메소드 서비스로 분리

    	if(isRecaptcha) {
    		result = "success";
    	}else {
    		result = "false";
    	}
    	return result;
   }
```
