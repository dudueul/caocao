using UnityEngine;

public class BattleScreenFxController : SceneSingleton<BattleScreenFxController>
{
	private UnitScreenFxPool m_fxObjectPool;

	private const float fadeOutDuration = 0.3f;

	private GameObject m_curScreenFx;

	private bool errorDetected;

	private bool inited;

	protected BattleScreenFxController()
	{
	}

	private void Start()
	{
	}

	public void Init()
	{
	}

	private EnumData.UnitScreenFx GetScreenFxTypeForUnit(ControlFlow.statusType statusType, UnitData unitData)
	{
		return EnumData.UnitScreenFx.None;
	}

	public void ControlUnitScreenFx(EnumData.UnitScreenFx fxType)
	{
	}

	public void UpdateUnitScreenFxForControlFlow(ControlFlow.statusType statusType)
	{
	}

	public void DestroyUnitScreenFxImmediate()
	{
	}

	protected override void OnDestroy()
	{
	}
}
