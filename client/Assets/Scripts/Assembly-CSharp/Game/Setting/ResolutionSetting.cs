using UnityEngine;

namespace Game.Setting
{
	public static class ResolutionSetting
	{
		private static BaseResolutionMetaData m_metaData;

		private static bool m_fullscreen;

		private static string m_curResolution;

		public static BaseResolutionMetaData meta
		{
			get
			{
				return null;
			}
		}

		public static bool fullscreen
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public static string curResolution
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		static ResolutionSetting()
		{
		}

		public static void Initialize()
		{
		}

		public static bool contain(int width, int height)
		{
			return false;
		}

		public static bool contain(Resolution resolution)
		{
			return false;
		}
	}
}
