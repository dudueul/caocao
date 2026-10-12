using System.Xml;

public interface IEventXmlSerializable
{
	void ImportFromXml(XmlNode xmlNode);
}
