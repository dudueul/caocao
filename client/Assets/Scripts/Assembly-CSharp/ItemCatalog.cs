// [RE] Rebuild-only static registry for the server-driven item catalog.
// Original: Item instances deserialized from the login payload; the rebuild
// mirrors that — MyUserData.ApplyServerState calls ItemCatalog.Load with the
// `catalog` array from /user/getUserData (24-seed contract, milestone 3a).
using System.Collections.Generic;
using LitJson;

public static class ItemCatalog
{
	public static Dictionary<int, Item> byId = new Dictionary<int, Item>();

	public static int version;

	public static Item Get(int id)
	{
		Item it;
		return byId.TryGetValue(id, out it) ? it : null;
	}

	public static void Load(JsonData catalog)
	{
		if (catalog == null || !catalog.IsArray)
			return;
		for (int i = 0; i < catalog.Count; i++)
		{
			var row = catalog[i];
			var item = new Item();
			if (row.Keys.Contains("item_id")) item.id = (int)row["item_id"];
			if (row.Keys.Contains("name")) item.name = row["name"].ToString();
			if (row.Keys.Contains("tier")) item.tier = (int)row["tier"];
			if (row.Keys.Contains("slot")) item.slot = (int)row["slot"];
			if (row.Keys.Contains("item_type")) item.itemType = (int)row["item_type"];
			if (row.Keys.Contains("box_type")) item.boxType = (int)row["box_type"];
			if (row.Keys.Contains("price")) item.price = (int)row["price"];
			if (row.Keys.Contains("gold")) item.gold = (int)row["gold"];
			if (row.Keys.Contains("item_effect")) item.itemEffect = (int)row["item_effect"];
			if (row.Keys.Contains("effect_value")) item.effectValue = (int)row["effect_value"];
			if (row.Keys.Contains("max_value")) item.maxValue = (int)row["max_value"];
			if (row.Keys.Contains("type_value")) item.typeValue = (int)row["type_value"];
			if (row.Keys.Contains("pay_type")) item.payType = (int)row["pay_type"];
			if (row.Keys.Contains("atk")) item.atk = (int)row["atk"];
			if (row.Keys.Contains("atk_up")) item.atkUp = (int)row["atk_up"];
			if (row.Keys.Contains("def")) item.def = (int)row["def"];
			if (row.Keys.Contains("def_up")) item.defUp = (int)row["def_up"];
			if (row.Keys.Contains("inte")) item.inte = (int)row["inte"];
			if (row.Keys.Contains("inte_up")) item.inteUp = (int)row["inte_up"];
			if (row.Keys.Contains("agi")) item.agi = (int)row["agi"];
			if (row.Keys.Contains("agi_up")) item.agiUp = (int)row["agi_up"];
			if (row.Keys.Contains("mor")) item.mor = (int)row["mor"];
			if (row.Keys.Contains("mor_up")) item.morUp = (int)row["mor_up"];
			if (row.Keys.Contains("hp")) item.hp = (int)row["hp"];
			if (row.Keys.Contains("hp_up")) item.hpUp = (int)row["hp_up"];
			if (row.Keys.Contains("mp")) item.mp = (int)row["mp"];
			if (row.Keys.Contains("mp_up")) item.mpUp = (int)row["mp_up"];
			if (row.Keys.Contains("max_lv")) item.maxLv = (int)row["max_lv"];
			if (row.Keys.Contains("target_type")) item.targetType = (int)row["target_type"];
			if (row.Keys.Contains("skill_id")) item.skillId = (int)row["skill_id"];
			if (row.Keys.Contains("unit_id")) item.unitId = (int)row["unit_id"];
			if (row.Keys.Contains("atlas")) item.atlas = row["atlas"].ToString();
			if (row.Keys.Contains("icon")) item.icon = row["icon"].ToString();
			if (row.Keys.Contains("country")) item.country = (int)row["country"];
			if (row.Keys.Contains("job_type")) item.jobType = (int)row["job_type"];
			byId[item.id] = item;
		}
	}
}
