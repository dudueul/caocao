using System;
using System.Collections.Generic;
using LitJson;

[Serializable]
public class UserStorybookRewardHistories : ITransmittable, iTransmitContainer
{
	public delegate void RequestCallback();

	private Dictionary<ulong, UserStorybookRewardHistory> m_srl2RewardHistories;

	private List<ulong> m_srls;

	private Dictionary<int, List<UserStorybookRewardHistory>> m_storybookId2RewardHistories;

	public Action deserializeCallback;

	public Dictionary<ulong, UserStorybookRewardHistory> srl2RewardHistories
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserStorybookRewardHistory get_Item(ulong srl)
	{
		return null;
	}

	public List<UserStorybookRewardHistory> GetStorybookRewardHistories(int storybookId)
	{
		return null;
	}

	public List<UserStorybookRewardHistory> GetStorybookRewardHistories(int storybookId, EnumData.ScenarioModeLevel mode)
	{
		return null;
	}

	private void RebuildDataByStorybookId()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool CanRequestStorybookReward(int storybookId, int gradeCount, EnumData.ScenarioModeLevel mode)
	{
		return false;
	}

	public bool IsAlreadyAchievedReward(int storybookId, int gradeCount, EnumData.ScenarioModeLevel mode)
	{
		return false;
	}

	public RewardInfo GetNextGradeReward(int storybookId, EnumData.ScenarioModeLevel mode, out int gradeCount)
	{
		gradeCount = default(int);
		return null;
	}

	public void RequestHistory(int[] storybookIds, RequestCallback callback)
	{
	}
}
