using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x4D : EventCmdBase
{
	[Flags]
	public enum UnitStat
	{
		NONMOVE = 2,
		NOART = 4,
		NONMOVE_NOART = NONMOVE | NOART,
		CONFUSE = 8,
		NONMOVE_CONFUSE = NONMOVE | CONFUSE,
		NOART_CONFUSE = NOART | CONFUSE,
		NONMOVE_NOART_CONFUSE = NONMOVE_NOART | CONFUSE,
		POISON = 0x10,
		NONMOVE_POISON = NONMOVE | POISON,
		NOART_POISON = NOART | POISON,
		NONMOVE_NOART_POISON = NONMOVE_NOART | POISON,
		CONFUSE_POISON = CONFUSE | POISON,
		NONMOVE_CONFUSE_POISON = NONMOVE_CONFUSE | POISON,
		NOART_CONFUSE_POISON = NOART_CONFUSE | POISON,
		NONMOVE_NOART_CONFUSE_POISON = NONMOVE_NOART_CONFUSE | POISON,
		CANCEL_NONMOVE = 0x82,
		CANCEL_NOART = 0x84,
		CANCEL_NONMOVE_NOART = 0x86,
		CANCEL_CONFUSE = 0x88,
		CANCEL_NONMOVE_CONFUSE = 0x8A,
		CANCEL_NOART_CONFUSE = 0x8C,
		CANCEL_NONMOVE_NOART_CONFUSE = 0x8E,
		CANCEL_POISON = 0x90,
		CANCEL_NONMOVE_POISON = 0x92,
		CANCEL_NOART_POISON = 0x94,
		CANCEL_NONMOVE_NOART_POISON = 0x96,
		CANCEL_CONFUSE_POISON = 0x98,
		CANCEL_NONMOVE_CONFUSE_POISON = 0x9A,
		CANCEL_NOART_CONFUSE_POISON = 0x9C,
		CANCEL_NONMOVE_NOART_CONFUSE_POISON = 0x9E,
		NONE = 0xFFFF
	}

	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public EnumData.StatType nAttributiton;

	public EnumData.ChangingState nChanging;

	public bool cancel;

	public int originalState;

	public int nHP;

	public int nMP;

	public bool usePercent;

	private static readonly int CANCEL;

	public UnitStat state;

	public EnumData.Conditions[] conditions;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	public IEnumerator ChangeUnitStateUnit(float waitingTime)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ApplyUnitStatus(UnitData data, float waitingTime)
	{
		return null;
	}

	private void SetAttributeCondition(UnitData data, EnumData.ChangingState changing, EnumData.StatType statType)
	{
	}

	private EnumData.Conditions GetApplyCondition(EnumData.ChangingState changing, EnumData.StatType statType)
	{
		return EnumData.Conditions.NONE;
	}

	private void ConvertXmlUnitStatValue(int idx)
	{
	}

	private void ConvertEEXUnitStatValue(int value)
	{
	}

	private void CovertUnitStat2Conditions()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
