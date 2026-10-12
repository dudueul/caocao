public class SafePlayerPrefs
{
	private static string _saltForKey;

	private static byte[] _keys;

	private static byte[] _iv;

	private static int keySize;

	private static int blockSize;

	private static int _hashLen;

	public static void InitSafePlayerPrefs(string addKey)
	{
	}

	public static string MakeHash(string original)
	{
		return null;
	}

	public static byte[] Encrypt(byte[] bytesToBeEncrypted)
	{
		return null;
	}

	public static byte[] Decrypt(byte[] bytesToBeDecrypted)
	{
		return null;
	}

	public static string Encrypt(string input)
	{
		return null;
	}

	public static string Decrypt(string input)
	{
		return null;
	}

	private static void SetSecurityValue(string key, string value)
	{
	}

	private static string GetSecurityValue(string key)
	{
		return null;
	}

	public static void DeleteKey(string key)
	{
	}

	public static void DeleteAll()
	{
	}

	public static void Save()
	{
	}

	public static void SetInt(string key, int value)
	{
	}

	public static void SetLong(string key, long value)
	{
	}

	public static void SetFloat(string key, float value)
	{
	}

	public static void SetString(string key, string value)
	{
	}

	public static int GetInt(string key, int defaultValue)
	{
		return 0;
	}

	public static long GetLong(string key, long defaultValue)
	{
		return 0L;
	}

	public static float GetFloat(string key, float defaultValue)
	{
		return 0f;
	}

	public static string GetString(string key, string defaultValue)
	{
		return null;
	}
}
