using System;
using System.Collections.Generic;
using LitJson;

public class UserUnitPassiveInvens : ITransmittable, iTransmitContainer
{
	private static List<UserUnitPassiveInven> EmptyInvens;

	private Dictionary<ulong, UserUnitPassiveInven> m_srl2PassiveInven;

	private Dictionary<int, List<UserUnitPassiveInven>> m_unitId2PassiveInvens;

	private List<int> m_unitIds;

	public Action deserializeCallback { get; set; }

	public Dictionary<int, List<UserUnitPassiveInven>> unitId2PassiveInvens
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public List<UserUnitPassiveInven> get_Item(int unitId)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<int> unitIds
	{
		get
		{
			return null;
		}
	}

	public bool isCached(int unitId)
	{
		return false;
	}

	public UserUnitPassiveInven Get(int unitId, int passiveLIstId)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void UpdateId2Units()
	{
	}
}
