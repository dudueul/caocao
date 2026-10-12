using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarCombatAction : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_attackCheckPrefab;

	[SerializeField]
	private ObscuredString m_attackEffectPrefab;

	[SerializeField]
	private ObscuredInt m_attackEffectType;

	[SerializeField]
	private ObscuredString m_damagedEffectPrefab;

	[SerializeField]
	private ObscuredInt m_damagedEffectType;

	[SerializeField]
	private ObscuredString m_damagedEffectPrefabOnWater;

	[SerializeField]
	private ObscuredInt m_damagedEffectTypeOnWater;

	[SerializeField]
	private ObscuredInt m_actionType;

	[SerializeField]
	private ObscuredInt m_elementalType;

	[SerializeField]
	private ObscuredInt m_damageType;

	[SerializeField]
	private ObscuredInt m_isProjectile;

	[SerializeField]
	private ObscuredInt m_canDouble;

	[SerializeField]
	private ObscuredInt m_effectArea;

	[SerializeField]
	private ObscuredInt m_isTargetCorps;

	[SerializeField]
	private ObscuredInt m_isTargetBuilding;

	[SerializeField]
	private ObscuredInt m_isTargetTile;

	[SerializeField]
	private ObscuredInt m_effectAreaTarget;

	[SerializeField]
	private ObscuredInt m_skillPower;

	[SerializeField]
	private ObscuredInt m_minSkillPowerRate;

	[SerializeField]
	private ObscuredInt m_providePassiveListId;

	[SerializeField]
	private ObscuredInt m_tileEffectRate;

	[SerializeField]
	private ObscuredInt m_provideTileEffect;

	[SerializeField]
	private ObscuredInt m_canCounterAttack;

	[SerializeField]
	private ObscuredInt m_damagedContributionPoint;

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

	public string attackCheckPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string attackEffectPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int attackEffectType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string damagedEffectPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int damagedEffectType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string damagedEffectPrefabOnWater
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int damagedEffectTypeOnWater
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int actionType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int elementalType
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

	public int isProjectile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canDouble
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

	public int isTargetCorps
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isTargetBuilding
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isTargetTile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int effectAreaTarget
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

	public int minSkillPowerRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int providePassiveListId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileEffectRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int provideTileEffect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canCounterAttack
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int damagedContributionPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}
}
