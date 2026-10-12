namespace SimpleDiskUtils
{
	public class DiskUtils
	{
		public static int CheckFreeSpace(bool isExternalStorage = true)
		{
			return 0;
		}

		public static int CheckTotalSpace(bool isExternalStorage = true)
		{
			return 0;
		}

		public static int CheckBusySpace(bool isExternalStorage = true)
		{
			return 0;
		}

		public static void DeleteFile(string filePath)
		{
		}

		public static void SaveFile(object obj, string filePath)
		{
		}

		public static void SaveFile(object obj, string dirPath, string fileName)
		{
		}

		public static T LoadFile<T>(string filePath)
		{
			return default(T);
		}

		public static void SaveTextFile(string str, string filePath)
		{
		}

		public static void SaveTextFile(string str, string dirPath, string fileName)
		{
		}

		public static string LoadTextFile<T>(string filePath)
		{
			return null;
		}

		public static byte[] ObjectToByteArray(object obj)
		{
			return null;
		}

		public static T ByteArrayToObject<T>(byte[] bytes)
		{
			return default(T);
		}
	}
}
