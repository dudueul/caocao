using System;
using LitJson;

[Serializable]
public class UserTags : ITransmittable, iTransmitContainer
{
	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
