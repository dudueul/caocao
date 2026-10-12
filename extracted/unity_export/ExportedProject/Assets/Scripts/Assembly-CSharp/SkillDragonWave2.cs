using System.Collections.Generic;

public class SkillDragonWave2 : SkillBase
{
	private enum WaveType
	{
		TopLeft = 0,
		TopRight = 1,
		BottomLeft = 2,
		BottomRight = 3
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

	public SkillDragonWave2(AttackBase attack, UnitData castUnit, UnitData targetUnit, List<UnitData> targetUnits, int successRate)
		: base(null, null)
	{
	}

	private void CalculatedDiagonalLine(AttackBase attack, List<UnitData> listTargetUnitDatas)
	{
	}

	private WaveType GetWaveType(IntVector2 triggerGrid, IntVector2 targetGrid)
	{
		return WaveType.TopLeft;
	}

	private bool SameWaveLine(IntVector2 triggerGrid, IntVector2 targetGrid)
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
