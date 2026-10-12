using System.Text.RegularExpressions;

public class Keyword
{
	public string Value { get; private set; }

	public Keyword(string value)
	{
	}

	public static explicit operator Keyword(string s)
	{
		return null;
	}

	public static bool operator ==(string k1, Keyword k2)
	{
		return false;
	}

	public static bool operator !=(string k1, Keyword k2)
	{
		return false;
	}

	public static bool operator ==(Keyword k1, string k2)
	{
		return false;
	}

	public static bool operator !=(Keyword k1, string k2)
	{
		return false;
	}

	public bool IsMatch(string source, RegexOptions options = RegexOptions.IgnoreCase)
	{
		return false;
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}
}
