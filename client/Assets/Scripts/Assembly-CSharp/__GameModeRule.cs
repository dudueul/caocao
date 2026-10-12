using System;
using System.Collections.Generic;

[Serializable]
public class __GameModeRule
{
	public int id;

	public int pRawDmg;

	public int sRawDmg;

	public int randDmg;

	public int multipleDmg;

	public int multipleHeal;

	public int multipleHPBase;

	public int multipleHPByLv;

	public int toughFightRange;

	public int maxCost;

	public int canPause;

	public int canContinue;

	public int canGetExp;

	public int canTag;

	public int isItemOn;

	public int isUndoOn;

	public int countUnit;

	public int countHoldUnit;

	public int countBanUnit;

	public int initUserSpellpt;

	public int maxUserSpellpt;

	public int turnUserSpellPt;

	public List<string> rulePrefab;

	public List<int> blackListPassive;

	public List<int> fairTreasureEnhance;

	public List<int> fairTreasureEnhancePassive;

	public List<int> fairRune;

	public List<int> fairRuneSet;

	public List<int> fairRuneEnhance;

	public List<string> fairDesc;

	public void CopyTo(GameModeRule dest)
	{
	}

	public void Reset()
	{
	}
}
