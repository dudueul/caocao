using System;

[Serializable]
public class __BattleFieldNpcUnit
{
	public string name;

	public int id;

	public int type;

	public int unit_id;

	public int weapon_tr;

	public int armor_tr;

	public int kit_tr;

	public int passive1;

	public int passive2;

	public int passive3;

	public void CopyTo(BattleFieldNpcUnit dest)
	{
	}

	public void Reset()
	{
	}
}
