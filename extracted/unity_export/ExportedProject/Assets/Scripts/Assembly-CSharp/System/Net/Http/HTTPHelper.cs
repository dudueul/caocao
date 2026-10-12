using LitJson;

namespace System.Net.Http
{
	public class HTTPHelper
	{
		private static bool ms_logging;

		private const int N = 624;

		private const int M = 397;

		private const uint MATRIX_A = 2567483615u;

		private const uint UPPER_MASK = 2147483648u;

		private const uint LOWER_MASK = 2147483647u;

		private const int PRIME_A = 43;

		private const int PRIME_B = 61;

		private const int LCM = 2623;

		private uint[] mt;

		private short mti;

		private static uint[] mag01;

		private const string encryptedSalt = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		private static HTTPHelper ms_twister;

		private static string ms_uuid;

		private static uint ms_uuidHash;

		private static uint ms_seqNum;

		private const string ms_defaultUuid = "9876543210";

		private static uint ms_defaultSeqNum;

		private static readonly uint ms_defaultUuidHash;

		private static readonly string ms_defaultToken;

		private static readonly byte[] IV;

		private HTTPHelper()
		{
		}

		private void Reset(uint seed)
		{
		}

		private uint GenerateUInt()
		{
			return 0u;
		}

		private int Next(int max)
		{
			return 0;
		}

		private uint Next(uint max)
		{
			return 0u;
		}

		private static int GetHashCode(string strInt)
		{
			return 0;
		}

		private static string CreateToken(uint hashByUuid, uint seqNum)
		{
			return null;
		}

		public static void SetSession(string uuid)
		{
		}

		public static void ExpireSession()
		{
		}

		public static bool HasSession()
		{
			return false;
		}

		public static string CreatePacket(string packet, bool useMyKey)
		{
			return null;
		}

		public static JsonData RetrivePacket(string packet, bool tryMyKey)
		{
			return null;
		}

		private static string _RetrivePacket(string packet, string uuid, ref uint seqNum, bool tryMyKey)
		{
			return null;
		}

		private static uint TwistValue(uint num)
		{
			return 0u;
		}

		private static string Encrypt(string key, string src)
		{
			return null;
		}

		private static string Decrypt(string key, string src)
		{
			return null;
		}
	}
}
