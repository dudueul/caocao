using System.Text;
using UnityEngine;

public class ShowDamage : MonoBehaviour
{
	public enum DamageType
	{
		normal = 0,
		gold = 1,
		poison = 2,
		mpDamage = 3,
		mpRecover = 4,
		heal = 5,
		miss = 6,
		burn = 7,
		epDamage = 8,
		epRecover = 9,
		guildWarDamageHP = 10,
		guildWarHeapHP = 11,
		guildWarMyDamageHP = 12
	}

	private GameObject m_dmgLabelObj;

	private UILabelLocalized m_label;

	private SpriteRenderer m_spriteRenderer;

	private int m_dmgValue;

	private bool m_playing;

	private bool m_isCompleted;

	private DamageType m_curDamageType;

	private const float AddHeightValue = 0.3f;

	private static StringBuilder ms_sb;

	private static string m_dmgNumObjNameFormat;

	private static readonly float m_spaceBetweenDigit;

	private static readonly float m_guildWarSpaceBetweenDigit;

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	private float GetSpaceBetweenDigit
	{
		get
		{
			return 0f;
		}
	}

	public void StartAnimation(int dmgValue, DamageType damageType, Transform parent = null)
	{
	}

	private GameObject GetFontResource(DamageType damageType)
	{
		return null;
	}

	private void OnShowDamageStarted(Object dmgLabelObj)
	{
	}

	private void OnShowDamageUpdated(float value)
	{
	}

	private void OnShowDamageCompleted(Object dmgLabelObj)
	{
	}

	private void OnDisable()
	{
	}

	public void GuildWarDestroy()
	{
	}

	private void ShowDigitValue(int value, GameObject targetObj)
	{
	}

	private float GetAddHeight()
	{
		return 0f;
	}
}
