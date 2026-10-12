using System.Collections;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class BattleFieldIcon : MonoBehaviour
{
	[SerializeField]
	private tk2dSprite m_townIcon;

	[SerializeField]
	private TextMesh m_townNameMesh;

	[SerializeField]
	private SpriteRenderer m_townBgSpriteRenderer;

	private ObscuredInt m_id;

	private Color m_initTownIcon;

	private Color m_initTownName;

	private Color m_initTownBg;

	public int permanentDataId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public void ClickIcon()
	{
	}

	public void InitIconFadeIn()
	{
	}

	public void StartIconFadeIn()
	{
	}

	[DebuggerHidden]
	private IEnumerator IconFadeIn()
	{
		return null;
	}

	public void UpdateUI()
	{
	}

	private void SetIconColor(MeshRenderer renderer, Color32 color)
	{
	}

	private void SetIconAlpha(float alpha)
	{
	}
}
