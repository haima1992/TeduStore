import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestTest {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String[] passwords= {"123456","000000","abcdef"};
		MessageDigest md = MessageDigest.getInstance("MD5");
		for(int i=0;i<passwords.length;i++) {
			byte[] mdBytes = md.digest(passwords[i].getBytes());
			System.out.println("mdBytes:"+mdBytes);
		}
	}
}
