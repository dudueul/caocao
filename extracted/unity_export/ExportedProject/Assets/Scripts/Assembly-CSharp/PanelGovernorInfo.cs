using UnityEngine;

public class PanelGovernorInfo : MonoBehaviour
{
	public UILabelLocalized m_townName;

	public UILabelLocalized m_userName;

	public PrefabInstance m_unitInst;

	private int m_townId;

	private MayorEffectUI m_mayerEffectUI
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	public static void Toggle(bool show)
	{
	}

	public static void RefreshUI(int townId)
	{
	}

	public static void OnMayorUpdated(int townId)
	{
	}

	public void UpdateUI()
	{
	}

	public void OnChangeMayor()
	{
	}
}
