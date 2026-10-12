using System.Collections.Generic;
using GuildWar;
using UnityEngine;
using UnityEngine.UI;

public class UIGuildWarCorpsBattleInformation : MonoBehaviour
{
	[SerializeField]
	private Canvas _canvas;

	[SerializeField]
	private Text _desc;

	[SerializeField]
	private Text _name;

	[SerializeField]
	private Slider _hpBar;

	[SerializeField]
	private Image _corpsInfoHpColor;

	[SerializeField]
	private Text _hpText;

	[SerializeField]
	private Slider _durabilityBar;

	[SerializeField]
	private Image _corpsInfoDurabilityColor;

	[SerializeField]
	private Text _durabilityText;

	[SerializeField]
	private GameObject _disturb;

	[SerializeField]
	private GameObject _confuse;

	[SerializeField]
	private GameObject _buff;

	[SerializeField]
	private GameObject _debuff;

	[SerializeField]
	private GameObject _moveLock;

	[SerializeField]
	private GameObject _defense;

	private Camera worldCam;

	private Camera uiCam;

	private List<GameObject> passiveMarkerTypeObjects;

	private Slider _currentBar;

	private Text _currentText;

	private int _currentTextValue;

	public void Init(string nationName, Color32 corpsColor)
	{
	}

	public void UpdateAnchor(Transform corpsTransform, bool isInSight, bool isDurability)
	{
	}

	public void InformationSetActive(bool value)
	{
	}

	public void PassiveMarkerSetActive(List<PassiveMarkerType> markerTypes)
	{
	}

	public void SetName(string nationName)
	{
	}

	public void SetColor(Color32 corpsColor)
	{
	}

	public void SetGauge(int militaryPower, int maxMilitaryPower, long repair_start_date = 0L, long repair_end_date = 0L)
	{
	}
}
