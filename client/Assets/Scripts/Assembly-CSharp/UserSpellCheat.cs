using System;
using LitJson;

public class UserSpellCheat
{
	public enum Type
	{
		Cheat_MaxCost = 0,
		Cheat_InitCost = 1
	}

	public static Action callBackUpdate;

	public Type type;

	public int Value { get; set; }

	public UserSpellCheat()
	{
	}

	public UserSpellCheat(JsonData data)
	{
	}

	public void Apply()
	{
	}

	private void Notice()
	{
	}

	private void SetMaxPossessionWallet()
	{
	}

	private void SetInitPayWallet()
	{
	}

	public void Serialize(JsonWriter writer)
	{
	}

	private void Deserialize(JsonData data)
	{
	}

	private void UpdateCallBack()
	{
	}
}
