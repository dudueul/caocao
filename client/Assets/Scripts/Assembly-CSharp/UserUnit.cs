using System;
using System.Collections.Generic;
using System.IO;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class UserUnit : ITransmittable
{
	public class UnitWeaknessInfo
	{
		private enum DamageRate
		{
			Weak = 0,
			Strong = 1,
			Soften = 2
		}

		private bool m_activated;

		private int m_weakPassiveId;

		private int m_prevWeakTypeIndex;

		private List<int> m_weakness;

		private List<int> m_revealed;

		private int[] m_damageRate;

		private WeakPassive m_weakPassive;

		private int m_setupCount;

		public int PrevWeakTypeIndex
		{
			get
			{
				return 0;
			}
		}

		public int WeakDamageRate
		{
			get
			{
				return 0;
			}
		}

		public int StrongDamageRate
		{
			get
			{
				return 0;
			}
		}

		public int SoftenDamageRate
		{
			get
			{
				return 0;
			}
		}

		public int MaxArmor
		{
			get
			{
				return 0;
			}
		}

		public int WeaknessCount
		{
			get
			{
				return 0;
			}
		}

		public bool Active
		{
			get
			{
				return false;
			}
		}

		public UnitWeaknessInfo Export()
		{
			return null;
		}

		public void Import(UnitWeaknessInfo source)
		{
		}

		public void Setup(int weakPassiveId, int m_weakTypeIndex)
		{
		}

		public void Clear()
		{
		}

		private bool CheckValidIndex(int index)
		{
			return false;
		}

		public void SetWeakness(int index, int value)
		{
		}

		public EnumData.WeaknessType GetWeakness(int index)
		{
			return EnumData.WeaknessType.Fan;
		}

		public void SetRevealedState(int index, bool state)
		{
		}

		public bool GetRevealedState(int index)
		{
			return false;
		}

		public int FindWeakness(EnumData.WeaknessType weaknessType)
		{
			return 0;
		}
	}

	private class CustomLevelMode
	{
		public ObscuredInt m_jobLevel;

		public ObscuredInt m_exp;

		public ObscuredInt m_weapon;

		public ObscuredInt m_armor;

		public ObscuredInt m_kit;

		public ObscuredInt m_weaponExp;

		public ObscuredInt m_armorExp;

		public ObscuredInt m_kitExp;

		public GeneralStat m_trainStat;

		public ObscuredInt m_reinforceLevel;

		public ObscuredInt m_level;

		public UserItem[] m_equipments;

		public int m_jobId;

		public Job m_job;

		[DoNotJsonfy]
		public Dictionary<int, int> m_tileJobDefense;

		public void CopyDeepTo(CustomLevelMode other)
		{
		}

		public void WriteInvariants(BinaryWriter w)
		{
		}

		public bool ReadInvariants(BinaryReader r)
		{
			return false;
		}
	}

	private enum EquipSlot
	{
		Weapon = 0,
		Armor = 1,
		Kit = 2,
		WeaponTreasure = 3,
		ArmorTreasure = 4,
		KitTreasure = 5
	}

	[SerializeField]
	public class UnitRes
	{
		protected static int resLessMin;

		protected static int resNum;

		[SerializeField]
		protected ObscuredInt[] m_res;

		protected UserUnit ownerUnit;

		// C# has no syntax for parameterized property 'Item'.
		public virtual int get_Item(EnumData.UnitResType resType)
		{
			return 0;
		}

		public virtual void set_Item(EnumData.UnitResType resType, int value)
		{
		}

		public UnitRes(UserUnit userUnit)
		{
		}
	}

	[SerializeField]
	public class UnitMaxRes : UnitRes
	{
		// C# has no syntax for parameterized property 'Item'.
		public override void set_Item(EnumData.UnitResType resType, int value)
		{
		}

		public UnitMaxRes(UserUnit userUnit)
			: base(null)
		{
		}
	}

	public delegate void ResChangeCallback(UserUnit userUnit, EnumData.UnitResType resType);

	protected ObscuredULong m_uid;

	protected ObscuredULong m_srl;

	private ObscuredInt m_id;

	protected ObscuredString m_nickName;

	protected ObscuredInt m_jobLevel;

	protected ObscuredInt m_exp;

	protected ObscuredInt m_weapon;

	protected ObscuredInt m_armor;

	protected ObscuredInt m_kit;

	protected ObscuredInt m_weapon_exp;

	protected ObscuredInt m_armor_exp;

	protected ObscuredULong m_weaponTre;

	protected ObscuredULong m_armorTre;

	protected ObscuredULong m_kitTre;

	protected ObscuredBool m_isInjury;

	protected ObscuredBool m_isDemise;

	private GeneralStat m_trainStat;

	private GeneralStat m_bonusTrainStat;

	private TroopStat m_relationStat;

	protected ObscuredInt m_lookUnitID;

	protected ObscuredInt m_isFavorite;

	protected ObscuredInt m_reinforceLevel;

	protected ObscuredInt m_energy;

	protected DateTime m_lastRecoverTime;

	protected ObscuredInt m_runeSlotIndex;

	protected ObscuredInt m_skinItemId;

	protected ObscuredInt m_unitSideManually;

	protected readonly ObscuredInt[] m_passives;

	protected ObscuredInt m_level;

	private bool m_usingTreasureFilterMode;

	private bool m_usingRuneFilterMode;

	private UnitWeaknessInfo m_weaknessInfo;

	private bool m_usingCustomLevelMode;

	private CustomLevelMode m_customLevelMode;

	private UserItem[] m_equipments;

	private SortedList<int, UserRuneSlot> m_runeSlotGroup;

	public ObscuredBool persist;

	public string m_defenseTownName;

	public bool m_isMayorCached;

	private Action deserializeCallback__BackingField;

	private Action<UserUnit> onChangedStat__BackingField;

	private ObscuredBool m_isCompleteAction;

	private IUserDataBase m_userData;

	private string m_localizedName;

	private bool m_isUpdatingFavorite;

	private Unit m_unitInfo;

	private int m_jobId;

	private Job m_job;

	private Dictionary<int, int> m_tileJobDefense;

	private ObscuredInt m_aiType;

	private ObscuredInt m_levelCorrect;

	private ObscuredInt m_aiPriorityCorrect;

	private ObscuredInt m_group;

	private ObscuredBool m_isClone;

	public bool showDefeatMsg;

	private bool m_updatingMaxHPMP;

	private int m_MaxHPMPCacheVersion;

	private int m_statVersion;

	private int m_statCacheVersion;

	private GeneralStat m_generalStat;

	private GeneralStat m_basicStat;

	private GeneralStat m_buffStat;

	private GeneralStat m_unitStatCheat;

	private TroopStat m_troopStatWithoutBuff;

	private TroopStat m_troopStatBuff;

	private TroopStat m_troopStatSpAdjustInBattle;

	private TroopStat m_troopStatAdjust;

	private TroopStat m_troopStatWithoutAdjust;

	private TroopStat m_prevTroopStat;

	private TroopStat m_troopStat;

	private TroopStat m_guildWarTroopStat;

	private TroopStat m_guildWarTroopStatNoBuff;

	public bool GuildWarSortingTag;

	private bool m_updatingStat;

	private UnitRes m_res;

	private UnitRes m_maxRes;

	private List<ResChangeCallback> m_resChangeCallbackList;

	private Action<UserUnit>[] onChangedRes;

	private TroopsGrade m_troopsGrade;

	private List<UnitCondition> m_conditions;

	private int m_skillVersion;

	private int m_skillCacheVersion;

	private List<ObscuredInt> m_skillArr;

	private List<ObscuredInt> m_eventSkillArr;

	private bool m_updatingSkillArr;

	private bool m_useFixedHpMp;

	private int m_fixedMaxHp;

	private int m_fixedMaxMp;

	private int m_fixedHp;

	private int m_fixedMp;

	private List<SpActionBase> m_spActionsBackup;

	private List<SpActionBase> m_spActions;

	private int m_spActionVersion;

	private int m_spActionCacheVersion;

	public UnitData unitData;

	private bool m_updatingSpAction;

	private static float[] buffPoints;

	private static float[] debuffPoints;

	public UnitSide unitSide
	{
		get
		{
			return UnitSide.OWN;
		}
	}

	public UnitSide unitSideManually
	{
		set
		{
		}
	}

	public int unitSynergyType
	{
		get
		{
			return 0;
		}
	}

	public bool useTreasureFilterMode
	{
		get
		{
			return false;
		}
	}

	public bool useRuneFilterMode
	{
		get
		{
			return false;
		}
	}

	public UnitWeaknessInfo WeaknessInfo
	{
		get
		{
			return null;
		}
	}

	public bool useCustomLevelMode
	{
		get
		{
			return false;
		}
	}

	public UserItem weaponItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserItem armorItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserItem kitItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserItem weaponTreItem
	{
		get
		{
			return null;
		}
	}

	public UserItem armorTreItem
	{
		get
		{
			return null;
		}
	}

	public UserItem kitTreItem
	{
		get
		{
			return null;
		}
	}

	public UserItem equippedWeaponTreItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserItem equippedArmorTreItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UserItem equippedKitTreItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public ObscuredInt runeSlotIndex
	{
		get
		{
			return default(ObscuredInt);
		}
		set
		{
		}
	}

	public UserRuneSlot runeSlot
	{
		get
		{
			return null;
		}
	}

	public SortedList<int, UserRuneSlot> runeSlotGroup
	{
		get
		{
			return null;
		}
	}

	public TroopStat runeStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat relationStat
	{
		get
		{
			return default(TroopStat);
		}
		set
		{
		}
	}

	public int addAtkPct { get; set; }

	public int addDefPct { get; set; }

	public int addIntePct { get; set; }

	public int addAgiPct { get; set; }

	public int addMorPct { get; set; }

	public int addHpPct { get; set; }

	public int addMpPct { get; set; }

	public bool isCompleteAction
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isCompleteMove { get; set; }

	public bool inProgressingFlow { get; set; }

	public ulong uid
	{
		get
		{
			return 0uL;
		}
		private set
		{
		}
	}

	public ulong srl
	{
		get
		{
			return 0uL;
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

	public string nickName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int originJobLevel
	{
		get
		{
			return 0;
		}
	}

	public int jobLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int realJobLevel
	{
		get
		{
			return 0;
		}
	}

	public string localizedName
	{
		get
		{
			return null;
		}
	}

	public int exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weapon_exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armor_exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weapon
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public ulong weaponTre
	{
		get
		{
			return 0uL;
		}
	}

	public ulong equippedWeaponTre
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong armorTre
	{
		get
		{
			return 0uL;
		}
	}

	public ulong equippedArmorTre
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong kitTre
	{
		get
		{
			return 0uL;
		}
	}

	public ulong equippedKitTre
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public int skinId
	{
		get
		{
			return 0;
		}
	}

	public int skinItemId
	{
		get
		{
			return 0;
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	public int realLevel
	{
		get
		{
			return 0;
		}
	}

	public bool isMaxLevel
	{
		get
		{
			return false;
		}
	}

	public bool isAvailableMaxUnitLevel
	{
		get
		{
			return false;
		}
	}

	public bool isSoldierUnit
	{
		get
		{
			return false;
		}
	}

	public int baseExpSum
	{
		get
		{
			return 0;
		}
	}

	public int needExp
	{
		get
		{
			return 0;
		}
	}

	public int expOnCurrentLevToShow
	{
		get
		{
			return 0;
		}
	}

	public bool isInjury
	{
		get
		{
			return false;
		}
	}

	public bool isEnoughEnergy
	{
		get
		{
			return false;
		}
	}

	public int maxEnergy
	{
		get
		{
			return 0;
		}
	}

	public bool isMaxEnergy
	{
		get
		{
			return false;
		}
	}

	public bool canHeal
	{
		get
		{
			return false;
		}
	}

	public bool canPromote
	{
		get
		{
			return false;
		}
	}

	public bool canReinforce
	{
		get
		{
			return false;
		}
	}

	public bool isDemise
	{
		get
		{
			return false;
		}
	}

	public Action favoriteCallback { get; set; }

	public int isFavorite
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reinforceLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int lookUnitID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int energy
	{
		get
		{
			return 0;
		}
	}

	[DoNotJsonfy]
	public DateTime nextEnergyChargeTime
	{
		get
		{
			return default(DateTime);
		}
	}

	public double time2NextEnergyChargeMilliSecs
	{
		get
		{
			return 0.0;
		}
	}

	public float remainedSecondsToMinEnergy
	{
		get
		{
			return 0f;
		}
	}

	public EnumData.UnitResType resourceType
	{
		get
		{
			return EnumData.UnitResType.HP;
		}
	}

	public bool isMyUnit
	{
		get
		{
			return false;
		}
	}

	public bool IsAnyUnitPassive
	{
		get
		{
			return false;
		}
	}

	[DoNotJsonfy]
	public Unit unitInfo
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public int jobID
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	[DoNotJsonfy]
	public Job job
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	[DoNotJsonfy]
	public int jobStyle
	{
		get
		{
			return 0;
		}
	}

	[DoNotJsonfy]
	public int jobType
	{
		get
		{
			return 0;
		}
	}

	[DoNotJsonfy]
	public Dictionary<int, int> tileJobDefense
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	[DoNotJsonfy]
	public JobType jobDesc { get; private set; }

	public int aiType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int levelCorrect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int aiPriorityCorrect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int group
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isClone
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int str
	{
		get
		{
			return 0;
		}
	}

	public int cha
	{
		get
		{
			return 0;
		}
	}

	public int wis
	{
		get
		{
			return 0;
		}
	}

	public int dex
	{
		get
		{
			return 0;
		}
	}

	public int luck
	{
		get
		{
			return 0;
		}
	}

	public GeneralStat trainStatByLevelMode
	{
		get
		{
			return default(GeneralStat);
		}
		set
		{
		}
	}

	public GeneralStat trainStat
	{
		get
		{
			return default(GeneralStat);
		}
	}

	public int maxTrainStatByJobLevel
	{
		get
		{
			return 0;
		}
	}

	public int maxTrainStatByUnitCost
	{
		get
		{
			return 0;
		}
	}

	public int maxTrainStat
	{
		get
		{
			return 0;
		}
	}

	public int currentTotalTrainCountByLevelMode
	{
		get
		{
			return 0;
		}
	}

	public int maxTotalTrainCountByLevelMode
	{
		get
		{
			return 0;
		}
	}

	public int currentTotalTrainCount
	{
		get
		{
			return 0;
		}
	}

	public int maxTotalTrainCount
	{
		get
		{
			return 0;
		}
	}

	public GeneralStat buffStat
	{
		get
		{
			return default(GeneralStat);
		}
		set
		{
		}
	}

	public GeneralStat basicStat
	{
		get
		{
			return default(GeneralStat);
		}
	}

	public GeneralStat generalStat
	{
		get
		{
			return default(GeneralStat);
		}
	}

	public GeneralStat unitStatCheat
	{
		get
		{
			return default(GeneralStat);
		}
		set
		{
		}
	}

	public TroopStat troopStatWithoutBuff
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat troopStatBuff
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat troopStatInBattle
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat troopStatAdjust
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat troopStatWithoutAdjust
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat troopStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat GuildWarTroopStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat GuildWarTroopStatNoBuff
	{
		get
		{
			return default(TroopStat);
		}
	}

	public int combatPower
	{
		get
		{
			return 0;
		}
	}

	public int nonCombatPower
	{
		get
		{
			return 0;
		}
	}

	public UnitRes res
	{
		get
		{
			return null;
		}
	}

	public UnitRes maxRes
	{
		get
		{
			return null;
		}
	}

	public float expPercentage
	{
		get
		{
			return 0f;
		}
	}

	public int atk
	{
		get
		{
			return 0;
		}
	}

	public int def
	{
		get
		{
			return 0;
		}
	}

	public int inte
	{
		get
		{
			return 0;
		}
	}

	public int agi
	{
		get
		{
			return 0;
		}
	}

	public int mor
	{
		get
		{
			return 0;
		}
	}

	public int move
	{
		get
		{
			return 0;
		}
	}

	public int moveStep { get; set; }

	public int priority
	{
		get
		{
			return 0;
		}
	}

	public bool isProjectile
	{
		get
		{
			return false;
		}
	}

	public bool isCatapult
	{
		get
		{
			return false;
		}
	}

	public TroopsGrade troopsGrade
	{
		get
		{
			return TroopsGrade.Common;
		}
		set
		{
		}
	}

	public List<UnitCondition> conditions
	{
		get
		{
			return null;
		}
	}

	public List<ObscuredInt> skillArr
	{
		get
		{
			return null;
		}
	}

	public bool isAlive
	{
		get
		{
			return false;
		}
	}

	public bool isDead
	{
		get
		{
			return false;
		}
	}

	public bool isExhausted
	{
		get
		{
			return false;
		}
	}

	public int normalAttackRangeType
	{
		get
		{
			return 0;
		}
	}

	public int normalAttackEffectRangeType
	{
		get
		{
			return 0;
		}
	}

	public bool expendAttackRange
	{
		get
		{
			return false;
		}
	}

	public bool hasShortDistanceArchery
	{
		get
		{
			return false;
		}
	}

	public bool isNonAggressionUnit
	{
		get
		{
			return false;
		}
	}

	public int unitConsumptionCost
	{
		get
		{
			return 0;
		}
	}

	public int unitHealCost
	{
		get
		{
			return 0;
		}
	}

	public List<SpActionBase> spActions
	{
		get
		{
			return null;
		}
	}

	public event Action deserializeCallback
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<UserUnit> onChangedStat
	{
		add
		{
		}
		remove
		{
		}
	}

	public UserUnit()
	{
	}

	public UserUnit(ulong uid, ulong srl, int id, string nickName, int jobLevel, int exp, int weaponID, int armorID, int kitID, int weaponEXP, int armorEXP, int skinItemId = 0)
	{
	}

	public int GetEquippedPassivePoint()
	{
		return 0;
	}

	public void WriteInvariantsForTreasureFilterMode(BinaryWriter w)
	{
	}

	public bool ReadInvariantsForTreasureFilterMode(BinaryReader r)
	{
		return false;
	}

	public void WriteInvariantsForRuneFilterMode(BinaryWriter w)
	{
	}

	public bool ReadInvariantsForRuneFilterMode(BinaryReader r)
	{
		return false;
	}

	public void WriteInvariantsForCustomLevelMode(BinaryWriter w)
	{
	}

	public bool ReadInvariantsForCustomLevelMode(BinaryReader r)
	{
		return false;
	}

	private UserItem GetEquipment(EquipSlot slot)
	{
		return null;
	}

	private UserItem GetEquippedEquipment(EquipSlot slot)
	{
		return null;
	}

	public void CheckTreasurePassives(EnumData.ItemSlotType slotType)
	{
	}

	private void SetEquipment(UserItem value, EquipSlot slot)
	{
	}

	public UserItem GetEquipment(EnumData.ItemSlotType slot)
	{
		return null;
	}

	public UserItem GetEquippedEquipment(EnumData.ItemSlotType slot)
	{
		return null;
	}

	public string GetEquipmentName(EnumData.ItemSlotType slot)
	{
		return null;
	}

	public ulong GetRuneSlotSrl(int runeSlotIndex)
	{
		return 0uL;
	}

	public UserRuneSlot GetRuneSlot(int slotIndex)
	{
		return null;
	}

	public void SetRuneSlot(int slotIndex, UserRuneSlot userRuneSlot)
	{
	}

	public RuneItemSlot GetRune(int idxBaseOne)
	{
		return null;
	}

	public RuneItemSlot GetRune(int slotIndex, int idxBaseOne)
	{
		return null;
	}

	public RuneItemSlot GetEquippedRune(int idxBaseOne)
	{
		return null;
	}

	public RuneItemSlot GetEquippedRune(int slotIndex, int idxBaseOne)
	{
		return null;
	}

	private RuneItemSlot _getRune(UserRuneSlot slot, int idxBaseOne)
	{
		return null;
	}

	private RuneItemSlot _getEquippedRune(UserRuneSlot slot, int idxBaseOne)
	{
		return null;
	}

	public bool IsEquippedRune(int runeId, int runePassiveId)
	{
		return false;
	}

	public void UpdateRuneStat()
	{
	}

	public override string ToString()
	{
		return null;
	}

	private void UpdateClassicExp(int oldExp)
	{
	}

	private void UpdateExp(int oldExp)
	{
	}

	private void UpdateClassicWeapon()
	{
	}

	private void UpdateWeapon()
	{
	}

	private void UpdateClassicArmor()
	{
	}

	private void UpdateArmor()
	{
	}

	private void UpdateClassicKit()
	{
	}

	private void UpdateKit()
	{
	}

	public void Injury()
	{
	}

	public void Resurrection()
	{
	}

	public void Demise()
	{
	}

	public int GetRunePoint()
	{
		return 0;
	}

	private int GetGuildWarRuneValue(int level)
	{
		return 0;
	}

	public int GetUsedPassivePoint()
	{
		return 0;
	}

	public void SetUnitPassive(int index, int passiveId)
	{
	}

	public int GetUnitPassive(int index)
	{
		return 0;
	}

	public int GetUnitPassiveIndex(int id)
	{
		return 0;
	}

	private void UpdatePermanentData()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void SerializeDefenceInfo(JsonWriter writer)
	{
	}

	public int GetMaxTrainStatByLevelMode()
	{
		return 0;
	}

	public int GetMaxTrainStat()
	{
		return 0;
	}

	public void InvalidateStat()
	{
	}

	private void UpdateStatCache()
	{
	}

	private TroopStat GetInitTroopStat(GeneralStat stat)
	{
		return default(TroopStat);
	}

	private TroopStat GetReinforcedTroopsStat()
	{
		return default(TroopStat);
	}

	public TroopStat GetTechTroopsStat()
	{
		return default(TroopStat);
	}

	public void RegisterResChangeCallback(ResChangeCallback callback)
	{
	}

	public void UnRegisterResChangeCallback(ResChangeCallback callback)
	{
	}

	protected void DoResChangeCallback(EnumData.UnitResType resType)
	{
	}

	public void ClearResChangeCallback()
	{
	}

	public float GetResPercentage(EnumData.UnitResType resType)
	{
		return 0f;
	}

	public void SetLevel(int desireLevel)
	{
	}

	public void InvalidateSkillArr()
	{
	}

	private void UpdateSkillArrCache()
	{
	}

	public void AddSkill(EnumData.Skills skillId)
	{
	}

	public void AddEventSkill(EnumData.Skills skillId)
	{
	}

	public void RemoveEventSkill(EnumData.Skills skillId)
	{
	}

	public void ClearEventSkill()
	{
	}

	public bool CheckSkillLevel(EnumData.Skills skillId)
	{
		return false;
	}

	public List<int> AddSkillByLevelUp()
	{
		return null;
	}

	public void RemoveSkill(EnumData.Skills skillId)
	{
	}

	public void SetUnitInfo(bool init = true)
	{
	}

	public void ResetStatesforBattleStart()
	{
	}

	public int GetSkillAttackRangeType(EnumData.Skills skillID)
	{
		return 0;
	}

	public int GetSkillEffectRangeType(EnumData.Skills skillID)
	{
		return 0;
	}

	public int GetSkillCost(EnumData.Skills skillID, out EnumData.UnitResType resType)
	{
		resType = default(EnumData.UnitResType);
		return 0;
	}

	public int GetItemRangeType(int itemID)
	{
		return 0;
	}

	public bool ContainsTilePassive(int tileId)
	{
		return false;
	}

	public int GetTileJobDefenese(int tileId)
	{
		return 0;
	}

	public bool IsAnnihilationWarTileJobDefensePositive(int warId)
	{
		return false;
	}

	public bool IsTileJobDefensePositive(EnumData.BattleMode battleMode, int mapId, int tileId = 0)
	{
		return false;
	}

	public bool HasConditions(EnumData.Conditions conditionID)
	{
		return false;
	}

	public bool CheckPreparationSkill(EnumData.Skills skillID)
	{
		return false;
	}

	public int GetHighHierarchyConditionId(EnumData.Conditions conditionID)
	{
		return 0;
	}

	public int GetLowHierarchyConditionId(EnumData.Conditions conditionID)
	{
		return 0;
	}

	public bool HasCancelDefensePassiveCondition()
	{
		return false;
	}

	public void SetStat(bool isInit)
	{
	}

	private void UpdateMaxHPMPCache()
	{
	}

	public void SetFixedMaxHpMp(int maxHp, int maxMp, int hp, int mp)
	{
	}

	private void InitHpmpMax()
	{
	}

	public bool IsEquippedItem(ulong itemSrl, int itemId = 0, bool isTreasure = true)
	{
		return false;
	}

	public bool IsEquippedItemId(int itemId)
	{
		return false;
	}

	public bool IsBetterEquip(UserItem userItem)
	{
		return false;
	}

	public bool IsBetterEquip(Item item)
	{
		return false;
	}

	public UnitCondition AddUnitCondition(EnumData.Skills triggerSkillId, EnumData.Conditions conditionId, int occuredTurn, UnitSide occuredTurnOwner, UnitData triggerUnitData, out UnitCondition added)
	{
		added = null;
		return null;
	}

	private UnitCondition ChangeUnitCondition(EnumData.Conditions conditionId, EnumData.Conditions oppositeConditionId, EnumData.Skills triggerSkillId, int occuredTurn, UnitSide occuredTurnOwner, UnitData triggerUnitData, out UnitCondition added)
	{
		added = null;
		return null;
	}

	public UnitCondition FindUnitCondition(EnumData.Conditions conditionId)
	{
		return null;
	}

	public void AddUnitCondition(UnitCondition condition)
	{
	}

	public void RemoveAllUnitConditions()
	{
	}

	public UnitCondition RemoveUnitCondition(EnumData.Conditions conditionId)
	{
		return null;
	}

	public UnitCondition RemoveAuraUnitCondition()
	{
		return null;
	}

	public void RemoveUnitCondition(UnitCondition condition)
	{
	}

	public UnitCondition IncreaseConditionStack(UnitCondition condition, int amount)
	{
		return null;
	}

	public UnitCondition DecreaseConditionStack(EnumData.Conditions conditionid, int amount)
	{
		return null;
	}

	public int GetConditionStack(EnumData.Conditions conditionid)
	{
		return 0;
	}

	private int AdjustMoveByWeather(int moveDistance)
	{
		return 0;
	}

	public int GetUnitConsumptionCost(int jobLevel)
	{
		return 0;
	}

	public bool isAvailableUpgradeClass()
	{
		return false;
	}

	public UnitJobLv GetUnitJobLevel()
	{
		return null;
	}

	private bool IsPromotable()
	{
		return false;
	}

	public bool IsVaildPromotionCondition(UnitJobLv unitJobLv, int idx)
	{
		return false;
	}

	public bool IsVaildPromotionCondition(UnitJobLv unitJobLv, int idx, out float pct, out string msg)
	{
		pct = default(float);
		msg = null;
		return false;
	}

	public bool IsReinforceable()
	{
		return false;
	}

	public bool IsMaxReinforceLevel()
	{
		return false;
	}

	public bool AreAllItemUpgraded()
	{
		return false;
	}

	public bool IsEquipMaxLevel(EnumData.ItemSlotType slot)
	{
		return false;
	}

	public bool IsNextItemReady(EnumData.ItemSlotType slot)
	{
		return false;
	}

	public bool CanEquipNextItem(EnumData.ItemSlotType slot)
	{
		return false;
	}

	public int GetNextItemMinLevel(EnumData.ItemSlotType slot)
	{
		return 0;
	}

	public bool DidUpgradeItem(EnumData.ItemSlotType slot)
	{
		return false;
	}

	public int GetMinLevelToReinforce()
	{
		return 0;
	}

	public bool IsEnoughLevelToReinforce()
	{
		return false;
	}

	public bool IsMayorUnit()
	{
		return false;
	}

	public UserTown GetTownWithMayorUnit()
	{
		return null;
	}

	public void InvalidateSpActionWithDeserializeCallback()
	{
	}

	public void InvalidateSpAction()
	{
	}

	public void RemoveBlacklistSpActions()
	{
	}

	public void RestoreBlacklistSpActions()
	{
	}

	private void UpdateUnitPassive(int index)
	{
	}

	private void UpdateJobPassives()
	{
	}

	private void UpdateEquipmentPassives(EquipSlot slot)
	{
	}

	private void UpdateRunePassives()
	{
	}

	public SpActionBase GetSpAction(EnumData.spAction spActionId, int spActionValue)
	{
		return null;
	}

	public SpActionBase GetSpAction(EnumData.spAction spActionId, int spActionValue, SpActionBase.Origin origin)
	{
		return null;
	}

	private void RemoveSpAction(SpActionBase.Origin origin)
	{
	}

	public void RemoveSpAction(SpActionBase spAction)
	{
	}

	private void ResetSpActionIntState()
	{
	}

	private void UpdateSpActionCache()
	{
	}

	public int GetHighestSpActionValue(EnumData.spAction spActionId)
	{
		return 0;
	}

	public int GetAccumulatedSpActionValue(EnumData.spAction spActionId)
	{
		return 0;
	}

	public T GetActiveSpAction<T>() where T : SpActionBase
	{
		return null;
	}

	public List<T> GetActiveSpActions<T>() where T : SpActionBase
	{
		return null;
	}

	public SpActionBase GetActiveSpAction(EnumData.spAction spActionId)
	{
		return null;
	}

	public int OnGetCalculatedIntToAccumulate<T>(int value) where T : SpActionBase
	{
		return 0;
	}

	public SpActionBase FindSpAction(EnumData.spAction spActionId, int spActionValue, SpActionBase.Origin origin, int initTurn, int remainedTurn, int initCount, int remainedCount)
	{
		return null;
	}

	public bool HasActiveSpAction<T>() where T : SpActionBase
	{
		return false;
	}

	public bool HasActiveSpAction(EnumData.spAction spActionId)
	{
		return false;
	}

	public SpActionBase AddSpAction(EnumData.spAction spActionId, int spActionValue, SpActionBase.Origin origin)
	{
		return null;
	}

	public void AddSpAction(SpActionBase spAction)
	{
	}

	private TroopStat AdjustStatByCondition(TroopStat baseStat)
	{
		return default(TroopStat);
	}

	private TroopStat AdjustStatBySpAction(TroopStat baseStat)
	{
		return default(TroopStat);
	}

	private TroopStat AdjustStatBySpActionInBattle(TroopStat baseStat)
	{
		return default(TroopStat);
	}

	private int GetSpecialAdjustValueByCondition(EnumData.Conditions condition, EnumData.Skills skillId, TroopStat baseStat, float factorValue)
	{
		return 0;
	}

	public UserUnit DeepCopy(UserUnit newUnit, bool copyUid = false)
	{
		return null;
	}

	public bool SetCustomLevelMode(int unitLevel)
	{
		return false;
	}

	public void ResetCustomLevelMode()
	{
	}

	private void UpdateClassicModeTrainStat(GeneralStat baseStat)
	{
	}

	public bool SetCustomTreasureFilterMode()
	{
		return false;
	}

	public void ResetCustomTreasureFilterMode()
	{
	}

	public bool SetCustomRuneFilterMode()
	{
		return false;
	}

	public void ResetCustomRuneFilterMode()
	{
	}
}
