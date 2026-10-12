using System.Collections.Generic;
using System.Text;
using System.Xml;

public class MapObjectPropertyPool : SceneSingleton<MapObjectPropertyPool>
{
	private static StringBuilder m_stringBuilder;

	private Dictionary<string, MapObjectProperty> m_objectProperties;

	public MapObjectProperty GetObjectProperties(string propertyType)
	{
		return null;
	}

	private XmlDocument GetXMLDocument(string propertyType)
	{
		return null;
	}
}
