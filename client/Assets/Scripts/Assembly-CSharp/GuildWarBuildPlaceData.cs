using System;
using System.Xml;
using UnityEngine;

public class GuildWarBuildPlaceData : MonoBehaviour, IComparable
{
	public IntVector2 GridPos;

	public int Id;

	public Action<GuildWarBuildPlaceData> clickCallBack { get; set; }

	private void OnMouseUpAsButton()
	{
	}

	int IComparable.CompareTo(object b)
	{
		return 0;
	}

	public XmlElement ToXML(XmlDocument xmlDoc)
	{
		return null;
	}
}
