using System;

public sealed class JsonFieldAttribute : Attribute
{
	public string JsonName { get; private set; }

	public JsonFieldAttribute(string jsonName)
	{
	}
}
