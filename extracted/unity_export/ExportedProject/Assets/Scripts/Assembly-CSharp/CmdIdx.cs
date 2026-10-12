using System;

[Serializable]
public class CmdIdx
{
	public string key;

	public int idx;

	public CmdIdx()
	{
	}

	public CmdIdx(string key, int idx)
	{
	}

	public CmdIdx(CmdIdx src)
	{
	}

	public bool IsValid()
	{
		return false;
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public static bool operator ==(CmdIdx a, CmdIdx b)
	{
		return false;
	}

	public static bool operator !=(CmdIdx a, CmdIdx b)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public override string ToString()
	{
		return null;
	}
}
