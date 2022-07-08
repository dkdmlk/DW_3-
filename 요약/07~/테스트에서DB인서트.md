```java
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.data.batch.mapper.BatchMapper;
import com.data.batch.mapper.OpenDataMapper;
import com.data.batch.utils.AddressHandler;
import com.data.batch.vo.ApartmentsVO;
import com.data.batch.vo.OpenDataVO;

@SpringBootTest
//@Transactional(rollbackFor = Exception.class)
public class insertTest {

	@Autowired
	private BatchMapper batchmapper; //인서트문 mapper
	@Autowired
	private OpenDataMapper openDataMapper;//DateList mapper
	@Autowired
	private AddressHandler addressHandler;


	@Test
	void contextLoads() throws Exception{
		List<OpenDataVO> list = openDataMapper.getGroupApart(); //List를 불러옴
		ApartmentsVO vo = new ApartmentsVO(); // Date를 담을 table VO를 생성

		for(OpenDataVO i: list) {
			String ApartmentName = i.getApartmentName();
			vo.setApartmentsName(ApartmentName);//아파트이름
			String price = i.getDealAmountAge();
			vo.setPrice(price);//가격
			String addr = i.getAddr();
			vo.setAddr(addr);//주소
			String gu = i.getGu();
			vo.setGu(gu);//구
			String dong = i.getDong();
			vo.setDong(dong);//동 을 넣어준다.
			try {
				String result = addressHandler.convertAddrToGPS(addr);//도로명을 위도 경도로 변화
				String[] arr = result.split("/");
				String Latitude =arr[0];
				vo.setLatitude(Latitude);
				String Longitude =arr[1]; //vo set을 해서 insert
				vo.setLongitude(Longitude);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			String BuildYear = i.getBuildYear();
			SimpleDateFormat Format = new SimpleDateFormat("yyyy");
			Date date = Format.parse(BuildYear);//날짜변환
			String createAt = Format.format(date);
			vo.setCreateAt(date);//vo에 날짜데이터를 넣는다.
			batchmapper.insertApartments(vo);//데이터를 넣은 vo을 insert
		}
	}
}

```
