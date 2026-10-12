using CodeStage.AntiCheat.ObscuredTypes;
using HutongGames.PlayMaker;
using UnityEngine;

public class WorldBossBattleController : SceneSingleton<WorldBossBattleController>
{
	private GameObject m_object;

	private WorldBossState m_bossState;

	private UITexture[] m_weaknessTextureUI;

	private PlayMakerFSM m_fsm;

	private FsmString m_stateString;

	private UILabelLocalized m_armorCountLabel;

	private UnitData m_targetUnitData;

	private ObscuredInt m_maxRes;

	private ObscuredInt m_curRes;

	private ObscuredInt m_delta;

	private bool m_battleStart;

	private WorldbossDamageRewardInfo m_damageRewardInfo;

	private int m_curArmorCount;

	private bool m_hitted;

	private bool m_initialized;

	public UnitData TargetUnitData
	{
		get
		{
			return null;
		}
	}

	public int CurArmorCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool Hitted
	{
		get
		{
			return false;
		}
	}

	protected override void OnDestroy()
	{
	}

	public void Initialize(UnitData targetUnitData, int worldbossId, ulong maxDamage)
	{
	}

	public void StartBattle()
	{
	}

	private void SetRes(int value, bool isRevert = false)
	{
	}

	private bool CheckValid(UnitData unit)
	{
		return false;
	}

	public void UpdateWeaknessInfo(UnitData unitData)
	{
	}

	public void UpdateArmorCount(UnitData unitData, EnumData.Conditions conditionId, int value)
	{
	}

	public int GetDeltaRes()
	{
		return 0;
	}

	public int GetCurRes()
	{
		return 0;
	}

	public int GetMaxRes()
	{
		return 0;
	}

	public void ApplyEffect(UnitData unitData, int to)
	{
	}

	public void RevertEffect(UnitData unitData, int to)
	{
	}

	public void Animate(UnitData unitData, int from, int to, bool isRevert = false)
	{
	}

	public void SendDamageToServer()
	{
	}
}
