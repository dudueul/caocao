public class GLinkNaverIdiOS : IGLinkNaverId
{
	private delegate void NaverIdLoginDelelgate();

	private delegate void NaverIdGetProfileDelegate(string result);

	public static void _NaverIdLoginCallback()
	{
	}

	public static void _NaverIdGetProfileCallback(string result)
	{
	}

	public void init(string clientId, string clientSecret)
	{
	}

	public void login()
	{
	}

	public void logout()
	{
	}

	public bool isLogin()
	{
		return false;
	}

	public void getProfile()
	{
	}
}
