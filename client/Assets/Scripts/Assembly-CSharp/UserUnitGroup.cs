using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserUnitGroup : ITransmittable
{
	public class UnitInfo
	{
		public ObscuredInt unitId;

		public ObscuredULong weaponTreasure;

		public ObscuredULong armorTreasure;

		public ObscuredULong kitTreasure;

		public ObscuredInt skinItemId;

		public ObscuredBool holdUnit;

		public ObscuredInt idx;

		public virtual Dictionary<string, object> Serialize()
		{
			return null;
		}

		public virtual void Deserialize(JsonData jsonData)
		{
		}

		public virtual UnitInfo DeepCopy()
		{
			return null;
		}
	}

	protected ObscuredULong m_srl;

	protected List<UnitInfo> m_unitList;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public List<UnitInfo> unitList
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	protected virtual UnitInfo GenerateUnitInfo()
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	protected void DeserializeUnitList(JsonData unitList)
	{
	}

	public void CopyTo(UserUnitGroup dst)
	{
	}

	public bool HasUnit(int unitId)
	{
		return false;
	}

	public bool IsHoldUnit(int unitId)
	{
		return false;
	}

	public void UpdateFormation(List<UnitInfo> unitOrder)
	{
	}

	public void UpdateUnitInfo()
	{
	}
}
