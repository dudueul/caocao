using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserStorybookRewardHistory : ITransmittable
{
	public ObscuredLong srl { get; private set; }

	public ObscuredInt storybookId { get; private set; }

	public ObscuredBool isHard { get; private set; }

	public int gradeCount { get; private set; }

	public DateTime regDate { get; private set; }

	public ObscuredBool isClassic { get; private set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
