using System;
using LitJson;
using UnityEngine;

public class PanelUnitRune : SelfRegister2UIComponentsMonobehaviour, IDisposable
{
	private UserUnit m_userUnit;

	public Transform act;

	public RuneSlotUI[] runeSlotUIs;

	public bool deactiveNoneRune;

	public UILabelLocalized statAtk;

	public UILabelLocalized statDef;

	public UILabelLocalized statInte;

	public UILabelLocalized statAgi;

	public UILabelLocalized statMor;

	public UILabelLocalized statHp;

	public UILabelLocalized statMp;

	public UIButton[] btnSlots;

	public UILabelLocalized passiveName;

	public PrefabInstance passiveUIPrefabInstance;

	public UILabelLocalized runePassiveDesc;

	public UIButton[] btnSelector;

	private Action m_updateUnitHandler;

	private Action m_runePassiveListHandler;

	private Action m_postAction;

	private static string statStringFormat;

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

	private PassiveUI setPassiveUI
	{
		get
		{
			return null;
		}
	}

	public void Dispose()
	{
	}

	protected void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void SetRuneSlotChangeHandler(int runeSlotIndex)
	{
	}

	private void SetRunePassiveListHandler(int runeSlotIndex)
	{
	}

	private void OnRuneSlotChange(JsonData response)
	{
	}

	public void ShowPanel(bool show, UserUnit userUnit, int runeSlotIndex, Action callback)
	{
	}

	public void ShowPanel(bool show, UserUnit userUnit, Action callback = null)
	{
	}

	public void ClosePanel()
	{
	}

	private void UpdateUI(int runeSlotIndex)
	{
	}

	private void UpdateTotalStat(int runeSlotIndex)
	{
	}

	private void UpdateRuneUI(int runeSlotIndex)
	{
	}

	private void InitRuneSlotButton(int runeSlotIndex)
	{
	}

	private void Initialize()
	{
	}

	private void UpdateRuneSetUI(int runeSlotIndex)
	{
	}

	private void ToogleSlot(int runeSlotIndex)
	{
	}

	public void OnClickRunePassiveListBtn()
	{
	}
}
