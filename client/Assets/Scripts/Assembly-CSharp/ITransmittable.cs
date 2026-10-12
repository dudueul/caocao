using LitJson;

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
	}
}
