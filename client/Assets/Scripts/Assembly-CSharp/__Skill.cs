using System;

[Serializable]
public class __Skill
{
	public string name;

	public int id;

	public int mp;

	public int ep;

	public int resourceType;

	public int targetType;

	public int targetArea;

	public int effectArea;

	public int skillType;

	public int skillEffect;

	public int damageType;

	public int healType;

	public int skillPower;

	public int accuType;

	public int maxAccu;

	public int ignitionRate;

	public int prevSkill;

	public int nextSkill;

	public int canStreakCast;

	public int canSerenity;

	public int canCloud;

	public int canRain;

	public int canSnow;

	public int canStorm;

	public int canFog;

	public int canUseAnnihilationWar;

	public int canUseThousandBattle;

	public int canUseBattleField;

	public int canUseRaid;

	public int canUseGuildSiege;

	public int canSkillCopy;

	public int showIconInGame;

	public int obstructiveSkill;

	public int aiFrequency;

	public string icon;

	public string fxPrefab;

	public string sfxPrefab;

	public int fxInterval;

	public int grade;

	public int conditionEffect;

	public string SE;

	public string specialSE;

	public int speechSE;

	public string desc;

	public string canUseGameMode;

	public void CopyTo(Skill dest)
	{
	}

	public void Reset()
	{
	}
}
