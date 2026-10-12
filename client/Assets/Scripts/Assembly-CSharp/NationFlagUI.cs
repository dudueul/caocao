using UnityEngine;

[ExecuteInEditMode]
public class NationFlagUI : MonoBehaviour
{
	private IUserDataBase m_userData;

	private string m_nationCode;

	public IUserDataBase userData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private string nationCode
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void Start()
	{
	}

	private void UpdateSprite()
	{
	}

	public void UpdateData(IUserDataBase user)
	{
	}

	public void UpdateData(string countryCode)
	{
	}

	private void SetSprite(bool inUiRoot, string name)
	{
	}
}
