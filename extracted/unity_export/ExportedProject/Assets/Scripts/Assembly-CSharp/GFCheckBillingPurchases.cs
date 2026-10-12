using System.Collections;
using System.Diagnostics;
using CaoCaoIAB;

public class GFCheckBillingPurchases : GameFlowNodeBase
{
	protected override void DefineFollowers()
	{
	}

	protected override void _DoLogic()
	{
	}

	private void BillingInitSucceed()
	{
	}

	private void BillingInitFailed()
	{
	}

	private void QueryInventorySucceed(Inventory inv)
	{
	}

	private void QueryInventoryFailed(string err)
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait2CheckQueryFinish()
	{
		return null;
	}

	private void CallNextFlow()
	{
	}

	protected override int? _GetState()
	{
		return null;
	}
}
