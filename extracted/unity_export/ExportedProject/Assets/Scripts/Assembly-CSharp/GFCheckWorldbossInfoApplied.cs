using System.Collections;
using System.Diagnostics;
using LitJson;

public class GFCheckWorldbossInfoApplied : GameFlowNodeBase
{
	private int m_damage;

	protected override void DefineFollowers()
	{
	}

	protected override void _DoLogic()
	{
	}

	protected override int? _GetState()
	{
		return null;
	}

	private void ProcessCheckWorldbossInfoApplied()
	{
	}

	private void OnGetSavedDamage(JsonData response)
	{
	}

	private void OnApplySavedDamage(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator CallNextNode()
	{
		return null;
	}
}
