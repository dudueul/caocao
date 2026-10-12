namespace M
{
	public static class OpenInFileBrowser
	{
		public static bool IsInMacOS
		{
			get
			{
				return false;
			}
		}

		public static bool IsInWinOS
		{
			get
			{
				return false;
			}
		}

		public static void OpenInMac(string path)
		{
		}

		public static void OpenInWin(string path)
		{
		}

		public static void Open(string path)
		{
		}
	}
}
