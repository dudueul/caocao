using System.Collections.Generic;

public class SkillDragonWave1 : SkillBase
{
	private enum WaveType
	{
		Top = 0,
		Right = 1,
		Bottom = 2,
		Left = 3
	}

	public override EnumData.Skills skillID
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	public override EnumData.SkillTypes skillType
	{
		get
		{
			return EnumData.SkillTypes.ATK_FIRE;
		}
	}

	public SkillDragonWave1(AttackBase attack, UnitData castUnit, UnitData targetUnit, List<UnitData> targetUnits, int successRate)
		: base(null, null)
	{
	}

	private void CalculatedStraightLine(AttackBase attack, List<UnitData> listTargetUnitDatas)
	{
	}

	private WaveType GetWaveType(IntVector2 triggerGrid, IntVector2 targetGrid)
	{
		return WaveType.Top;
	}

	private bool SameWaveLine(WaveType waveType, IntVector2 triggerGrid, IntVector2 targetGrid)
	{
		return false;
	}

	private bool IsInnerGridPos(WaveType waveType, IntVector2 dstGrid, IntVector2 srcGrid)
	{
		return false;
	}

	private void CalcDirectionValue(WaveType waveType, ref IntVector2 toGrid, bool isAdd = true)
	{
	}
}
