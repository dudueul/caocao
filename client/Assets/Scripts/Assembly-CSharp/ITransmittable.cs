using System;

// [RE] Rebuilt ITransmittable base (TypeDefIndex 10914): the decompiled
// flow is Serialize(JsonWriter) / Deserialize(JsonData) → DeserializeImpl.
// (The deserializeCallback property lives on UserItem, not the base.)
using LitJson;

[Serializable]
public class ITransmittable
{
	public virtual void Serialize(JsonWriter writer)
	{
	}

	protected virtual void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Deserialize(JsonData jsonData)
	{
		if (jsonData == null)
			return;
		DeserializeImpl(jsonData);
	}
}
