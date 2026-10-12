using System.Collections.Generic;
using LitJson;

public class FairRuleManager : Singleton<FairRuleManager>
{
	private Dictionary<EnumData.BattleMode, Dictionary<int, FairRule>> fairRulesByTier;

	private Dictionary<EnumData.BattleMode, FairRule> fairRules;

	private Dictionary<EnumData.BattleMode, FairRule> overridenFairRules;

	private Dictionary<EnumData.BattleMode, bool> applyFairRuleDic;

	public static int treasureEnhanceDefault;

	public static int treasureEnhancePassiveDefault;

	public static bool runeDefault;

	public static bool runeSetDefault;

	public static int runeEnhanceDefault;

	public Dictionary<int, FairRule> defaultFairRules;

	protected override void Awake()
	{
	}

	private void Init()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnBattleModeChanged()
	{
	}

	private void OnMultiplayModeChanged()
	{
	}

	public void ClearOverriden(EnumData.BattleMode battleMode)
	{
	}

	public void ClearOverriden()
	{
	}

	public void UpdateFairRule()
	{
	}

	public void SetFairRulesByTier(EnumData.BattleMode battleMode, JsonData gameModeRuleData)
	{
	}

	private void SetFairRule(EnumData.BattleMode battleMode, FairRule fairRule)
	{
	}

	private void SetFairRuleByTier(EnumData.BattleMode battleMode, int tierGrade, FairRule fairRule)
	{
	}

	public void OverrideFairRule(EnumData.BattleMode battleMode, FairRule fairRule)
	{
	}

	public FairRule GetFairRule()
	{
		return null;
	}

	public FairRule GetFairRule(EnumData.BattleMode battleMode)
	{
		return null;
	}

	public FairRule GetFairRuleByTier(int tierGrade)
	{
		return null;
	}

	public FairRule GetFairRuleByTier(EnumData.BattleMode battleMode)
	{
		return null;
	}

	public FairRule GetFairRuleByTier(EnumData.BattleMode battleMode, int tierGrade)
	{
		return null;
	}

	public int GetTierGrade()
	{
		return 0;
	}

	public int GetTierGrade(EnumData.BattleMode battleMode)
	{
		return 0;
	}

	public bool WillApplyFairRule()
	{
		return false;
	}

	public bool WillApplyFairRule(EnumData.BattleMode battleMode)
	{
		return false;
	}

	public bool WillApplyFairRule(EnumData.FairRuleType ruleType, UserItem item)
	{
		return false;
	}

	public bool WillApplyFairRule(EnumData.FairRuleType ruleType, UserRuneSlot runeSlot)
	{
		return false;
	}

	public bool WillApplyFairRule(EnumData.FairRuleType ruleType, RuneItemSlot rune)
	{
		return false;
	}

	public int GetFairRuleValue(EnumData.FairRuleType ruleType, UserItem userItem, int dbValue)
	{
		return 0;
	}

	public void SetApplyFairRule(EnumData.BattleMode battleMode, bool applyFairRule)
	{
	}

	public FairRule GetDefaultFairRule(int tier)
	{
		return null;
	}

	public void SetFairRuleLabels(FairRule rule, UILabelLocalized[] labels)
	{
	}
}
