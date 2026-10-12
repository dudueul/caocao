using System;
using System.Collections.Generic;
using BattlePlay;
using LitJson;

public class UserUnitRelations : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserUnitRelation> m_id2Relations;

	private List<int> m_ids;

	private List<UserUnitRelation> removedList;

	private int m_newCount;

	public Action deserializeCallback;

	private const string PREFS_KEY_RELATION_NEW = "{0}_relation_{1}_new";

	public Dictionary<int, UserUnitRelation> id2Relations
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public int NewCount
	{
		get
		{
			return 0;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserUnitRelation get_Item(int id)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void ApplyStat(IUserDataBase userData, UserUnitRelation unitRelation, bool add)
	{
	}

	public void SetFavorite(int id, bool favorite)
	{
	}

	public bool IsPurchased(int id)
	{
		return false;
	}

	public bool IsDoing(int id)
	{
		return false;
	}

	public bool IsCompleteAble(int id)
	{
		return false;
	}

	public bool IsCompleteMission(int id)
	{
		return false;
	}

	public bool IsCompleted(int id)
	{
		return false;
	}

	public List<int> GetCompletedIdList()
	{
		return null;
	}

	public List<UserUnitRelation> GetCompletedListInMultiplay(List<BattleUnit> battleUnits)
	{
		return null;
	}

	public List<int> GetNotCompletedList()
	{
		return null;
	}

	private void SetNewCount()
	{
	}

	public void ClearNewCount(ulong userId)
	{
	}

	public void CopyFrom(ulong uid, UserUnitRelations src)
	{
	}

	public static bool IsNew(ulong userId, int relationId, UserUnitRelation unitRelation)
	{
		return false;
	}

	public static string GetPrefsKeyString_RelationNew(ulong userId, int relationId)
	{
		return null;
	}
}
