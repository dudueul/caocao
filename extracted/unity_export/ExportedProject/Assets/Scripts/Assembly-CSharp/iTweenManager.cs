using System.Collections.Generic;
using UnityEngine;

public class iTweenManager : Singleton<iTweenManager>
{
	private float prevUnscaledTime;

	private Dictionary<int, List<_iTween>> instID2tweens;

	public LinkedList<_iTween> _waitings;

	private void OnEnable()
	{
	}

	public void Register(_iTween tween)
	{
	}

	private void RegisterRef(_iTween tween)
	{
	}

	private void UnregisterRef(_iTween tween)
	{
	}

	public void Resume(string type)
	{
	}

	public void Resume(GameObject go, string type, bool includeChildrens)
	{
	}

	public void Pause(string type)
	{
	}

	public void Pause(GameObject go, string type, bool includeChildrens)
	{
	}

	public int Count(string type)
	{
		return 0;
	}

	public int Count(GameObject go, string type, bool includeChildrens)
	{
		return 0;
	}

	public int CountByName(GameObject go, string name, bool includeChildrens)
	{
		return 0;
	}

	public void Stop(string type)
	{
	}

	public void Stop(GameObject go, string type, bool includeChildrens)
	{
	}

	public void StopByName(string name)
	{
	}

	public void StopByName(GameObject go, string name, bool includeChildrens)
	{
	}

	public void ConflictCheck(_iTween newTween)
	{
	}

	private void Update()
	{
	}

	private void FixedUpdate()
	{
	}

	private void LateUpdate()
	{
	}
}
