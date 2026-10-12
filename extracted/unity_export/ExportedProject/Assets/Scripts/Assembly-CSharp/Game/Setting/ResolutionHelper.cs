using UnityEngine;

namespace Game.Setting
{
	public static class ResolutionHelper
	{
		public const string simpleResolutionFormat = "{0} x {1}";

		public static string ToSimpleString(int width, int height)
		{
			return null;
		}

		public static string ToSimpleString(this Resolution resolution)
		{
			return null;
		}

		public static bool TryParseResolution(string text, out int width, out int height)
		{
			width = default(int);
			height = default(int);
			return false;
		}
	}
}
