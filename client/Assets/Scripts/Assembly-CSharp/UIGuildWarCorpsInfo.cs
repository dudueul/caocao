using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class UIGuildWarCorpsInfo : CaocaoUIModalPopup
{
	private class SelectedCorpsInfo
	{
		public int face_unit_id;

		public int face_skinitem_id;

		public string nation_name;

		public int corps_military_power;

		public int corps_max_military_power;

		public int corps_job_id;

		public int corps_job_type_id;

		public int level;

		public int exp;

		public int max_level;

		public bool is_siege_weapon;

		public int siege_weapon_military_power;

		public int siege_weapon_max_military_power;

		public int siege_weapon_job_id;

		public ulong guild_id;

		public string guild_name;

		public int guild_emblem_sprite_index;

		public int guildwar_rank;

		public int atk;

		public int inte;

		public int def;

		public int agi;

		public int mor;

		public int move;

		public int currentActionPoint;

		public int maxActionPoint;

		public string corpsPrefab;

		public Color32[] corpsColor;

		public List<int> p_passive_list_ids;
	}

	[SerializeField]
	private CaocaoTabButtons tabButtons;

	[SerializeField]
	private GameObject BasicInfo;

	[SerializeField]
	private GameObject PassiveList;

	[SerializeField]
	private UILabel NationName;

	[SerializeField]
	private UILabel CorpsLevel;

	[SerializeField]
	private GameObject FaceTexture;

	[SerializeField]
	private UISlider ActionPointGauge;

	[SerializeField]
	private UILabel ActionPointText;

	[SerializeField]
	private UIButton BtnArtifice;

	[SerializeField]
	private GameObject CorpsPortrate;

	private MaterialPropertyBlock _matProp;

	[SerializeField]
	private UISlider CorpsAtkGauge;

	[SerializeField]
	private UILabel CorpsAtkText;

	[SerializeField]
	private UISlider CorpsInteGauge;

	[SerializeField]
	private UILabel CorpsInteText;

	[SerializeField]
	private UISlider CorpsDefGauge;

	[SerializeField]
	private UILabel CorpsDefText;

	[SerializeField]
	private UISlider CorpsAgiGauge;

	[SerializeField]
	private UILabel CorpsAgiText;

	[SerializeField]
	private UISlider CorpsMorGauge;

	[SerializeField]
	private UILabel CorpsMorText;

	[SerializeField]
	private UISlider CorpsMoveGauge;

	[SerializeField]
	private UILabel CorpsMoveText;

	[SerializeField]
	private GameObject CorpsAblilty;

	[SerializeField]
	private GameObject CorpsCreateInfo;

	[SerializeField]
	private GameObject CorpsDurability;

	[SerializeField]
	private UISlider CorpsDurabilityGauge;

	[SerializeField]
	private UILabel CorpsDurabilityText;

	[SerializeField]
	private UISlider CorpsMilliteryPowerGauge;

	[SerializeField]
	private UILabel CorpsMilliteryPowerText;

	[SerializeField]
	private UILabel CorpsGuildNameText;

	[SerializeField]
	private UISlider CorpsExpGauge;

	[SerializeField]
	private UILabel CorpsExpText;

	[SerializeField]
	private UILabel CorpsMaxLevelText;

	[SerializeField]
	private UILabelLocalized CorpsDescription;

	[SerializeField]
	private GameObject HitRangeTexture;

	[SerializeField]
	private GameObject EffectRangeTexture;

	[SerializeField]
	private UIButton BtnCreate;

	[SerializeField]
	private UIButton BtnPurchase;

	[SerializeField]
	private UIButton BtnClose;

	[SerializeField]
	private UIGrid CorpsTypeTable;

	[SerializeField]
	private UIScrollView CorpsTypeScrollView;

	[SerializeField]
	private UITexture GuildEmblem;

	private Dictionary<int, bool> CorpsJobTypeShowCondition;

	private Dictionary<int, List<GameObject>> CorpsJobTypeDic;

	private GameObject LastSelectedCorpsJobObj;

	private int SelectedJobId;

	private List<int> functionList;

	[SerializeField]
	private UIReuseGrid CorpsPassiveList;

	[SerializeField]
	private Transform Grade;

	private static UIGuildWarCorpsInfo _self;

	private GuildWarCorpsInfoState InfoState;

	private bool isFree;

	private int CreateCost;

	private SelectedCorpsInfo selectedCorps;

	private static void checkSelf()
	{
	}

	public static void ShowNewCreate(bool isFree)
	{
	}

	public static void ShowInfo(CorpsData CorpsData)
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void RefreshCorpsData(GuildWarCorpsJob jobData)
	{
	}

	private void ClearCorpsData()
	{
	}

	private void InitCorpsData(CorpsData corpsData)
	{
	}

	private void initData()
	{
	}

	public void OnBtnCreate()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private int GetMaxActionPoint(GuildWarCorpsJob jobData)
	{
		return 0;
	}

	private int getRandomFace(int faceUnitId = 0)
	{
		return 0;
	}

	private void UpdateFaceTexture()
	{
	}

	private int GetMaxMilitaryPower(GuildWarCorpsJob jobData)
	{
		return 0;
	}

	private string GetDetailPowerTitleText(CorpsData renderingData)
	{
		return null;
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
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
}
