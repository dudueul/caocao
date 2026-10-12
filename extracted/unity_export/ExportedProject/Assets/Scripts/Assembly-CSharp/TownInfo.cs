using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class TownInfo : MonoBehaviour
{
	private static TownInfo self;

	[SerializeField]
	private UILabelLocalized m_hpLabel;

	[SerializeField]
	private UILabelLocalized m_farmLabel;

	[SerializeField]
	private UILabelLocalized m_marketLabel;

	[SerializeField]
	private UILabelLocalized m_safeLabel;

	[SerializeField]
	private UILabelLocalized m_hp;

	[SerializeField]
	private UILabelLocalized m_size;

	[SerializeField]
	private UILabelLocalized m_farm;

	[SerializeField]
	private UILabelLocalized m_market;

	[SerializeField]
	private UILabelLocalized m_safe;

	[SerializeField]
	private GameObject m_popularityObj;

	[SerializeField]
	private UILabelLocalized m_popularityLabel;

	[SerializeField]
	private PrefabInstance m_foodUI;

	[SerializeField]
	private PrefabInstance m_silverUI;

	private UserTown m_town;

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnDisable()
	{
	}

	private void AddTimer()
	{
	}

	public static void Refresh()
	{
	}

	public void UpdateUI()
	{
	}

	private void SetBuildingInfo(UserTown userTown, UILabelLocalized label, UILabelLocalized desc, EnumData.TownBuildingType buildingType)
	{
	}

	private void UpdateHarvestAmount()
	{
	}

	public void HarvestTown()
	{
	}

	private void OnHarvestTown(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowHarvestEffect(int townId, int addFood, int addSilver)
	{
		return null;
	}

	public void HarvestAllTowns()
	{
	}

	[DebuggerHidden]
	public IEnumerator SendHarvestAll()
	{
		return null;
	}
}
