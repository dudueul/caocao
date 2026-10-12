using System.Collections;
using System.Diagnostics;

public class HpBar : EnergyBarBase
{
	private int m_from;

	private int m_to;

	private bool showingDelta;

	private bool m_isFirst;

	private void Start()
	{
	}

	public override void ShowExpect(float deltaPct)
	{
	}

	public override void AddEffect(int from, int to)
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	public override void OnAnimCompleted()
	{
	}

	public override void OnComplete()
	{
	}

	public override EnumData.UnitResType GetBarResType()
	{
		return EnumData.UnitResType.HP;
	}
}
