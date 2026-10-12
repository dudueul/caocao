using UnityEngine;

public class BattleUnitInfoListItem : UIReuseScrollViewCell
{
	public class UnitCellData : IReuseCellData
	{
		public int Index { get; set; }

		public int width { get; set; }

		public int height { get; set; }

		public UserUnit userUnit { get; set; }

		public bool isNonAggressionUnit { get; set; }

		public bool isDimActive { get; set; }

		public EnumData.Brawl_Unit_State brawlUnitState { get; set; }
	}

	private UnitCellData m_unitCellData;

	private UserUnit m_userUnit;

	[SerializeField]
	private GameObject m_redBG;

	[SerializeField]
	private GameObject m_blueBG;

	[SerializeField]
	private UITexture m_Face;

	[SerializeField]
	private GameObject m_DimFace;

	[SerializeField]
	private UILabelLocalized m_uiLabelUnitName;

	[SerializeField]
	private Transform[] m_status;

	[SerializeField]
	private PrefabInstance[] m_treasureUI;

	[SerializeField]
	private UIGrid m_gridPassive;

	[SerializeField]
	private PrefabInstance[] m_passiveUI;

	[SerializeField]
	private PrefabInstance m_runePassiveUI;

	[SerializeField]
	private Transform m_dim;

	[SerializeField]
	private Transform[] m_position;

	public UnitCellData unitCellData
	{
		get
		{
			return null;
		}
	}

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UnitItemSetting(bool isNonAggressionUnit)
	{
	}

	private void UnitDetailSetting()
	{
	}

	private void ArenaUnitDetailSetting()
	{
	}

	private void GaugeSetting(Transform gauge, bool active, UserUnit unit, EnumData.UnitResType res)
	{
	}

	private void DimSetting(bool isActive)
	{
	}

	private void PositionSetting(EnumData.Brawl_Unit_State brawlUnitState)
	{
	}

	public void UpdateUnitGrid(bool isNonAggressionUnit, bool isDimActive, EnumData.Brawl_Unit_State brawlUnitState)
	{
	}
}
