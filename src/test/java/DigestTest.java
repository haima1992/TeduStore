import org.springframework.util.DigestUtils;

public class DigestTest {
	public static void main(String[] args) {
		String[] passwords= {"123456","123450","abc"};
		for(int i=0;i<passwords.length;i++) {
			String md5Str = DigestUtils.md5DigestAsHex(passwords[i].getBytes());
			System.out.println(md5Str.toUpperCase());
		}
	}
}
