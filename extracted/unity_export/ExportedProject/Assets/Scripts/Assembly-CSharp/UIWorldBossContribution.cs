using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;
using Worldboss;

public class UIWorldBossContribution : MonoBehaviour, IWorldBossTab, PopupLevelPlusMethod.IParentLevelPlusMethod
{
	public class ContributeItemData : PopupLevelPlusMethod.ItemData
	{
		public int cost;

		public string itemName;
	}

	private class itemDataComparer : IComparer<PopupLevelPlusMethod.ItemData>
	{
		public int Compare(PopupLevelPlusMethod.ItemData a, PopupLevelPlusMethod.ItemData b)
		{
			return 0;
		}
	}

	public UITexture bossImage;

	public UISlider gaugeSlider;

	public UILabelLocalized gaugeValue;

	public UIButton btnGive;

	public UILabelLocalized giveValue;

	public UIButton btnOpen;

	public UILabelLocalized timer;

	public GameObject stateObj;

	public UILabelLocalized stateDesc;

	public PlayMakerFSM fsm;

	public UILabelLocalized ticketCountLabel;

	private ContributeItemData selectItem;

	private PanelPopup3Buttons popup;

	private bool updatingGauge;

	private bool activeContribution;

	public string methodTitle
	{
		get
		{
			return null;
		}
	}

	public PopupWorldboss parent { get; private set; }

	Transform PopupLevelPlusMethod.IParentLevelPlusMethod.transform
	{
		get
		{
			return IParentLevelPlusMethod_002Eget_transform();
		}
	}

	public void Initialize(PopupWorldboss parent)
	{
	}

	public void SetActive(bool active)
	{
	}

	public void Start()
	{
	}

	public void OnEnable()
	{
	}

	public void OnDisable()
	{
	}

	public void OnDestroy()
	{
	}

	private void UpdateTimer()
	{
	}

	private void WaitSeasonStart()
	{
	}

	private void WaitSeasonOff(DateTime seasonOffDate, DateTime curTime)
	{
	}

	private void WaitContributionCoolTime(DateTime contributionStartDate, DateTime curTime)
	{
	}

	private void WaitForContributionComplete(DateTime contributionCompleteDate, DateTime curTime)
	{
	}

	private void WaitForInstanceCoolTime(DateTime instanceStartDate, DateTime curTime)
	{
	}

	private void WaitForRelease()
	{
	}

	private void ActiveMirror(bool active)
	{
	}

	private void SetMirrorText(string text, params object[] args)
	{
	}

	private void SetGaugeText(string text, params object[] args)
	{
	}

	public void UpdateUI()
	{
	}

	private void OnClickRelease()
	{
	}

	private void UpdateGaugeBar(float from, float to, float time = 1f)
	{
	}

	protected void UpdateProgrssBar(float time = 0f)
	{
	}

	protected void OnUpdatedGaugeBar(float val)
	{
	}

	protected void OnUpdatingGaugeBar(float val)
	{
	}

	private bool _updateGaugeBar(float val)
	{
		return false;
	}

	protected void CreateContributionPopup()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitContributionEfffect(JsonData rewardData)
	{
		return null;
	}

	public List<PopupLevelPlusMethod.ItemData> GetItemDatas(out PopupLevelPlusMethod.ItemData selected)
	{
		selected = null;
		return null;
	}

	public void SetItemData(PopupLevelPlusMethod.ItemData itemData)
	{
	}

	private Transform IParentLevelPlusMethod_002Eget_transform()
	{
		return null;
	}
}
