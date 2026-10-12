using System.Collections;

internal interface ISecuritySlave
{
	void SetMaster(ISecurityMaster master);

	IEnumerator ComputeHash(HashType hashType);
}
