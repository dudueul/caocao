using System;
using System.Collections;
using CodeStage.AntiCheat.ObscuredTypes;

internal interface ISecurityMaster
{
	IEnumerator ComputeHash(ObscuredString url, Action<ObscuredString> callback);

	void AddParameter(ObscuredString name, ObscuredString value);
}
