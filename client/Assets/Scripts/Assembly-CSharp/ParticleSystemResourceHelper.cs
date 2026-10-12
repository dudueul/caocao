using System.Collections.Generic;
using UnityEngine;

public class ParticleSystemResourceHelper : MonoBehaviour, IWeakResourceRef
{
	public string materialPath;

	private void OnEnable()
	{
	}

	public void DisconnectResourceRef(List<string> adjustedPaths)
	{
	}

	void IWeakResourceRef.ConnectResourceRef()
	{
	}
}
