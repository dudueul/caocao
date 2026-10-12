using System.Collections.Generic;
using UnityEngine;

public class ByteReader
{
	private byte[] mBuffer;

	private int mOffset;

	private static BetterList<string> mTemp;

	public bool canRead
	{
		get
		{
			return false;
		}
	}

	public ByteReader(byte[] bytes)
	{
	}

	public ByteReader(TextAsset asset)
	{
	}

	public static ByteReader Open(string path)
	{
		return null;
	}

	private static string ReadLine(byte[] buffer, int start, int count)
	{
		return null;
	}

	public string ReadLine()
	{
		return null;
	}

	public string ReadLine(bool skipEmptyLines)
	{
		return null;
	}

	public Dictionary<string, string> ReadDictionary()
	{
		return null;
	}

	public BetterList<string> ReadCSV()
	{
		return null;
	}
}
