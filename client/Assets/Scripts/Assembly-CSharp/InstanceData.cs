using UnityEngine;

public class InstanceData : MonoBehaviour
{
	private UserInstance m_userInst;

	public CustomPlaceLabel m_name;

	public CustomPlaceLabel m_time;

	public GameObject m_timeBG;

	public GameObject m_capitalIcon;

	public GameObject[] m_icons;

	public UserInstance userInst
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void UpdateUI()
	{
	}

	private void UpdateExpireTime()
	{
	}

	private void OnDestroy()
	{
	}
}
