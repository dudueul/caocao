using System.Collections.Generic;

public class FilterManager
{
	private class SlangNode
	{
		public bool isSlang;

		public readonly Dictionary<char, SlangNode> children;

		public bool needContainsMatch;
	}

	private SlangNode m_nameFilterRoot;

	private SlangNode m_chatFilterRoot;

	public static FilterManager instance
	{
		get
		{
			return null;
		}
	}

	public void LoadFilters()
	{
	}

	private SlangNode BuildFilterNodes(string[] filters, bool checkWildcard)
	{
		return null;
	}

	private bool ContainSlang(string str, SlangNode filterRoot)
	{
		return false;
	}

	public bool IsFilteredName(string str)
	{
		return false;
	}

	public bool IsFilteredChat(string str)
	{
		return false;
	}
}
