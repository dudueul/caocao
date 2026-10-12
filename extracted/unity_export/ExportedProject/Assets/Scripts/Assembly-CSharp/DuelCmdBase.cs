using System.Collections;
using UnityEngine;

public abstract class DuelCmdBase
{
	protected GameObject m_Owner;

	protected GameObject m_Enemy;

	public bool isCompleted;

	public abstract void SetData(params object[] args);

	public abstract IEnumerator ProcessCommand();

	public abstract void OnCommandStarted();

	public abstract void OnCommandFinished();
}
