using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class BattleFieldDefenseUnit : UserUnitGroup.UnitInfo
{
	protected ObscuredInt m_pos;

	public int pos
	{
		get
		{
			return 0;
		}
	}

	public BattleFieldDefenseUnit()
	{
	}

	public BattleFieldDefenseUnit(int pos, int unitId)
	{
	}

	public BattleFieldDefenseUnit(int pos, int unitId, ulong weaponSrl, ulong armorSrl, ulong kitSrl, int skinItemId)
	{
	}

	public override void Deserialize(JsonData jsonData)
	{
	}

	public override UserUnitGroup.UnitInfo DeepCopy()
	{
		return null;
	}

	public bool IsEquip(ulong itemSrl)
	{
		return false;
	}

	public void SetEquipItem(ulong weaponSrl, ulong armorSrl, ulong kitSrl)
	{
	}

	public bool Equals(BattleFieldDefenseUnit dstUnit)
	{
		return false;
	}
}
