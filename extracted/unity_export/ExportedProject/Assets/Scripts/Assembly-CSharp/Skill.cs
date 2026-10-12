using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Skill : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_mp;

	[SerializeField]
	private ObscuredInt m_ep;

	[SerializeField]
	private ObscuredInt m_resourceType;

	[SerializeField]
	private ObscuredInt m_targetType;

	[SerializeField]
	private ObscuredInt m_targetArea;

	[SerializeField]
	private ObscuredInt m_effectArea;

	[SerializeField]
	private ObscuredInt m_skillType;

	[SerializeField]
	private ObscuredInt m_skillEffect;

	[SerializeField]
	private ObscuredInt m_damageType;

	[SerializeField]
	private ObscuredInt m_healType;

	[SerializeField]
	private ObscuredInt m_skillPower;

	[SerializeField]
	private ObscuredInt m_accuType;

	[SerializeField]
	private ObscuredInt m_maxAccu;

	[SerializeField]
	private ObscuredInt m_ignitionRate;

	[SerializeField]
	private ObscuredInt m_prevSkill;

	[SerializeField]
	private ObscuredInt m_nextSkill;

	[SerializeField]
	private ObscuredInt m_canStreakCast;

	[SerializeField]
	private ObscuredInt m_canSerenity;

	[SerializeField]
	private ObscuredInt m_canCloud;

	[SerializeField]
	private ObscuredInt m_canRain;

	[SerializeField]
	private ObscuredInt m_canSnow;

	[SerializeField]
	private ObscuredInt m_canStorm;

	[SerializeField]
	private ObscuredInt m_canFog;

	[SerializeField]
	private ObscuredInt m_canUseAnnihilationWar;

	[SerializeField]
	private ObscuredInt m_canUseThousandBattle;

	[SerializeField]
	private ObscuredInt m_canUseBattleField;

	[SerializeField]
	private ObscuredInt m_canUseRaid;

	[SerializeField]
	private ObscuredInt m_canUseGuildSiege;

	[SerializeField]
	private ObscuredInt m_canSkillCopy;

	[SerializeField]
	private ObscuredInt m_showIconInGame;

	[SerializeField]
	private ObscuredInt m_obstructiveSkill;

	[SerializeField]
	private ObscuredInt m_aiFrequency;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_fxPrefab;

	[SerializeField]
	private ObscuredString m_sfxPrefab;

	[SerializeField]
	private ObscuredInt m_fxInterval;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredInt m_conditionEffect;

	[SerializeField]
	private ObscuredString m_SE;

	[SerializeField]
	private ObscuredString m_specialSE;

	[SerializeField]
	private ObscuredInt m_speechSE;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredString m_canUseGameMode;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ep
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int resourceType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int targetType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int targetArea
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int effectArea
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int skillType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int skillEffect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int damageType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int healType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int skillPower
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int accuType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxAccu
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ignitionRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int prevSkill
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int nextSkill
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canStreakCast
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canSerenity
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canCloud
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canRain
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canSnow
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canStorm
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canFog
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canUseAnnihilationWar
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canUseThousandBattle
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canUseBattleField
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canUseRaid
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canUseGuildSiege
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canSkillCopy
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int showIconInGame
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int obstructiveSkill
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int aiFrequency
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string fxPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string sfxPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int fxInterval
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int grade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int conditionEffect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string SE
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string specialSE
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int speechSE
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string desc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string canUseGameMode
	{
		get
		{
			return null;
		}
		set
		{
		}
	}
}
