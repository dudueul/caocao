using UnityEngine;

public class RaidStuffUI : MonoBehaviour
{
	[SerializeField]
	private EnumData.RaidStuffType m_stuffType;

	private bool m_initialized;

	private UILabelLocalized m_valueLabel;

	private UISprite m_icon;

	public EnumData.RaidStuffType StuffType
	{
		get
		{
			return EnumData.RaidStuffType.NONE;
		}
		set
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnClickStuff()
	{
	}
}
