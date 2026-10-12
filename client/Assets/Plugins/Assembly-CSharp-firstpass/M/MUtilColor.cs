using System.Collections.Generic;
using UnityEngine;

namespace M
{
	public static class MUtilColor
	{
		public static MHsvColor ConvertRgbToHsv(Color color)
		{
			return default(MHsvColor);
		}

		public static MHsvColor ConvertRgbToHsv(double r, double b, double g)
		{
			return default(MHsvColor);
		}

		public static Color ToColor(this MHsvColor hsvColor)
		{
			return default(Color);
		}

		public static Color ConvertHsvToRgb(MHsvColor color)
		{
			return default(Color);
		}

		public static Color ConvertHsvToRgb(double h, double s, double v)
		{
			return default(Color);
		}

		public static List<Color> GenerateHsvSpectrum()
		{
			return null;
		}

		public static Texture2D GenerateHSVTexture(int width, int height)
		{
			return null;
		}

		public static Texture2D GenerateColorTexture(Color mainColor, Texture2D texture)
		{
			return null;
		}

		public static Texture2D GenerateColorTexture(int width, int height, Color mainColor)
		{
			return null;
		}
	}
}
