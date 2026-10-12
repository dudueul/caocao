using System;
using System.Collections.Generic;
using LitJson;

public class UserQuickBattleDecks : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserQuickBattleDeck> m_srl2Deck;

	private List<ulong> m_srls;

	private Dictionary<EnumData.BattleMode, List<UserQuickBattleDeck>> battleMode2QuickBattleDecks;

	public Dictionary<ulong, UserQuickBattleDeck> dicSrl
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserQuickBattleDeck get_Item(ulong srl)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	private void InitBattleDeckDictionary()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void UpdateQuickBattleDeckDictionary()
	{
	}

	public List<UserQuickBattleDeck> GetQuickBattleDeck(EnumData.BattleMode battleMode, int fieldId)
	{
		return null;
	}

	public void RequestUpdateQuickBattleDeck(EnumData.BattleMode battleMode, int fieldId, int slotNum, List<UserQuickBattleDeckUnit> deckUnits, Action<bool> callback = null)
	{
	}

	public static string GetDefaultDeckName(EnumData.BattleMode battleMode)
	{
		return null;
	}

	public void RequestPurchaseQuickBattleDeck(EnumData.BattleMode battleMode, int fieldId, Action<bool, UserQuickBattleDeck> callback)
	{
	}

	public void RequestChangeQuickBattleDeckName(ulong deckSrl, string newName, Action<bool> callback)
	{
	}

	public void RequestSetQuickBattleDeckTreasures(ulong deckSrl, Action<bool> callback)
	{
	}
}
