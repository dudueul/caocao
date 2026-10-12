using UnityEngine;

public class ExpectDmg
{
	public enum ExpectRelation
	{
		Strong = 0,
		Normal = 1,
		Weak = 2
	}

	public UnitData unitData;

	public float dmgPct;

	public int atkPct;

	public EnumData.UnitResType dmgType;

	public ExpectRelation relation;

	public Color accColor;

	public void SetData(UnitData unitData, float dmgPct, int atkPct, EnumData.UnitResType dmgType = EnumData.UnitResType.HP, ExpectRelation relation = ExpectRelation.Normal)
	{
	}
}
