using GuildWar;
using UnityEngine;

public class UIGuildWarBuildingInfo : CaocaoUIModalPopup
{
	[SerializeField]
	private CaocaoTabButtons tabButtons;

	[SerializeField]
	private GameObject BasicInfo;

	[SerializeField]
	private GameObject PassiveInfo;

	[SerializeField]
	private GameObject FacilityInfo;

	[SerializeField]
	private UILabelLocalized BuildName;

	[SerializeField]
	private UITexture BuildTexture;

	[SerializeField]
	private UILabelLocalized BuildType;

	[SerializeField]
	private GameObject BuildSprite;

	[SerializeField]
	private UISlider BuildHPGage;

	[SerializeField]
	private UILabel BuildHPGageText;

	[SerializeField]
	private UILabel GuildName;

	[SerializeField]
	private UILabelLocalized BuildDesc;

	[SerializeField]
	private GameObject BuildHitRange;

	[SerializeField]
	private GameObject BuildEffectRange;

	[SerializeField]
	private UITexture GuildEmblem;

	[SerializeField]
	private UISlider BuildingAtkGauge;

	[SerializeField]
	private UILabel BuildingAtkText;

	[SerializeField]
	private UISlider BuildingInteGauge;

	[SerializeField]
	private UILabel BuildingInteText;

	[SerializeField]
	private UISlider BuildingDefGauge;

	[SerializeField]
	private UILabel BuildingDefText;

	[SerializeField]
	private UISlider BuildingAgiGauge;

	[SerializeField]
	private UILabel BuildingAgiText;

	[SerializeField]
	private UISlider BuildingMorGauge;

	[SerializeField]
	private UILabel BuildingMorText;

	[SerializeField]
	private UIReuseGrid BuildPassiveList;

	private BuildingData buildData;

	private GuildWarBuilding p_buildingData;

	private GuildWarPlace p_placeData;

	private static UIGuildWarBuildingInfo _self;

	private static void checkSelf()
	{
	}

	public static void ShowInfo(BuildingData renderingData)
	{
	}

	private void Init(BuildingData renderingData)
	{
	}

	private void RefreshPassiveList()
	{
	}

	private void RefreshBuildSprite()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}
}
