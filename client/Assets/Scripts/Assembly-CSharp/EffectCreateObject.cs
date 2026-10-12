using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public class EffectCreateObject : EffectBase
{
	private BattleMapObject m_mapObject;

	private IntVector2 m_pos;

	private int m_objectId;

	private int m_prevState;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectCreateObject(UnitObject triggerUnit, UnitObject targetUnit, int objectId)
		: base(null, null)
	{
	}

	public EffectCreateObject(UnitObject triggerUnit, UnitObject targetUnit, IntVector2 pos, int objectId)
		: base(null, null)
	{
	}

	public override void _Apply()
	{
	}

	protected override bool _ApplyOnLoading()
	{
		return false;
	}

	public override void Redo()
	{
	}

	public override void _Revert()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectCreateObject Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	private void DoProcess()
	{
	}
}
