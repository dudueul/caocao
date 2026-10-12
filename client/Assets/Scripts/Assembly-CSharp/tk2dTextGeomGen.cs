using UnityEngine;

public static class tk2dTextGeomGen
{
	public class GeomData
	{
		internal tk2dTextMeshData textMeshData;

		internal tk2dFontData fontInst;

		internal string formattedText;
	}

	private static GeomData tmpData;

	private static readonly Color32[] channelSelectColors;

	private static Color32 meshTopColor;

	private static Color32 meshBottomColor;

	private static float meshGradientTexU;

	private static int curGradientCount;

	private static Color32 errorColor;

	public static GeomData Data(tk2dTextMeshData textMeshData, tk2dFontData fontData, string formattedText)
	{
		return null;
	}

	public static Vector2 GetMeshDimensionsForString(string str, GeomData geomData)
	{
		return default(Vector2);
	}

	public static float GetYAnchorForHeight(float textHeight, GeomData geomData)
	{
		return 0f;
	}

	public static float GetXAnchorForWidth(float lineWidth, GeomData geomData)
	{
		return 0f;
	}

	private static void PostAlignTextData(Vector3[] pos, int offset, int targetStart, int targetEnd, float offsetX)
	{
	}

	private static int GetFullHexColorComponent(int c1, int c2)
	{
		return 0;
	}

	private static int GetCompactHexColorComponent(int c)
	{
		return 0;
	}

	private static int GetStyleHexColor(string str, bool fullHex, ref Color32 color)
	{
		return 0;
	}

	private static int SetColorsFromStyleCommand(string args, bool twoColors, bool fullHex)
	{
		return 0;
	}

	private static void SetGradientTexUFromStyleCommand(int arg)
	{
	}

	private static int HandleStyleCommand(string cmd)
	{
		return 0;
	}

	public static void GetTextMeshGeomDesc(out int numVertices, out int numIndices, GeomData geomData)
	{
		numVertices = default(int);
		numIndices = default(int);
	}

	public static int SetTextMeshGeom(Vector3[] pos, Vector2[] uv, Vector2[] uv2, Color32[] color, int offset, GeomData geomData)
	{
		return 0;
	}

	public static void SetTextMeshIndices(int[] indices, int offset, int vStart, GeomData geomData, int target)
	{
	}
}
