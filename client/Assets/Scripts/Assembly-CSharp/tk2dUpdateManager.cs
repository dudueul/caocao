using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class tk2dUpdateManager : MonoBehaviour
{
	private static tk2dUpdateManager inst;

	[SerializeField]
	private List<tk2dTextMesh> textMeshes;

	private static tk2dUpdateManager Instance
	{
		get
		{
			return null;
		}
	}

	public static void QueueCommit(tk2dTextMesh textMesh)
	{
	}

	public static void FlushQueues()
	{
	}

	private void OnEnable()
	{
	}

	private void LateUpdate()
	{
	}

	[DebuggerHidden]
	private IEnumerator coSuperLateUpdate()
	{
		return null;
	}

	private void QueueCommitInternal(tk2dTextMesh textMesh)
	{
	}

	private void FlushQueuesInternal()
	{
	}
}
