using System.Xml;
using Caocao;
using UnityEngine;

public class MapEnvData : MonoBehaviour
{
	[SerializeField]
	[EnumFlags]
	public Caocao.Weather m_WeatherFlags;

	private void Start()
	{
	}

	private void OnWeatherChanged()
	{
	}

	public XmlElement ToXML(XmlDocument xmlDoc)
	{
		return null;
	}
}
