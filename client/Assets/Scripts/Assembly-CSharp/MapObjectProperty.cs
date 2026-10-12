using System.Collections.Generic;
using System.Xml;

public class MapObjectProperty
{
	private List<MapObjectCondition> m_changesProperty;

	private Dictionary<int, MapObjectStatus> m_statusProperty;

	private List<MapObjectStatus> m_statusPropertyList;

	public List<MapObjectCondition> changesProperty
	{
		get
		{
			return null;
		}
	}

	public Dictionary<int, MapObjectStatus> statusProperty
	{
		get
		{
			return null;
		}
	}

	public List<MapObjectStatus> statusPropertyList
	{
		get
		{
			return null;
		}
	}

	public void ImportProperty(XmlDocument xmlDoc)
	{
	}

	public void ImportChangesProperty(XmlDocument xmlDoc)
	{
	}

	public void ImportStatusProperty(XmlDocument xmlDoc)
	{
	}
}
