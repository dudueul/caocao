using System;
using System.Xml;

[Serializable]
public sealed class NumericAttribution : IEventXmlSerializable
{
	public EnumData.Attribution nAttributiton;

	public EnumData.CompareSign nCalSignIndex;

	public int nParam;

	public bool usePercent;

	public void ImportFromXml(XmlNode xmlNode)
	{
	}

	public override string ToString()
	{
		return null;
	}
}
