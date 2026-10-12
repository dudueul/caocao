using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class UIGuildWarBottomDetailInfo : MonoBehaviour
{
	[SerializeField]
	private UnitUI _unitUI;

	[SerializeField]
	private UITexture _color;

	[SerializeField]
	private UITexture _passiveBgColor;

	[SerializeField]
	private UILabel _name;

	[SerializeField]
	private UILabel _state2Desc1;

	[SerializeField]
	private Transform _state2Desc2;

	[SerializeField]
	private UILabelLocalized _hpTitleText;

	[SerializeField]
	private UISlider _corpsHP;

	[SerializeField]
	private UISprite _corpsInjuryBar;

	[SerializeField]
	private UISprite _corpsDurabilityBar;

	[SerializeField]
	private UILabel _corpsHPValue;

	[SerializeField]
	private UILabel _state4Desc1;

	[SerializeField]
	private Transform _state4Desc2;

	[SerializeField]
	private UILabelLocalized _tileType;

	[SerializeField]
	private UILabel _tileFloor;

	[SerializeField]
	private UIGrid _passiveListGrid;

	private EBattleFieldObjectType targetObjectType;

	private int _gridPosX;

	private int _gridPosY;

	private bool m_gameObjectPoolCreated;

	private int m_passiveIconCount;

	private List<int> prevPassiveIds;

	private void Start()
	{
	}

	private void CreateGameObjectPool()
	{
	}

	public void UpdateCorps()
	{
	}

	public void UpdateBuild()
	{
	}

	public void UpdateFixingUI()
	{
	}

	private void UpdatePassives()
	{
	}

	private string getHPTitleText(bool isHP)
	{
		return null;
	}

	public void UpdateChangeUI()
	{
	}

	private string GetTileHeightString(int height)
	{
		return null;
	}

	private void Update()
	{
	}

	public void OnClickConditionGrid()
	{
	}

	public void OnClickProfile()
	{
	}

	public void OnClickUnitFace(UnitUI unitUI)
	{
	}
}
