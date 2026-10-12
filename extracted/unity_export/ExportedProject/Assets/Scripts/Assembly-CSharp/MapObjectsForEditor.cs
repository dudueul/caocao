using System.Collections.Generic;

public class MapObjectsForEditor
{
	private List<int> m_editorIDs;

	private List<string> m_editorNames;

	private List<string> m_editorMapObjects;

	public List<int> editorIDs
	{
		get
		{
			return null;
		}
	}

	public List<string> editorNames
	{
		get
		{
			return null;
		}
	}

	public List<string> editorMapObjects
	{
		get
		{
			return null;
		}
	}

	public void Load()
	{
	}

	public int FindEditorMapObjectName(string NameText)
	{
		return 0;
	}

	public void GetSearchedEditorObjects(string nameText, ref List<string> selects, ref List<int> ids)
	{
	}

	public string GetUnitName(int objId)
	{
		return null;
	}
}
