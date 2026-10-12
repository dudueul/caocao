using UnityEngine;

[ExecuteInEditMode]
public class JobStyleUI : MonoBehaviour
{
	private static string[] m_jobStyleObjNames;

	public UserUnit userUnit { get; private set; }

	public Job jobData { get; private set; }

	public void SetUserUnit(UserUnit unit)
	{
	}

	private void UpdateJobIcon()
	{
	}
}
