using System.Collections;
using System.Diagnostics;

internal class DummySecuritySlave : ISecuritySlave
{
	private ISecurityMaster m_master;

	public void SetMaster(ISecurityMaster master)
	{
	}

	[DebuggerHidden]
	public IEnumerator ComputeHash(HashType hashType)
	{
		return null;
	}
}
