using System.Diagnostics;
using System.Text;
using UnityEngine;

public static class NGUIText
{
	public enum Alignment
	{
		Automatic = 0,
		Left = 1,
		Center = 2,
		Right = 3,
		Justified = 4
	}

	public enum SymbolStyle
	{
		None = 0,
		Normal = 1,
		Colored = 2
	}

	public class GlyphInfo
	{
		public Vector2 v0;

		public Vector2 v1;

		public Vector2 u0;

		public Vector2 u1;

		public Vector2 u2;

		public Vector2 u3;

		public float advance;

		public int channel;
	}

	public static UIFont bitmapFont;

	public static Font dynamicFont;

	public static GlyphInfo glyph;

	public static int fontSize;

	public static float fontScale;

	public static float pixelDensity;

	public static FontStyle fontStyle;

	public static Alignment alignment;

	public static Color tint;

	public static int rectWidth;

	public static int rectHeight;

	public static int regionWidth;

	public static int regionHeight;

	public static int maxLines;

	public static bool gradient;

	public static Color gradientBottom;

	public static Color gradientTop;

	public static bool encoding;

	public static float spacingX;

	public static float spacingY;

	public static bool premultiply;

	public static SymbolStyle symbolStyle;

	public static int finalSize;

	public static float finalSpacingX;

	public static float finalLineHeight;

	public static float baseline;

	public static bool useSymbols;

	private static Color mInvisible;

	private static BetterList<Color> mColors;

	private static float mAlpha;

	private static CharacterInfo mTempChar;

	private static BetterList<float> mSizes;

	private static Color32 s_c0;

	private static Color32 s_c1;

	private static float[] mBoldOffset;

	public static void Update()
	{
	}

	public static void Update(bool request)
	{
	}

	public static void Prepare(string text)
	{
	}

	public static BMSymbol GetSymbol(string text, int index, int textLength)
	{
		return null;
	}

	public static float GetGlyphWidth(int ch, int prev)
	{
		return 0f;
	}

	public static GlyphInfo GetGlyph(int ch, int prev)
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static float ParseAlpha(string text, int index)
	{
		return 0f;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static Color ParseColor(string text, int offset)
	{
		return default(Color);
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static Color ParseColor24(string text, int offset)
	{
		return default(Color);
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static Color ParseColor32(string text, int offset)
	{
		return default(Color);
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static string EncodeColor(Color c)
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static string EncodeColor(string text, Color c)
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static string EncodeAlpha(float a)
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static string EncodeColor24(Color c)
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static string EncodeColor32(Color c)
	{
		return null;
	}

	public static bool ParseSymbol(string text, ref int index)
	{
		return false;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static bool IsHex(char ch)
	{
		return false;
	}

	public static bool ParseSymbol(string text, ref int index, BetterList<Color> colors, bool premultiply, ref int sub, ref bool bold, ref bool italic, ref bool underline, ref bool strike, ref bool ignoreColor)
	{
		return false;
	}

	public static string StripSymbols(string text)
	{
		return null;
	}

	public static void Align(BetterList<Vector3> verts, int indexOffset, float printedWidth)
	{
	}

	public static int GetExactCharacterIndex(BetterList<Vector3> verts, BetterList<int> indices, Vector2 pos)
	{
		return 0;
	}

	public static int GetApproximateCharacterIndex(BetterList<Vector3> verts, BetterList<int> indices, Vector2 pos)
	{
		return 0;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	private static bool IsSpace(int ch)
	{
		return false;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public static void EndLine(ref StringBuilder s)
	{
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	private static void ReplaceSpaceWithNewline(ref StringBuilder s)
	{
	}

	public static Vector2 CalculatePrintedSize(string text)
	{
		return default(Vector2);
	}

	public static int CalculateOffsetToFit(string text)
	{
		return 0;
	}

	public static string GetEndOfLineThatFits(string text)
	{
		return null;
	}

	public static bool WrapText(string text, out string finalText)
	{
		finalText = null;
		return false;
	}

	public static bool WrapText(string text, out string finalText, bool keepCharCount)
	{
		finalText = null;
		return false;
	}

	public static void Print(string text, BetterList<Vector3> verts, BetterList<Vector2> uvs, BetterList<Color32> cols)
	{
	}

	public static void PrintApproximateCharacterPositions(string text, BetterList<Vector3> verts, BetterList<int> indices)
	{
	}

	public static void PrintExactCharacterPositions(string text, BetterList<Vector3> verts, BetterList<int> indices)
	{
	}

	public static void PrintCaretAndSelection(string text, int start, int end, BetterList<Vector3> caret, BetterList<Vector3> highlight)
	{
	}
}
